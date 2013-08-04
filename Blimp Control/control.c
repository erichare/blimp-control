/* 
 * control.c - Control module for the Blimp
 * By Donghyo Min, Eric Hare, and Josh Peterson
 */

#include "control.h" // Defines the control functions
#include "uart.h" // Defines the UART TX functions

unsigned int sensorCount = 0;
unsigned int sensorSum = 0;
char sensorChoice = '0';
int topReverse = 0; // Flag: if 0, the top motor is in forward mode. else reverse
int runReceived = 0; // The run command has been received if this flag is set

void main(void)
{
  WDTCTL = WDTPW+WDTHOLD;
	
  // Initialize PWM/Motor control, and 1.6 for IR sensor
  P1DIR |= BIT0 + BIT1 + BIT2 + BIT3 + BIT4 + BIT5 + BIT6;
  P1SEL |= BIT2 + BIT3 + BIT4 + BIT5;
  
  // Pins for IR Sensor
  P2DIR |= BIT0;
  PJDIR |= BIT0 + BIT1;
  
  // ADC (Sensor) stuff
  ADC10CTL0 = ADC10SHT2 + ADC10ON;
  ADC10CTL1 = ADC10SHP;
  ADC10CTL0 |= ADC10ENC;
  P6SEL |= BIT0;
  
  // Use TA1 for a software PWM for the up/down motor
  TA1CTL = TASSEL_2 + MC_1 + TACLR;
  TA1CCR0 = 0;
  TA1CCTL0 = ~CCIE;
  
   // Use TA2 for ADC
  TA2CTL = TASSEL_2 + MC_1 + TACLR;
  TA2CCR0 = 0;
  TA2CCTL0 = ~CCIE;
  
  // Set the four PWMs to set/reset mode
  TA0CCTL1 = OUTMOD_7;
  TA0CCTL2 = OUTMOD_7;
  TA0CCTL3 = OUTMOD_7;
  TA0CCTL4 = OUTMOD_7;
  
  // Use TA0 Hardware PWMs for the left and right motors
  TA0CTL = TASSEL_2 + MC_1 + TACLR;
  TA0CCR0 = PERIOD;
  TA0CCR1 = 0;
  TA0CCR2 = 0;
  TA0CCR3 = 0;
  TA0CCR4 = 0;
  
  /*
   * The following block of code sets the clock to 8MHZ.
   */
  UCSCTL3 = SELREF_2;                       // Set DCO FLL reference = REFO
  UCSCTL4 |= SELA_2;                        // Set ACLK = REFO
  UCSCTL0 = 0x0000;                         // Set lowest possible DCOx, MODx

  // Loop until XT1,XT2 & DCO stabilizes - In this case only DCO has to stabilize
  do
  {
    UCSCTL7 &= ~(XT2OFFG + XT1LFOFFG + DCOFFG);
                                            // Clear XT2,XT1,DCO fault flags
    SFRIFG1 &= ~OFIFG;                      // Clear fault flags
  }while (SFRIFG1&OFIFG);                   // Test oscillator fault flag
	
  __bis_SR_register(SCG0);                  // Disable the FLL control loop
  UCSCTL1 = DCORSEL_5;                      // Select DCO range 16MHz operation
  UCSCTL2 |= 255;                           // Set DCO Multiplier for 8MHz
                                            // (N + 1) * FLLRef = Fdco
                                            // (249 + 1) * 32768 = 8MHz
  __bic_SR_register(SCG0);                  // Enable the FLL control loop

  // Worst-case settling time for the DCO when the DCO range bits have been
  // changed is n x 32 x 32 x f_MCLK / f_FLL_reference. See UCS chapter in 5xx
  // UG for optimization.
  // 32 x 32 x 8 MHz / 32,768 Hz = 250000 = MCLK cycles for DCO to settle
  __delay_cycles(250000);
  
  
  // Code to initialize the UART TX/RX to an 8MHZ clock, 9600 baud
  P4SEL |= BIT4+BIT5;
  UCA1CTL1 |= UCSWRST;
  UCA1CTL1 |= UCSSEL_2;
  UCA1BR0 = 54;
  UCA1BR1 = 0;
  UCA1MCTL = UCBRS_0 + UCBRF_10 + UCOS16;
  UCA1CTL1 &= ~UCSWRST;
  UCA1IE |= UCRXIE;
    
  __bis_SR_register(LPM0_bits + GIE);
}

// Timer1 A0 interrupt service routine
#pragma vector=TIMER2_A0_VECTOR
__interrupt void TIMER2_A0_ISR(void)
{
	if (sensorCount > 40) {
		TA2CCR0 = 0;
		TA2CCTL0 = ~CCIE;
		// Grab the ADC10 reading, send it over UART
	    char buf[10];
	    itoa(sensorSum, buf, 1, sensorChoice);
	    
		TXString(buf, strlen(buf));
		
		sensorCount = 0;
		sensorSum = 0;
	} else if (sensorCount > 0) {
		sensorSum += ADC10MEM0;
		sensorCount++;
	} else {
		sensorCount++;
	}
}

// Timer1 A0 interrupt service routine
#pragma vector=TIMER1_A0_VECTOR
__interrupt void TIMER1_A0_ISR(void)
{
   
   // If we are in forward mode, toggle the reverse bit.  And visa versa.
   if (topReverse == 0) {
   	  P1OUT ^= BIT1; // Toggle 1.1;
   } else {
   	  P1OUT ^= BIT0; // Toggle 1.0;
   }
   
   // Software PWM
   int oldTimer = TA1CCR0;
   TA1CCR0 = PERIOD - oldTimer;
}

// UART interrupt service routine
#pragma vector=USCI_A1_VECTOR
__interrupt void USCI_A1_ISR(void)
{
  	  parseCommand(UCA1RXBUF);
}

/*
 * Main command parsing routing.  Given the command, call the correct function
 */
void parseCommand(char command) {
	// 0 = Stop Command
	if (command == 0) {
		commandStop();
		
	// 1 = Run Command
	} else if (command == 1) {
		commandRun();
		
	// 2 - 18 = Left Motor Control (Reverse - Forward)
	} else if (command <= 10) {
		commandSpeed(0, (10 - command) << 12, 0);
	} else if (command <= 18) {
		commandSpeed(0, 0, (command - 10) << 12);
		
	// 19 - 35 = Right Motor Control (Reverse - Forward)
	} else if (command <= 27) {
		commandSpeed(1, (27 - command) << 12, 0);
	} else if (command <= 35) {
		commandSpeed(1, 0, (command - 27) << 12);
		
	// 36 - 52 = Top Motor Control (Reverse - Forward)
	} else if (command <= 44) {
		commandSpeed(2, (44 - command) << 12, 0);
	} else if (command <= 52) {
		commandSpeed(2, 0, (command - 44) << 12);
		
	// 53 - 57 = IR Sensor (Directions 0 - 4)
	} else if (command <= 57) {
		commandSensor(command - 53);
		
	// 58 = Power Command
	} else if (command == 58) {
		commandPower();
	}
}

/*
 * Command to stop the blimp, disable motors and IR
 */
void commandStop() {
	P1OUT = 0;
	P2OUT = 0; // Sensor disable
	
	TA0CCR1 = 0;
	TA0CCR2 = 0;
	TA0CCR3 = 0;
	TA0CCR4 = 0;
	
	TA1CCTL0 = ~CCIE;
	TA1CCR0 = 0;
	
	runReceived = 0;
}

/*
 * Command to run the blimp, enable and ready for commands
 */
void commandRun() {
	P1OUT = BIT0 + BIT1;
	P2OUT = BIT0; // Sensor enable
	
	TA0CCR1 = PERIOD;
    TA0CCR2 = PERIOD;
    TA0CCR3 = PERIOD;
    TA0CCR4 = PERIOD;
    
	TA1CCTL0 = CCIE;
	
	runReceived = 1;
}

/*
 * Puts blimp in standby mode, which just calls the run command again
 */
void commandStandby() {
	if (runReceived == 1) {
		commandRun();
	}
}

/*
 * Brake command (not used)
 */
void commandBrake() {
	if (runReceived == 1) {
	}
}

/*
 * Command to control the speed of a motor.  The parameters are:
 * Motor: Left = 0, Right = 1, Top = 2
 * Direction: Forward = 0, Reverse = 1
 */
void commandSpeed(int motor, int speed1, int speed2) {
	if (runReceived == 1) {
		if (motor == 0) {
			TA0CCR1 = PERIOD - speed1;
			TA0CCR2 = PERIOD - speed2;
		} else if (motor == 1) {
			TA0CCR3 = PERIOD - speed1;
			TA0CCR4 = PERIOD - speed2;
		} else {
			int topSpeed = (speed1 == 0? speed2 : speed1);
			if (topSpeed == 0) {
				P1OUT |= BIT0 + BIT1;
				TA1CCTL0 = ~CCIE;
			} else {
				TA1CCTL0 = CCIE;
			}
			TA1CCR0 = topSpeed;
			if (topSpeed > 0 && speed2 > 0) {
				P1OUT |= BIT1;
				P1OUT &= ~BIT0;
				topReverse = 0;
			} else if (topSpeed > 0 && speed1 > 0) {
				P1OUT |= BIT0;
				P1OUT &= ~BIT1;
				topReverse = 1;
			}
		}
	}
}

/*
 * Command to control the IR sensor
 * Direction: Forward = 0, Reverse = 1, Right = 2, Left = 3, Down = 4
 */
void commandSensor(int direction) {
	ADC10CTL0 |= ADC10SC;
	
	if (direction == 0) {
		sensorChoice = '0';
    	P1OUT &= ~BIT6;
 		PJOUT = 0;
	} else if (direction == 1) {
		sensorChoice = '1';
		P1OUT |= BIT6;
 		PJOUT = 0;
	} else if (direction == 2) {
		sensorChoice = '2';
		P1OUT &= ~BIT6;
 		PJOUT = BIT0;
	} else if (direction == 3) {
		sensorChoice = '3';
		P1OUT |= BIT6;
 		PJOUT = BIT0;
	} else if (direction == 4) {
		sensorChoice = '4';
		P1OUT &= ~BIT6;
		PJOUT = BIT1;
	}
	
	TA2CCR0 = 100;
	TA2CCTL0 = CCIE;
}

/*
 * Command to read the MSP supply voltage
 */
void commandPower() {
	/*ADC10CTL0 &= ~ADC10ENC;                   // Disable conv to allow mod of ADC10CTL0 0xFFFx
    while(REFCTL0 & REFGENBUSY);              // If ref generator busy, WAIT
	REFCTL0 |= REFON+REFVSEL_2;                        
	ADC10CTL0 = ADC10ON+ADC10SHT_2;           // turn on ADC10
  	ADC10CTL1 = ADC10SHP;                     // use sampling timer
  	ADC10MCTL0 = ADC10INCH_11+ADC10SREF_1;     // channel = A11, internal ref
  	ADC10CTL0 |= ADC10ENC;                    // Enable conversions
	
	while (!(ADC10CTL0 & ADC10IFG));                // Conversion done?
	
	int voltage = ADC10MEM0;
	char buf[10];
    itoa(voltage, buf, 1, '9');
    
	TXString(buf, strlen(buf));*/
}
