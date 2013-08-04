/* 
 * control.h - Header file for the blimp commands
 * By Donghyo Min, Eric Hare, and Josh Peterson
 */

#ifndef CONTROL_H_
#define CONTROL_H_

#include <msp430f5510.h>
#include <string.h>

#define PERIOD 32768 // 8mhz / 32768 = ~250Hz
#define LEFT_MOTOR_OFF 10 // Command to turn off the left motor
#define RIGHT_MOTOR_OFF 27 // Command to turn off the right motor
#define TOP_MOTOR_OFF 44 // Command to turn off the top motor

// Commands that the blimp can execute
void parseCommand(char command);
void commandRun();
void commandStop();
void commandStandby();
void commandBrake();
void commandSpeed(int motor, int speed1, int speed2);
void commandSensor(int direction);
void commandPower();

#endif /*CONTROL_H_*/
