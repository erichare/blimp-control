/*
 * Donghyo Min, Eric Hare, Josh Peterson
 * 
 * This file sets for serial communication.
 * This would be searching available ports, then allow users to select.
 * After connection made, then it can read.
 */

import gnu.io.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class PortCommunication implements SerialPortEventListener
{
	public static final int WIDTH = 50;
	public static final int NUM_SENSOR = 2;
	public boolean inFinalMode = false;
    
	//the timeout value for connecting with the port
    final static int TIMEOUT = 1800;

    //ascii values for space and new_line char
    final static int SPACE = 32;
    final static int NEW_LINE = 10;
    
	// drawing panel to draw graphs on.
	Panel drawing_panel = null;
	
	Keyboard key_b = null;
	
	// Object for graphs, color, font, etc
    Graphics g = null;
    
    // values from sensor
    int height1 = 0;	// for sensor 1
    int height2 = 0;	// for sensor 2
    
    // the number of values sensors got.
    int count = 0;	
    
    // Array list to store each char.
    // Later, the added chars are combined, then cast to int.
    ArrayList<String> list;
    
    // value from a sensor. 
    // value right before casting.
    String output1 = "";
    
    // Anything written on the drawing panel. 
    String event_info = "";
    byte singleData = 0;
    String value = "";
    
    
    
    
    // distance from a sensor.
    Integer distance = 0;
    
    // available ports.
    private Enumeration ports = null;
    
    // mapping port name
    private HashMap m_ports = new HashMap();

    // ports a user open.
    private CommPortIdentifier open_port = null;
    private SerialPort serialPort = null;

    private InputStream input = null;	// input
    protected OutputStream output = null;

    // connectivity
    private boolean conn = false;

    
    public int dummy = 0;
    
    
    
    
    // constructor
    // drawing panel and graphic object built
    public PortCommunication(Panel drawing_panel)
    {
    	this.list = new ArrayList<String>();
        this.drawing_panel = drawing_panel;
        this.g = this.drawing_panel.getGraphics();
    }
    
    private void init(){
    	g.setColor(Color.cyan);
        g.fillRect(25, 360, 40, 25);
        
        g.setColor(Color.green);
        g.fillRect(83, 360, 40, 25);
        
        g.setColor(Color.orange);
        g.fillRect(140, 360, 40, 25);
        
        g.setColor(Color.pink);
        g.fillRect(197, 360, 40, 25);
        
        g.setColor(Color.red);
        g.fillRect(254, 360, 40, 25);
    }
    // looking for available prots.
    // Once it figures out which one is available, it will add it.
    public void port_searcher()
    {
        ports = CommPortIdentifier.getPortIdentifiers();

        while (ports.hasMoreElements())
        {
            CommPortIdentifier curPort = (CommPortIdentifier)ports.nextElement();
            if (curPort.getPortType() == CommPortIdentifier.PORT_SERIAL)
            {
                drawing_panel.cboxPorts.addItem(curPort.getName());
                m_ports.put(curPort.getName(), curPort);
            }
        }
    }

    // Make connection.
    public void connect()
    {
        String port_sel = (String)drawing_panel.cboxPorts.getSelectedItem();
        open_port = (CommPortIdentifier)m_ports.get(port_sel);

        CommPort c_port = null;

        try
        {
            c_port = open_port.open("Electric Field Sensing", TIMEOUT);
            serialPort = (SerialPort)c_port;

            setConnected(true);

            event_info = port_sel + " opened successfully.";
            drawing_panel.text_log.append(event_info + "\n");
        }
        catch (PortInUseException e)
        {
            event_info = port_sel + " is now being used by something else. (" + e.toString() + ")";
            
            drawing_panel.text_log.setForeground(Color.RED);
            drawing_panel.text_log.append(event_info + "\n");
        }
        catch (Exception e)
        {
            event_info = "Failed to open " + port_sel + "(" + e.toString() + ")";
            drawing_panel.text_log.append(event_info + "\n");
            
            drawing_panel.text_log.setForeground(Color.RED);
        }
    }
    
    // initializing input and output stream
    public boolean io_stream()
    {
        //return value for whather opening the streams is successful or not
        boolean open_streams = false;

        try {
            input = serialPort.getInputStream();
            output = serialPort.getOutputStream();
            
            
            open_streams = true;
            return open_streams;
        }
        catch (IOException e) {return open_streams;}
    }

    // figure out when data is available.
    public void initiation_listener()
    {
        try
        {
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        }
        catch (TooManyListenersException e)
        {   drawing_panel.text_log.setForeground(Color.red);
            drawing_panel.text_log.append(event_info + "\n");
        }
    }

    // Disconnect port
    public void disconnect()
    {
        //close the serial port
        try
        {
        	
            serialPort.removeEventListener();
            serialPort.close();
            input.close();
            output.close();
            setConnected(false);

            event_info = "Port has been Disconnected.";
            drawing_panel.text_log.setForeground(Color.red);
            drawing_panel.text_log.append(event_info + "\n");
        }
        catch (Exception e)
        {
            event_info = "Failed: closing" + serialPort.getName() + "(" + e.toString() + ")";
            drawing_panel.text_log.setForeground(Color.red);
            drawing_panel.text_log.append(event_info + "\n");
        }
    }

    final public boolean getConnected()
    {
        return conn;
    }

    public void setConnected(boolean conn)
    {
        this.conn = conn;
    }

    // Listen if there is any data coming from a blimp.
    // If there is, then get a byte of the data and save in to array list.
    // If we see a line break char, that means we've got one value from a sensor. 
    // Therefore, it would go to next step to draw graphs.
    public void serialEvent(SerialPortEvent evt) {
        if (evt.getEventType() == SerialPortEvent.DATA_AVAILABLE)
        {
        	if(count == 0){
        		this.list = new ArrayList<String>();
        	}
			try {
				singleData = (byte)input.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			event_info = new String(new byte[] {singleData});
			value = event_info + "";
	                    
            if(singleData != NEW_LINE){
            	count++;
            	list.add(value);
            } else {
            	
            	int size = list.size();
            	if(size == 7){
	            	for(int i = 0; i < size - 2; i++){
	            		output1 += list.get(i);
	            	}
	            	
	            	try{
	            		System.out.println("output1 " + output1);
	            		height1 = Integer.parseInt(output1);
	            	} catch (Exception e){
	            		
	            		System.out.println("ERROR1");
	            	}
	            	
	            	output1 = "";
	            	System.out.println(output1);
	            	System.out.println("test");
	            	int sensor_num = 10;
	            	
	            	try{ 
	            		System.out.println("sensor num: " + list.get(size - 1));
	            		sensor_num = Integer.parseInt(list.get(size - 1));
	            	} catch(Exception e){
	            		System.out.println("ERROR2");
	            	}
	            	
	                if(!this.inFinalMode && sensor_num == 4 && height1 < 36345 && drawing_panel.get_auto_mode()){
	                	
						this.outputData(51);
		    			System.out.flush();
		    			this.inFinalMode = true;
	            	} else if (this.inFinalMode && sensor_num == 4 && height1 >= 36347 && drawing_panel.get_auto_mode()) {
						this.outputData(44);
		    			System.out.flush();
		    			this.inFinalMode = false;
	            	}

	                drawing_graph(sensor_num, height1);
	            }
            	list.clear();
                count = 0;
            }
        }
    }
    
    // drawing graphs
    private void drawing_graph(int sensor, int value){
    	init();
    	value = value / 40;
    	if(sensor == 0){
    		g.clearRect(450, 150, 40, value/2);
    		g.setColor(Color.cyan);
            g.fill3DRect(450, 150, 40, value/2, true);
            
    	}else if (sensor == 1){
    		g.clearRect(510, 150, 40, value/2);
    		g.setColor(Color.green);
            g.fill3DRect(510, 150, 40, value/2, true);
            
    	}else if(sensor == 2){
    		g.clearRect(570, 150, 40, value/2);
    		g.setColor(Color.orange);
            g.fill3DRect(570, 150, 40, value/2, true);
            
    	}else if(sensor == 3){
    		g.clearRect(630, 150, 40, value/2);
    		g.setColor(Color.pink);
            g.fill3DRect(630, 150, 40, value/2, true);
            
    	}else if(sensor == 4){
    		g.clearRect(700, 150, 40, value/2);
    		g.setColor(Color.red);
            g.fill3DRect(700, 150, 40, value/2, true);
            
    	}
    }
    
    // This is the part sending an order to the blimp.
    public void outputData(int data){
    	try
    	{
    		output.write(data);
    		output.flush();
    	}
    	catch(Exception e){
    		event_info = "Failed to write data";
    		drawing_panel.text_log.setForeground(Color.red);
    		drawing_panel.text_log.append(event_info + "\n");
    	}
	
    }
}
