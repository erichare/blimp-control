/* 
 * uart.c - UART for the blimp
 * By Donghyo Min, Eric Hare, and Josh Peterson
 */

#include "uart.h"

/*
 * Stores the ascii representation of val into str, optionally with a newline
 */
void itoa(unsigned int val, char *str, int newLine, char sensorPolled)
{
	// Initialize the digits and the indexs
  	int oldLoc = 0;
  	int newLoc = 1;
  	int digit = 0;
  	
  	// Stores the temporary char array for our val in reverse order
  	char tmp[10];

	// While the value is greater than 0, grab a digit, store it, read next digit
  	while (val > 0) 
  	{
  		digit = val % 10;
    	tmp[oldLoc++] = digit + '0';
    	val /= 10;
  	}

  	// Put the digits into the output string in reverse order
  	str[0] = '|';
  	while (oldLoc > 0) 
  	{
    	str[newLoc++] = tmp[--oldLoc];
  	}
	
	// Add the final terminating char
	str[newLoc++] = '0';
	str[newLoc++] = sensorPolled;
	
	// Add a newline if newLine = 1
	if (newLine) str[newLoc++] = '\n';
	
	str[newLoc++] = '|';
  	str[newLoc] = '\0';
}

/*
 * Transmits 'length' characters of 'string' on the UART
 */
void TXString(char* string, int length)
{
  int pointer;
  for( pointer = 0; pointer < length; pointer++)
  {
    while (!(UCA1IFG&UCTXIFG)); // USCI_A1 TX buffer ready?
    UCA1TXBUF = string[pointer];
  }
}

/*
 * Transmits a single character 'chr' on the UART
 */
void TXChar(char chr)
{
    UCA1TXBUF = chr;
    while (!(UCA1IFG&UCTXIFG)); // USCI_A1 TX buffer ready?
}
