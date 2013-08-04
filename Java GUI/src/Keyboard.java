/*
 * Donghyo Min, Eric Hare, Josh Peterson.
 * 
 * This file make a binding with button.
 * So, clicking a button with a mouse and press a button are going to have the same effect.
 */


import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.KeyStroke;

public class Keyboard {
    Panel window = null;

    private int left_duty = 10;
    private int right_duty = 27;
    private int mid_duty = 44;

    // variable to decide how much increment or decrement
    private static final int duty_unit = 1;	

    // keys on keyboard for each function
    private static char left_bt_accel = 'u';
    private static char left_bt_decel = 'j';
    private static char right_bt_accel = 'o';
    private static char right_bt_decel = 'l';
    private static char mid_bt_accel = 'i';
    private static char mid_bt_decel = 'k';
    private static char all_bt_accel = 'y';
    private static char all_bt_decel = 'h';
    
    private static char left = 'a';
    private static char right = 'd';
    private static char back = 's';
    private static char forward = 'w';
    private static char up = 'e';
    private static char down = 'c';
    
    private static char run = 'r';
    private static char stop = 'f';

    
    public Keyboard(Panel window)
    {
        this.window = window;
    }

    public void bindKeys()
    {
    	// This enables the program to read key bindings.
        window.left_bt_accel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(left_bt_accel), "left_accel");
        window.left_bt_accel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Character.toUpperCase(left_bt_accel)), "left_accel");
        window.left_bt_accel.getActionMap().put("left_accel", left_accel);

        window.left_bt_decel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(left_bt_decel), "left_decel");
        window.left_bt_decel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Character.toUpperCase(left_bt_decel)), "left_decel");
        window.left_bt_decel.getActionMap().put("left_decel", left_decel);

        window.right_bt_accel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(right_bt_accel), "right_accel");
        window.right_bt_accel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Character.toUpperCase(right_bt_accel)), "right_accel");
        window.right_bt_accel.getActionMap().put("right_accel", right_accel);

        window.right_bt_decel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(right_bt_decel), "right_decel");
        window.right_bt_decel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Character.toUpperCase(right_bt_decel)), "right_decel");
        window.right_bt_decel.getActionMap().put("right_decel", right_decel);

        window.mid_bt_accel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(mid_bt_accel), "mid_accel");
        window.mid_bt_accel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Character.toUpperCase(mid_bt_accel)), "mid_accel");
        window.mid_bt_accel.getActionMap().put("mid_accel", mid_accel);

        window.mid_bt_decel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(mid_bt_decel), "mid_decel");
        window.mid_bt_decel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Character.toUpperCase(mid_bt_decel)), "mid_decel");
        window.mid_bt_decel.getActionMap().put("mid_decel", mid_decel);
        
        window.mid_bt_accel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(all_bt_accel), "all_accel");
        window.mid_bt_accel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Character.toUpperCase(all_bt_accel)), "all_accel");
        window.mid_bt_accel.getActionMap().put("all_accel", all_accel);

        window.mid_bt_decel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(all_bt_decel), "all_decel");
        window.mid_bt_decel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Character.toUpperCase(all_bt_decel)), "all_decel");
        window.mid_bt_decel.getActionMap().put("all_decel", all_decel);
        
        window.mid_bt_decel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(left), "left_turn");
        window.mid_bt_decel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Character.toUpperCase(left)), "left_turn");
        window.mid_bt_decel.getActionMap().put("left_turn", left_turn);
        
        window.mid_bt_decel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(right), "right_turn");
        window.mid_bt_decel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Character.toUpperCase(right)), "right_turn");
        window.mid_bt_decel.getActionMap().put("right_turn", right_turn);
        
        window.mid_bt_decel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(forward), "forward_turn");
        window.mid_bt_decel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Character.toUpperCase(forward)), "forward_turn");
        window.mid_bt_decel.getActionMap().put("forward_turn", forward_turn);
        
        window.mid_bt_decel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(back), "back_turn");
        window.mid_bt_decel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Character.toUpperCase(back)), "back_turn");
        window.mid_bt_decel.getActionMap().put("back_turn", back_turn);
        
        window.mid_bt_decel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(up), "up_turn");
        window.mid_bt_decel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Character.toUpperCase(up)), "up_turn");
        window.mid_bt_decel.getActionMap().put("up_turn", up_turn);
        
        window.mid_bt_decel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(down), "down_turn");
        window.mid_bt_decel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Character.toUpperCase(down)), "down_turn");
        window.mid_bt_decel.getActionMap().put("down_turn", down_turn);
        
        window.mid_bt_decel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(run), "run_t");
        window.mid_bt_decel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Character.toUpperCase(run)), "run_t");
        window.mid_bt_decel.getActionMap().put("run_t", run_t);
        
        window.mid_bt_decel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(stop), "stop_t");
        window.mid_bt_decel.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Character.toUpperCase(stop)), "stop_t");
        window.mid_bt_decel.getActionMap().put("stop_t", stop_t);
    }
    
    // enables all the keys.
    public void toggleControls()
    {
        window.left_bt_accel.setEnabled(true);
        window.left_bt_decel.setEnabled(true);
        window.right_bt_accel.setEnabled(true);
        window.right_bt_decel.setEnabled(true);
        window.mid_bt_accel.setEnabled(true);
        window.mid_bt_decel.setEnabled(true);
        
        window.left.setEnabled(true);
        window.right.setEnabled(true);
        window.forward.setEnabled(true);
        window.back.setEnabled(true);
        window.up.setEnabled(true);
        window.down.setEnabled(true);
        
        window.run.setEnabled(true);
        window.stop.setEnabled(true);
        
        window.btnDisconnect.setEnabled(true);
        window.btnConnect.setEnabled(true);
        window.cboxPorts.setEnabled(true);
    }

    // update the condition
    protected void update_lable_helper_keyboard(int duty_cycle){
    	// It updates the duty cycle.
        update_lbl(duty_cycle);
        // updates lables
        updateLabels();
    }
    
    // for 'run' key is pushed
    Action run_t = new AbstractAction()
    {
        public void actionPerformed(ActionEvent evt)
        {
			set_left_duty(10);
			set_right_duty(27);
			set_mid_duty(44);
					
			
			update_lable_helper_keyboard(get_left_duty());
    		update_lable_helper_keyboard(get_right_duty());
    		update_lable_helper_keyboard(get_mid_duty());
        	
            window.comm_communicator.outputData(1);
        }
    };
    // for 'stop' key is pushed
    Action stop_t = new AbstractAction()
    {
        public void actionPerformed(ActionEvent evt)
        {
			set_left_duty(10);
			set_right_duty(27);
			set_mid_duty(44);
    		
        	window.duty_left.setText(String.valueOf(0));
        	window.duty_right.setText(String.valueOf(0));
        	window.duty_mid.setText(String.valueOf(0));
            window.comm_communicator.outputData(0);
        }
    };
    
    // for 'left turn' key is pushed
    Action left_turn = new AbstractAction()
    {
        public void actionPerformed(ActionEvent evt)
        {
            left_duty = decelerate(left_duty);
            right_duty = accelerate(right_duty);
            update_lable_helper_keyboard(left_duty);
            update_lable_helper_keyboard(right_duty);
        }
    };
    
    // for 'right turn' key is pushed
    Action right_turn = new AbstractAction()
    {
        public void actionPerformed(ActionEvent evt)
        {
            left_duty = accelerate(left_duty);
            right_duty = decelerate(right_duty);
            update_lable_helper_keyboard(right_duty);
            update_lable_helper_keyboard(left_duty);
        }
    };
    
    // for 'forward' key is pushed
    Action forward_turn = new AbstractAction()
    {
        public void actionPerformed(ActionEvent evt)
        {
        	left_duty = accelerate(left_duty);
            right_duty = accelerate(right_duty);
            update_lable_helper_keyboard(left_duty);
            update_lable_helper_keyboard(right_duty);
        }
    };
    
    // for 'back' key is pushed
    Action back_turn = new AbstractAction()
    {
    	public void actionPerformed(ActionEvent evt)
        {
    		left_duty = decelerate(left_duty);
            right_duty = decelerate(right_duty);
        	update_lable_helper_keyboard(left_duty);
        	update_lable_helper_keyboard(right_duty);
        }
    };
    
    // for 'up' key is pushed
    Action up_turn = new AbstractAction()
    {
        public void actionPerformed(ActionEvent evt)
        {
            left_duty = 10; 
            right_duty = 27;
            
            mid_duty = 48;
            
            update_lable_helper_keyboard(mid_duty);
            update_lable_helper_keyboard(right_duty);
            update_lable_helper_keyboard(left_duty);
        }
    };
    
    // for 'down' key is pushed
    Action down_turn = new AbstractAction()
    {
        public void actionPerformed(ActionEvent evt)
        {
        	left_duty = 10; 
            right_duty = 27;
            
            mid_duty = 40;
           
            update_lable_helper_keyboard(mid_duty);
            update_lable_helper_keyboard(right_duty);
            update_lable_helper_keyboard(left_duty);
        }
    };
    
    // for 'left fan accel' key is pushed
    Action left_accel = new AbstractAction()
    {
        public void actionPerformed(ActionEvent evt)
        {
            left_duty = accelerate(left_duty);
         
            update_lable_helper_keyboard(left_duty);
        }
    };

    // for 'left fan deccel' key is pushed
    Action left_decel = new AbstractAction()
    {
        public void actionPerformed(ActionEvent evt)
        {
        	left_duty = decelerate(left_duty);
        	update_lable_helper_keyboard(left_duty);
        }
    };

    // for 'right fan accel' key is pushed
    Action right_accel = new AbstractAction()
    {
        public void actionPerformed(ActionEvent evt)
        {
            right_duty = accelerate(right_duty);
            update_lable_helper_keyboard(right_duty);
        }
    };

    // for 'right fan decel' key is pushed
    Action right_decel = new AbstractAction()
    {
        public void actionPerformed(ActionEvent evt)
        {
            right_duty = decelerate(right_duty);
            update_lable_helper_keyboard(right_duty);
        }
    };

    // for 'mid fan accel' key is pushed
    Action mid_accel = new AbstractAction()
    {
        public void actionPerformed(ActionEvent evt)
        {
            mid_duty = accelerate(mid_duty);
            update_lable_helper_keyboard(mid_duty);
        }
    };

    // for 'mid fan decel' key is pushed
    Action mid_decel = new AbstractAction()
    {
        public void actionPerformed(ActionEvent evt)
        {
            mid_duty = decelerate(mid_duty);
            update_lable_helper_keyboard(mid_duty);
        }
    };
    
    // for 'all fan accel' key is pushed
    Action all_accel = new AbstractAction()
    {
        public void actionPerformed(ActionEvent evt)
        {
            left_duty = accelerate(left_duty);
            right_duty = accelerate(right_duty);
            mid_duty = accelerate(mid_duty);
            update_lable_helper_keyboard(mid_duty);
            update_lable_helper_keyboard(right_duty);
            update_lable_helper_keyboard(left_duty);
        }
    };

    // for 'all fan decel' key is pushed
    Action all_decel = new AbstractAction()
    {
        public void actionPerformed(ActionEvent evt)
        {
            left_duty = decelerate(left_duty);
            right_duty = decelerate(right_duty);
            mid_duty= decelerate(mid_duty);
            update_lable_helper_keyboard(mid_duty);
            update_lable_helper_keyboard(right_duty);
            update_lable_helper_keyboard(left_duty);
        }
    };
    
    // update lable
    public void updateLabels()
    {
    	int left = get_left_duty();
        int right = get_right_duty();
    	int mid = get_mid_duty();
    	        
        if(left>= 2 && left<= 18){
        	left -= 10;
        }
        
        if(right >= 19 && right <= 35){
        	right -= 27;    	        
        }
        
        if(mid >= 36 && mid <= 52){
					mid -= 44;
        }
        
        window.duty_left.setText(String.valueOf(left));
        window.duty_right.setText(String.valueOf(right));
        window.duty_mid.setText(String.valueOf(mid));
    }

    // update duty cycle
    protected void update_lbl(int duty_cycle){
    	window.comm_communicator.outputData(duty_cycle);
    }
    
    // for accelerate a fan
    public int accelerate(int duty)
    {	
    	int max_duty = 10;
    	if(duty >= 2 && duty <= 18){
    		max_duty = 18;
    	} else if(duty >= 19 && duty <= 35){
    		max_duty = 35;
    	} else if(duty >= 36 && duty <= 52){
    		max_duty = 52;
    	}
    	
        if (duty < max_duty) {
            duty += duty_unit;
        }
    	
        return duty;
    }

    // for decelerate a fan
    public int decelerate(int duty)
    {
    	int min_duty = 2;
    	if(duty >= 2 && duty <= 18){
    		min_duty = 2;
    	} else if(duty >= 19 && duty <= 35){
    		min_duty = 19;
    	} else if(duty >= 36 && duty <= 52){
    		min_duty = 36;
    	}
    	
        if (duty > min_duty){
            duty -= duty_unit;
        }
        return duty;
    }

    final public int get_left_duty()
    {
        return left_duty;
    }

    public void set_left_duty(int value)
    {
        left_duty = value;
    }

    final public int get_right_duty()
    {
        return right_duty;
    }

    public void set_right_duty(int value)
    {
        right_duty = value;
    }
    
    final public int get_mid_duty()
    {
        return mid_duty;
    }

    public void set_mid_duty(int value)
    {
        mid_duty = value;
    }
}
