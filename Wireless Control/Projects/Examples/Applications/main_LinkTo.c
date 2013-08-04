/* 
 * wireless-PC.c - Firmware for the eZ430 wireless chip connected to the PC
 * By Donghyo Min, Eric Hare, and Josh Peterson
 */

#include "bsp.h"
#include "mrfi.h"
#include "nwk_types.h"
#include "nwk_api.h"
#include "bsp_leds.h"
#include "bsp_buttons.h"
#include "app_remap_led.h"
#include "uart.h" // For UART

// Function that links to the other module
static void linkTo(void);

// Toggles the LED
void toggleLED(uint8_t);

// References for the linked chip
static linkID_t sLinkID1 = 0;
static uint8_t sRxCallback(linkID_t);

void main (void)
{
  	// Set clock to 8MHZ
  	DCOCTL = CALDCO_8MHZ;
  	BCSCTL1 = CALBC1_8MHZ;
  
  	// Initialize the BSP and communication (UART) modules
	BSP_Init();
	COM_Init();

	// Initialize the receive callbacks
  	SMPL_Init(sRxCallback);
	
	// Link
  	linkTo();
}

// Makes the link to the other chip
static void linkTo()
{
 	uint8_t msg;
  
  	// wireless-Blimp address is {0x78, 0x52, 0x36, 0x13}
  	// wireless-PC address is {0x79, 0x57, 0x33, 0x13}
  	// MRFI_ADDR_SIZE is 4
  	addr_t address;
  	address.addr[0] = 0x78;
  	address.addr[1] = 0x52;
  	address.addr[2] = 0x36;
  	address.addr[3] = 0x13;
	
	// Connect to the other chip
  	smplStatus_t status = SMPL_Commission(&address, 0x3D, 0x3E, &sLinkID1);
  	while (SMPL_SUCCESS != status)
  	{
    	status = SMPL_Commission(&address, 0x3D, 0x3E, &sLinkID1);
  	}
  
	// Turn on LEDs
	if (!BSP_LED2_IS_ON())
	{
	  	toggleLED(2);
	}
	if (!BSP_LED1_IS_ON())
	{
	    toggleLED(1);
	}

    /* turn on RX. default is RX off. */
    SMPL_Ioctl( IOCTL_OBJ_RADIO, IOCTL_ACT_RADIO_RXON, 0);

    while (1)
    {
    
		// Main loop.  Grab a command, send it to the other wireless chip
	    int client = getClientIndex();
	    if (getIndex() != client) {
	    	msg = getCommandBuffer()[client];
	       	SMPL_Send(sLinkID1, &msg, sizeof(msg));
	       	
	       	client++;
	       	if (client == 20) {
	       		client = 0;
	       	}
	       	setClientIndex(client);
   		}	 
    }
}

// Handle received messages
static uint8_t sRxCallback(linkID_t port)
{
  	uint8_t len;
  	uint8_t msg;

  	// Make sure its the right chip
  	if (port == sLinkID1)
  	{
  		// If we successfully receive it, transmit it and return
     	if ((SMPL_SUCCESS == SMPL_Receive(sLinkID1, &msg, &len)) && len)
     	{
       
       		TXChar(msg);
      
       		return 1;
     	}
  	}
  
  return 0;
}

// This function toggles a particular LED
void toggleLED(uint8_t which)
{
  if (1 == which)
  {
    BSP_TOGGLE_LED1();
  }
  else if (2 == which)
  {
    BSP_TOGGLE_LED2();
  }
  return;
}
