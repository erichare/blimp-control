/* 
 * uart.h - Header file for the blimp UART
 * By Donghyo Min, Eric Hare, and Josh Peterson
 */

#ifndef UART_H_
#define UART_H_

#include <msp430f5510.h>
#include <string.h>

// Commands to transmit on the UART
void itoa(unsigned int val, char *str, int newLine, char sensorPolled);
void TXString(char* string, int length);
void TXChar(char chr);

#endif /*UART_H_*/
