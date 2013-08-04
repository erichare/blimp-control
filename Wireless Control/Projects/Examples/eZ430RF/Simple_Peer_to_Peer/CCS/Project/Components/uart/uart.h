/* 
 * uart.h - Header file for the wireless chip uarts
 * By Donghyo Min, Eric Hare, and Josh Peterson
 */

#ifndef UART_H_
#define UART_H_

#include <string.h>
#include "bsp.h"

// Get and set the received command/reply flags
int getReplyReceived();
void setReplyReceived(int val);


// Get the command/reply buffers
uint8_t* getCommandBuffer();
uint8_t* getReplyBuffer();

// Get and set the character and bar count (used for replies)
int getIndex();
int getClientIndex();
void setClientIndex(int val);
int getCharCount();
int getBarCount();
void setCharCount(int val);
void setBarCount(int val);

// Initialize the UART, transmit a character and string
void COM_Init(void);
void TXChar(char chr);
void TXString(char* string, int length);

#endif /*UART_H_*/
