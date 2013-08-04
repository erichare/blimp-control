/* 
 * uart.c - UART for the wireless chips
 * By Donghyo Min, Eric Hare, and Josh Peterson
 */

#include <string.h>
#include "uart.h"

// Buffers for the commands and replies
uint8_t command_buffer[20];
uint8_t reply_buffer[20];

// Flags for whether a command or a reply were received
int reply_received;

// Tracks the number of bars and characters for a reply
int bar_count = 0;
int char_count = 0;
int clientIndex = 0;
int index = 0;

/*
 * Initialize the UART
 */ 
void COM_Init()
{
  P3SEL |= 0x30;                            // P3.4,5 = USCI_A0 TXD/RXD
  UCA0CTL1 = UCSSEL_2;                      // SMCLK

  UCA0BR0 = 0x41;                           // 9600 from 8Mhz
  UCA0BR1 = 0x3;
  UCA0MCTL = UCBRS_2;
  UCA0CTL1 &= ~UCSWRST;                     // **Initialize USCI state machine**
  IE2 |= UCA0RXIE;                          // Enable USCI_A0 RX interrupt
  __enable_interrupt();
}

/*
 * Transmits a string on the UART TX
 */ 
void TXString(char* string, int length)
{
  int pointer;
  for( pointer = 0; pointer < length; pointer++)
  {
    volatile int i;
    UCA0TXBUF = string[pointer];
    while (!(IFG2&UCA0TXIFG));              // USCI_A0 TX buffer ready?
  }
}

/*
 * Transmits a char on the UART TX
 */ 
void TXChar(char chr)
{
    UCA0TXBUF = chr;
    while (!(IFG2&UCA0TXIFG));              // USCI_A0 TX buffer ready?
}

/*
 * Returns the command buffer
 */ 
uint8_t* getCommandBuffer() {
	return command_buffer;
}

/*
 * Returns the reply buffer
 */ 
uint8_t* getReplyBuffer() {
	return reply_buffer;
}

/*
 * Return flag to determine whether a reply has been received
 */
int getReplyReceived() {
	return reply_received;
}

/*
 * Set flag to determine whether a command has been received
 */
void setReplyReceived(int val) {
	reply_received = val;
}

/*
 * Return the character count of the reply received
 */
int getIndex() {
	return index;
}

/*
 * Return the character count of the reply received
 */
int getClientIndex() {
	return clientIndex;
}

/*
 * Return the bar count of the reply received
 */
void setClientIndex(int val) {
	clientIndex = val;
}

/*
 * Return the character count of the reply received
 */
int getCharCount() {
	return char_count;
}

/*
 * Return the bar count of the reply received
 */
int getBarCount() {
	return bar_count;
}

/*
 * Set the character count of the reply received
 */
void setCharCount(int val) {
	char_count = val;
}

/*
 * Set the bar count of the reply received
 */
void setBarCount(int val) {
	bar_count = val;
}

// Interrupt routine for the USCI
#pragma vector=USCIAB0RX_VECTOR
__interrupt void USCI0RX_ISR(void)
{
  uint8_t chr = UCA0RXBUF;
  
  // Replies are capital letters, commands are lowercase
  if (chr == '|') {
  	 bar_count++;
  	 
  	 if (bar_count == 2) {
  	 	reply_received = 1;
  	 }
  	 
  // Then this is a command
  } else if (bar_count == 0) {
  	 command_buffer[index] = chr;
  	 
  	 // Circular command buffer
  	 index++;
  	 if (index == 20) {
  	 	index = 0;
  	 }
     
  // Then this is a reply
  } else if (bar_count == 1) {
  	 reply_buffer[char_count] = chr;
  	 char_count++;
  }
  
}
