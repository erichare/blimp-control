/*
 * Donghyo Min, Eric Hare, Josh Peterson
 * 
 * This file create a drawing panel, adding buttons,
 * necessary texts, and lining up items.
 * 
 * Also, it determines size of drawing panel.
 * 
 * This program needs a library files designed for RX/TX.
 * There are many versions of the library files for each operating system and architecture.
 * To get and to see how to install library files for this program, 
 * go to http://rxtx.qbang.org/wiki/index.php/Download
 * and go to http://rxtx.qbang.org/wiki/index.php/Using_RXTX_In_Eclipse.
 */
import java.awt.Color;

public class Panel extends javax.swing.JFrame {
	final static int WIDTH = 50;
	final static int STOP = 0;
	final static int RUN = 1;
	final static int AUTO = 66;
	final static int NOT_AUTO = 67;
	final static int LEFT = 68;
	final static int RIGHT = 69;
	final static int BACK = 70;
	final static int FORWARD = 71;
	final int UP = 86;
	final int DOWN = 87;
	final static int HIGH = 72;
	final static int LOW = 73;
	final static int HIGH_LEFT = 74;
	final static int LOW_LEFT = 75;
	final static int HIGH_RIGHT = 76;
	final static int LOW_RIGHT = 77;
	final static int HIGH_MID = 78;
	final static int LOW_MID = 79;
	final static int IR1 = 53;
	final static int IR2 = 54;
	final static int IR3 = 55;
	final static int IR4 = 56;
	final static int IR5 = 57;
	final static int IR_ALL = 58;
	
	protected int[] arr = new int[20];
	protected int index = 0;
	
	private boolean mode = false;
	public boolean outputBusy = false;
	
	public javax.swing.JButton stop;
	public javax.swing.JButton run;
	public javax.swing.JButton btnConnect;
	public javax.swing.JButton btnDisconnect;
	public javax.swing.JButton auto;
	public javax.swing.JButton not_auto;
	
	public javax.swing.JLabel direction_l;
	public javax.swing.JLabel direction_r;
	public javax.swing.JLabel direction_m;
	public javax.swing.JLabel left_accel;
	public javax.swing.JLabel right_accel;
	public javax.swing.JLabel middle_accel;
	public javax.swing.JLabel duty_left;
	public javax.swing.JLabel duty_right;
	public javax.swing.JLabel duty_mid;
	public javax.swing.JButton left_bt_accel;
	public javax.swing.JButton right_bt_accel;
	public javax.swing.JButton mid_bt_accel;
	public javax.swing.JButton left_bt_decel;
	public javax.swing.JButton right_bt_decel;
	public javax.swing.JButton mid_bt_decel;
	
	public javax.swing.JButton left;
	public javax.swing.JButton right;
	public javax.swing.JButton back;
	public javax.swing.JButton forward;
	public javax.swing.JButton up;
	public javax.swing.JButton down;
	
	public javax.swing.JButton ir1;
	public javax.swing.JButton ir2;
	public javax.swing.JButton ir3;
	public javax.swing.JButton ir4;
	public javax.swing.JButton ir5;
	public javax.swing.JButton ir_all;
	
	public javax.swing.JLabel key1;
	public javax.swing.JLabel key2;
	public javax.swing.JLabel key3;
	public javax.swing.JLabel key4;
	public javax.swing.JLabel key5;
	public javax.swing.JLabel key6;
	public javax.swing.JLabel key7;
	public javax.swing.JLabel key8;
	public javax.swing.JLabel key9;
	public javax.swing.JLabel key10;
	public javax.swing.JLabel key11;
	public javax.swing.JLabel key12;
	public javax.swing.JLabel key13;
	public javax.swing.JLabel key14;
	public javax.swing.JLabel key15;
	public javax.swing.JLabel key16;
	
	
	public javax.swing.JComboBox cboxPorts;
	private javax.swing.JLabel label1;
	private javax.swing.JLabel label2;
	private javax.swing.JScrollPane panel_scroll;
	private javax.swing.JScrollPane panel_scroll1;
	private javax.swing.JTextArea jTextArea1;
	public javax.swing.JTextArea text_log;
	public javax.swing.JPanel	sensor;
	
	
    PortCommunication comm_communicator = null;
    Keyboard key_board = null;
    
    /** Creates new form GUI */
    public Panel() {
        initComponents();
        createObjects();
        comm_communicator.port_searcher();
        key_board.toggleControls();
        key_board.bindKeys();
    }

    private void createObjects()
    {
        comm_communicator = new PortCommunication(this);
        key_board = new Keyboard(this);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        panel_scroll = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        label1 = new javax.swing.JLabel();

        cboxPorts = new javax.swing.JComboBox();

        stop = new javax.swing.JButton();
        run = new javax.swing.JButton();
        btnConnect = new javax.swing.JButton();
        btnDisconnect = new javax.swing.JButton();
        auto = new javax.swing.JButton();
        not_auto = new javax.swing.JButton();
        
        direction_l = new javax.swing.JLabel();
        direction_r = new javax.swing.JLabel();
        direction_m = new javax.swing.JLabel();
        left_accel = new javax.swing.JLabel();
        right_accel = new javax.swing.JLabel();
        middle_accel = new javax.swing.JLabel();
        duty_left = new javax.swing.JLabel();
        duty_mid = new javax.swing.JLabel();
        duty_right = new javax.swing.JLabel();
        left_bt_accel = new javax.swing.JButton();
        right_bt_accel = new javax.swing.JButton();
        mid_bt_accel = new javax.swing.JButton();
        left_bt_decel = new javax.swing.JButton();
        right_bt_decel = new javax.swing.JButton();
        mid_bt_decel = new javax.swing.JButton();
        
        left = new javax.swing.JButton();
        right = new javax.swing.JButton();
        back = new javax.swing.JButton();
        forward = new javax.swing.JButton();
        up = new javax.swing.JButton();
        down = new javax.swing.JButton();
        
        ir1 = new javax.swing.JButton();
        ir2 = new javax.swing.JButton();
        ir3 = new javax.swing.JButton();
        ir4 = new javax.swing.JButton();
        ir5 = new javax.swing.JButton();
        ir_all = new javax.swing.JButton();

        key1 = new javax.swing.JLabel();
        key2 = new javax.swing.JLabel();
        key3 = new javax.swing.JLabel();
        key4 = new javax.swing.JLabel();
        key5 = new javax.swing.JLabel();
        key6 = new javax.swing.JLabel();
        key7 = new javax.swing.JLabel();
        key8 = new javax.swing.JLabel();
        key9 = new javax.swing.JLabel();
        key10 = new javax.swing.JLabel();
        key11 = new javax.swing.JLabel();
        key12 = new javax.swing.JLabel();
        key13 = new javax.swing.JLabel();
        key14 = new javax.swing.JLabel();
        key15 = new javax.swing.JLabel();
        key16 = new javax.swing.JLabel();
        
        
        
        
        label2 = new javax.swing.JLabel();
        panel_scroll1 = new javax.swing.JScrollPane();
        text_log = new javax.swing.JTextArea();

        jTextArea1.setColumns(40);
        jTextArea1.setRows(WIDTH);
        panel_scroll.setViewportView(jTextArea1);


        label1.setFont(new java.awt.Font("Tahoma", 1, 14));
        label1.setText("Real Time Information of Blimp");
        
        auto.setText("Auto");
        auto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_auto(evt);
            }
        });
        
        not_auto.setText("Not-auto");
        not_auto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_not_auto(evt);
            }
        });
        
        stop.setText("Stop");
        stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_stop(evt);
            }
        });
        
        run.setText("Run");
        run.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_run(evt);
            }
        });

        btnConnect.setText("Connect");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_conn(evt);
            }
        });

        btnDisconnect.setText("Disconnect");
        btnDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_disconn(evt);
            }
        });
        
        left.setText("left");
        left.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_left(evt);
            }
        });
        
        right.setText("right");
        right.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_right(evt);
            }
        });
        
        back.setText("back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_back(evt);
            }
        });
        
        forward.setText("forward");
        forward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_forward(evt);
            }
        });
        
        up.setText("up");
        up.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_up(evt);
            }
        });
        
        down.setText("down");
        down.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_down(evt);
            }
        });
        
        ir1.setText("IR1");
        ir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_ir1(evt);
            }
        });
        
        ir2.setText("IR2");
        ir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_ir2(evt);
            }
        });
        
        ir3.setText("IR3");
        ir3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_ir3(evt);
            }
        });
        
        ir4.setText("IR4");
        ir4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_ir4(evt);
            }
        });
        
        ir5.setText("IR5");
        ir5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_ir5(evt);
            }
        });
        
        ir_all.setText("IR ALL");
        ir_all.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_ir_all(evt);
            }
        });

        direction_l.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        direction_l.setText("FW/BACK");
        
        direction_r.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        direction_r.setText("FW/BACK");
        
        direction_m.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        direction_m.setText("FW/BACK");
        
        left_accel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        left_accel.setText("Left Fan");

        middle_accel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        middle_accel.setText("Top Fan");
        
        right_accel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        right_accel.setText("Right Fan");
        
        key1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        key1.setText("a: turn left");
        
        key2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        key2.setText("d: turn right");
        
        key3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        key3.setText("s: back");
        
        key4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        key4.setText("w: front");
        
        key5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        key5.setText("e: go up");
        
        key6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        key6.setText("c: go down");
        
        key7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        key7.setText("r: run/stand-by");
        
        key8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        key8.setText("s: stop");
        
        key9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        key9.setText("u: left fan accel");
        
        key10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        key10.setText("i: mid fan accel");
        
        key11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        key11.setText("o: right fan accel");
        
        key12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        key12.setText("j: left fan decel");
        
        key13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        key13.setText("k: mid fan decel");
        
        key14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        key14.setText("l: right fan decel");
        
        key15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        key15.setText("y: all fan accel");
        
        key16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        key16.setText("h: all fan decel");

        
        
        duty_left.setFont(new java.awt.Font("Tahoma", 1, 18));
        duty_left.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        duty_left.setText("0");
        
        duty_right.setFont(new java.awt.Font("Tahoma", 1, 18));
        duty_right.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        duty_right.setText("0");
        
        duty_mid.setFont(new java.awt.Font("Tahoma", 1, 18));
        duty_mid.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        duty_mid.setText("0");
        
        // button for accel. duty_cycle
        left_bt_accel.setText("UP");
        left_bt_accel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	left_bt_accel(evt);
            }
        });
        
        // button for decel. duty_cycle
        mid_bt_accel.setText("UP");
        mid_bt_accel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	mid_bt_accel(evt);
            }
        });
        
        right_bt_accel.setText("UP");
        right_bt_accel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	right_bt_accel(evt);
            }
        });
        
        // button for decel. duty_cycle
        left_bt_decel.setText("DOWN");
        left_bt_decel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	left_bt_decel(evt);
            }
        });
        
        mid_bt_decel.setText("DOWN");
        mid_bt_decel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	mid_bt_decel(evt);
            }
        });
        
        // button for decel. duty_cycle
        right_bt_decel.setText("DOWN");
        right_bt_decel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	right_bt_decel(evt);
            }
        });

        label2.setFont(new java.awt.Font("Tahoma", 1, 11));
        label2.setText("Graphing from Senses");

        text_log.setColumns(40);
        text_log.setEditable(false);
        text_log.setLineWrap(true);
        text_log.setRows(50);
        text_log.setFocusable(false);
        panel_scroll1.setViewportView(text_log);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cboxPorts, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnConnect)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDisconnect))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stop)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(run))
                               
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(auto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(not_auto))
                                
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(left)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(right)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(back)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(forward)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(up)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(down))   
                            
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                	.addComponent(left_accel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, Short.MAX_VALUE)	
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(middle_accel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(right_accel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, Short.MAX_VALUE)))
	                           .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
	                                    .addComponent(left_bt_decel)
	                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                                        .addComponent(duty_left, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                        .addComponent(left_bt_accel, javax.swing.GroupLayout.Alignment.CENTER)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(mid_bt_decel)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(duty_mid, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(mid_bt_accel, javax.swing.GroupLayout.Alignment.CENTER)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(right_bt_decel)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(duty_right, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(right_bt_accel, javax.swing.GroupLayout.Alignment.CENTER))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(key1)
                                    .addComponent(key2)
                                    .addComponent(key3)
                                    .addComponent(key4)
                                    .addComponent(key5)
                                    .addComponent(key6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(key7)
                                    .addComponent(key8)
                                    .addComponent(key9)
                                    .addComponent(key10)
                                    .addComponent(key11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(key12)
                                    .addComponent(key13)
                                    .addComponent(key14)
                                    .addComponent(key15)
                                    .addComponent(key16)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ir1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ir2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ir3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ir4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ir5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ir_all))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label2)
                            .addComponent(panel_scroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_scroll1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboxPorts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConnect)
                            .addComponent(btnDisconnect))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    		.addComponent(stop)
                            .addComponent(run))	
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    		.addComponent(auto)
                            .addComponent(not_auto)) 
                            
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    		.addComponent(left)
                            .addComponent(right)
                            .addComponent(back)
                            .addComponent(forward)
                            .addComponent(up)
                            .addComponent(down))  
                            
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(left_accel)
                            .addComponent(middle_accel)
                            .addComponent(right_accel))
                          
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(left_bt_accel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(duty_left)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(left_bt_decel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(right_bt_accel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(duty_right)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(right_bt_decel))
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(mid_bt_accel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(duty_mid)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(mid_bt_decel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    		.addComponent(ir1)
                            .addComponent(ir2)
                            .addComponent(ir3)
                            .addComponent(ir4)
                            .addComponent(ir5)
                            .addComponent(ir_all)) 
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGap(60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(key1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(key2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(key3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(key4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(key5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(key6))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(key7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(key8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(key9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(key10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(key11))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(key12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(key13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(key14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(key15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(key16)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        
    }
    
    // update labels and duty cycle
    void update_lable_helper(int duty_cycle){
        key_board.update_lbl(duty_cycle);
        key_board.updateLabels();
    }

    // increase duty cycle of the left fan
    private void left_bt_accel(java.awt.event.ActionEvent evt) {
        key_board.set_left_duty(key_board.accelerate(key_board.get_left_duty()));
        update_lable_helper(key_board.get_left_duty());
    }
    // decrease duty cycle of the left fan
    private void left_bt_decel(java.awt.event.ActionEvent evt) {
    	key_board.set_left_duty(key_board.decelerate(key_board.get_left_duty()));
    	update_lable_helper(key_board.get_left_duty());
    }
    // increase duty cycle of the right fan
    private void right_bt_accel(java.awt.event.ActionEvent evt) {
    	key_board.set_right_duty(key_board.accelerate(key_board.get_right_duty()));
    	update_lable_helper(key_board.get_right_duty());
    }
    // decrease duty cycle of the right fan
    private void right_bt_decel(java.awt.event.ActionEvent evt) {
    	key_board.set_right_duty(key_board.decelerate(key_board.get_right_duty()));
    	update_lable_helper(key_board.get_right_duty());
    }
    // increase duty cycle of the mid fan
    private void mid_bt_accel(java.awt.event.ActionEvent evt) {
    	key_board.set_mid_duty(key_board.accelerate(key_board.get_mid_duty()));
    	update_lable_helper(key_board.get_mid_duty());
    }
    // decrease duty cycle of the mid fan
    private void mid_bt_decel(java.awt.event.ActionEvent evt) {
    	key_board.set_mid_duty(key_board.decelerate(key_board.get_mid_duty()));
    	update_lable_helper(key_board.get_mid_duty());
    }
    // define a behavior of each commmand
    private void run_command(int command) throws InterruptedException {
    	if (command == STOP) {
	    	key_board.set_left_duty(10);
			key_board.set_right_duty(27);
			key_board.set_mid_duty(44);
			key_board.updateLabels();
			comm_communicator.outputData(STOP);
    	} else if (command == RUN) {
    		key_board.set_left_duty(10);
    		key_board.set_right_duty(27);
    		key_board.set_mid_duty(44);
    		update_lable_helper(key_board.get_left_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_right_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_mid_duty());
    		
    		comm_communicator.outputData(RUN);
    	} else if (command == LEFT) {
    		key_board.set_left_duty(key_board.decelerate(key_board.get_left_duty()));
    		key_board.set_right_duty(key_board.accelerate(key_board.get_right_duty()));
    		update_lable_helper(key_board.get_right_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_left_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_right_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_left_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_right_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_left_duty());
    		
    		Thread.sleep(100);
    		
    		key_board.set_left_duty(key_board.accelerate(key_board.get_left_duty()));
    		key_board.set_right_duty(key_board.decelerate(key_board.get_right_duty()));
    		update_lable_helper(key_board.get_right_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_left_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_right_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_left_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_right_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_left_duty());
    	} else if (command == RIGHT) {
    		key_board.set_left_duty(key_board.accelerate(key_board.get_left_duty()));
    		key_board.set_right_duty(key_board.decelerate(key_board.get_right_duty()));
    		update_lable_helper(key_board.get_right_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_left_duty());
    		Thread.sleep(10);
    		
    		update_lable_helper(key_board.get_right_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_left_duty());
    		Thread.sleep(10);
    		
    		update_lable_helper(key_board.get_right_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_left_duty());
    		Thread.sleep(10);
    	} else if (command == BACK) {
    		key_board.set_left_duty(key_board.decelerate(key_board.get_left_duty()));
    		key_board.set_right_duty(key_board.decelerate(key_board.get_right_duty()));
    		update_lable_helper(key_board.get_right_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_left_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_mid_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_right_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_left_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_mid_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_right_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_left_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_mid_duty());
    		Thread.sleep(10);
    	} else if (command == FORWARD) {
    		key_board.set_left_duty(key_board.accelerate(key_board.get_left_duty()));
    		key_board.set_right_duty(key_board.accelerate(key_board.get_right_duty()));
    		update_lable_helper(key_board.get_left_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_right_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_mid_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_left_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_right_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_mid_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_left_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_right_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_mid_duty());
    		Thread.sleep(10);
    	} else if (command == UP) {
    		key_board.set_left_duty(10);
    		key_board.set_right_duty(27);
    		key_board.set_mid_duty(48);
    		
    		update_lable_helper(key_board.get_right_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_left_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_mid_duty());
    		Thread.sleep(10);
    		
    		update_lable_helper(key_board.get_right_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_left_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_mid_duty());
    		Thread.sleep(10);
    		
    		update_lable_helper(key_board.get_right_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_left_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_mid_duty());
    		Thread.sleep(10);
    	} else if (command == DOWN) {
    		key_board.set_left_duty(10);
    		key_board.set_right_duty(27);
    		key_board.set_mid_duty(40);
  
    		update_lable_helper(key_board.get_right_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_left_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_mid_duty());
    		Thread.sleep(10);
    		
    		update_lable_helper(key_board.get_right_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_left_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_mid_duty());
    		Thread.sleep(10);
    		
    		update_lable_helper(key_board.get_right_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_left_duty());
    		Thread.sleep(10);
    		update_lable_helper(key_board.get_mid_duty());
    		Thread.sleep(10);
    	} else if (command == IR1) {
    		comm_communicator.outputData(IR1);
    	} else if (command == IR2) {
    		comm_communicator.outputData(IR2);
    	} else if (command == IR3) {
    		comm_communicator.outputData(IR3);
    	} else if (command == IR4) {
    		comm_communicator.outputData(IR4);
    	} else if (command == IR5) {
    		comm_communicator.outputData(IR5);
    	} else if (command == IR_ALL) {
    		comm_communicator.outputData(IR1);
    		Thread.sleep(100);
    		comm_communicator.outputData(IR2);
    		Thread.sleep(100);
    		comm_communicator.outputData(IR3);
    		Thread.sleep(100);
    		comm_communicator.outputData(IR4);
    		Thread.sleep(100);
    		comm_communicator.outputData(IR5);
    		Thread.sleep(100);

    		comm_communicator.outputData(IR1);
    		Thread.sleep(100);
    		comm_communicator.outputData(IR2);
    		Thread.sleep(100);
    		comm_communicator.outputData(IR3);
    		Thread.sleep(100);
    		comm_communicator.outputData(IR4);
    		Thread.sleep(100);
    		comm_communicator.outputData(IR5);
    		Thread.sleep(100);
    		
    		comm_communicator.outputData(IR1);
    		Thread.sleep(100);
    		comm_communicator.outputData(IR2);
    		Thread.sleep(100);
    		comm_communicator.outputData(IR3);
    		Thread.sleep(100);
    		comm_communicator.outputData(IR4);
    		Thread.sleep(100);
    		comm_communicator.outputData(IR5);
    	} else if (command == AUTO) {
    		set_mode(true);
    	} else if (command == 1000 && mode){
    		comm_communicator.outputData(IR4);
    	}
    }

    // for stop button.
    private void bttn_stop(java.awt.event.ActionEvent evt){
    	if (comm_communicator.io_stream() == true)
        {
			arr[index] = STOP;
			incIndex();
        }
    }
    
    // for run button
    private void bttn_run(java.awt.event.ActionEvent evt){
    	if (comm_communicator.io_stream() == true)
        {
    		arr[index] = RUN;
    		incIndex();
        }
    }
    
    // for auto altitude mode
    private void bttn_auto(java.awt.event.ActionEvent evt){
    	if (comm_communicator.io_stream() == true)
        {
    		arr[index] = AUTO;
    		incIndex();
        }
    }
    
    // return the mode: either auto or not auto
    public boolean get_auto_mode(){    	
    	return mode;
    }
    
    public void set_mode(boolean mode){
    	this.mode = mode;
    }
    
    private void bttn_not_auto(java.awt.event.ActionEvent evt){
    	if (comm_communicator.io_stream() == true)
        {
    		set_mode(false);
        }
    }
    
    private void bttn_left(java.awt.event.ActionEvent evt){
    	if (comm_communicator.io_stream() == true)
        {
    		arr[index] = LEFT;
    		incIndex();
        }
    }
    
    private void bttn_right(java.awt.event.ActionEvent evt){
    	if (comm_communicator.io_stream() == true)
        {
    		arr[index] = RIGHT;
    		incIndex();
        }
    }
    
    protected void incIndex() {
    	index++;
		if (index == 20) {
			index = 0;
		}
    }
    
    private void bttn_back(java.awt.event.ActionEvent evt){
    	if (comm_communicator.io_stream() == true)
        {
    		arr[index] = BACK;
			incIndex();
        }
    }
    
    private void bttn_forward(java.awt.event.ActionEvent evt){
    	if (comm_communicator.io_stream() == true)
        {
    		arr[index] = FORWARD;
			incIndex();   		
        }
    }
    
    private void bttn_up(java.awt.event.ActionEvent evt){
    	if (comm_communicator.io_stream() == true)
        {
    		arr[index] = UP;
			incIndex(); 
        }
    }
    
    private void bttn_down(java.awt.event.ActionEvent evt){
    	if (comm_communicator.io_stream() == true)
        {
    		arr[index] = DOWN;
			incIndex(); 
        }
    }
    
    private void bttn_ir1(java.awt.event.ActionEvent evt){
    	if (comm_communicator.io_stream() == true)
        {
    		arr[index] = IR1;
			incIndex(); 
        }
    }
    
    private void bttn_ir2(java.awt.event.ActionEvent evt){
    	if (comm_communicator.io_stream() == true)
        {
    		arr[index] = IR2;
			incIndex(); 
        }
    }
    
    private void bttn_ir3(java.awt.event.ActionEvent evt){
    	if (comm_communicator.io_stream() == true)
        {
    		arr[index] = IR3;
			incIndex(); 
        }
    }
    
    private void bttn_ir4(java.awt.event.ActionEvent evt){
    	if (comm_communicator.io_stream() == true)
        {
    		arr[index] = IR4;
			incIndex(); 
        }
    }
    
    private void bttn_ir5(java.awt.event.ActionEvent evt){
    	if (comm_communicator.io_stream() == true)
        {
    		arr[index] = IR5;
			incIndex(); 
        }
    }
    
    private void bttn_ir_all(java.awt.event.ActionEvent evt){
    	if (comm_communicator.io_stream() == true)
        {
    		arr[index] = IR_ALL;
			incIndex(); 
        }
    }
  
    // button connection.
    private void bttn_conn(java.awt.event.ActionEvent evt) {
        comm_communicator.connect();
        if (comm_communicator.getConnected() == true)
        {
            if (comm_communicator.io_stream() == true)
            {
                comm_communicator.initiation_listener();
            }
        }
    }

    // button disconnection
    private void bttn_disconn(java.awt.event.ActionEvent evt) {
        comm_communicator.disconnect();
    }
  
    // main loop.
    // once the program is run, it keeps looping.
    // and checking if there is any event.
    public static void main(String args[]) throws InterruptedException {
    	final Panel p = new Panel();
    	// event queue. First event is going to be executed first
        java.awt.EventQueue.invokeLater(new Runnable() {
        	public void run() {
		        p.setVisible(true);
        	}
        });
        int cnt = 0;
        int index2 = 0;
        while (true) {
        
        	if (p.index != index2) {
	        	int command = p.arr[index2];
	        	p.run_command(command);
	        	index2++;
	        	if (index2 == 20) {
	        		index2 = 0;
	        	}
	        	System.out.flush();
        	} 
        
        	if (p.get_auto_mode()) {
        		cnt++;
        		if(cnt == 100){
        			p.ir5.doClick();
        			cnt = 0;
        		}
        	}
        	Thread.sleep(10);
        	
        }
    }
}
