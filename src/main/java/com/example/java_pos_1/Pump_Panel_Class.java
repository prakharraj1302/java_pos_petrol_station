package com.example.java_pos_1;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

class Pane_Manager_Class{

	static JFrame main_frame ;
	static JPanel main_panel;
	static CardLayout clay;

	static void run_panel() {

		Pump_Panel_Class.initialize_pump_panel();
		Admin_Panel_Class.initialize_main_panel();
		Pane_Manager_Class.initialize_main_frame();

		Pump_Panel_Class.listen_pump_panel();
		Admin_Panel_Class.listen_admin_panel();

		//		EventQueue.invokeLater(new Runnable() {
		//			public void run() {
		//				try {
		//					Pump_Panel_Class.initialize_pump_panel();
		//					Admin_Panel_Class.initialize_main_panel();
		//					Pane_Manager_Class.initialize_main_frame();
		//					
		//					Pump_Panel_Class.listen_pump_panel();
		//					Admin_Panel_Class.listen_admin_panel();
		//				} catch (Exception e) {
		//					e.printStackTrace();
		//				}
		//			}
		//		});

	}


	static void initialize_main_frame() {




		main_frame = new JFrame() ;

		main_frame.setLocation(700, 0);;;
		main_frame.setSize(450,500);
		//main_frame.setBounds(100, 100, 450, 500);

		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main_frame.setTitle("PUMP");

		clay = new CardLayout();

		main_panel = new JPanel();
		//main_panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		//main_panel.setLayout(new CardLayout());
		main_panel.setLayout(clay);
		main_panel.add(Pump_Panel_Class.pump_panel);
		main_panel.add(Admin_Panel_Class.admin_panel);


		//main_frame.pack();
		main_frame.setContentPane(main_panel);
		main_frame.setVisible(true);

		//return main_frame;
	}

}

class Pump_Panel_Class {
	private static int amt;
	private static boolean type;

	public static JPanel pump_panel;
	private static JButton petrolb;
	private static JButton dieselb;
	private static JButton cont;
	private static JButton admin_b;
	private static JButton r1;
	private static JSlider slider;


	static  void initialize_pump_panel() {


		pump_panel = new JPanel();
		pump_panel.setOpaque(true);
		pump_panel.setBackground(Color.decode("#2EB086"));
		pump_panel.setLayout(Pane_Manager_Class.clay);
		//pump_panel.setLayout(new Cardlayout);
		//pump_panel.setBackground(new Color(255, 255, 255));
		//pump_panel.setTitle("Petrol Pump");
		//pump_panel.setBounds(100, 100, 450, 500);
		//pump_panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//pump_panel.getContentPane().setLayout(null);

		petrolb = new JButton("<HTML><h1> Petrol <br>" + Petrol.Rate + "/L </h1></html>");
		admin_b = new JButton("");
		cont = new JButton("<HTML><h2>SELECT ONE FUEL</h2></html>");
		dieselb = new JButton("<HTML><h1> Diesel <br>" + Diesel.Rate + "/L </h1></html>");
		r1 = new JButton("RESET");



		slider = new JSlider();
		//slider.setOpaque(false);
		slider.setMinimum(100);
		slider.setOrientation(SwingConstants.VERTICAL);
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(100);
		slider.setMajorTickSpacing(500);
		slider.setMaximum(5100);
		slider.setForeground(Color.red);
		//slider.setBackground();


		slider.setBounds(336, 5, 108, 460);
		slider.setEnabled(false);

		admin_b.setBounds(270, 369, 62, 96);
		r1.setBounds(270, 6, 62, 361);
		cont.setBounds(6, 369, 264, 96);
		dieselb.setBounds(6, 186, 264, 181);
		petrolb.setBounds(6, 6, 264, 181);


		pump_panel.add(petrolb);
		pump_panel.add(dieselb);
		pump_panel.add(slider);
		pump_panel.add(cont);
		pump_panel.add(admin_b);
		pump_panel.add(r1);

	}

	static  void listen_pump_panel() {

		petrolb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				slider.setEnabled(true);
				dieselb.setEnabled(false);
				dieselb.setVisible(false);
				petrolb.setBounds(6,6,264,362);
				cont.setText("<HTML><h1><center>CONFIRM </center></h> <p> " +val_print(1)+ "</html>");

			}
		});
		dieselb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				slider.setEnabled(true);
				petrolb.setEnabled(false);
				petrolb.setVisible(false);
				dieselb.setBounds(6,6,264,362);
				cont.setText("<HTML><h1><center>CONFIRM </center></h> <p> " +val_print(1)+ "</html>");
			}
		});

		r1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				petrolb.setBounds(6, 6, 264, 181);
				dieselb.setBounds(6, 186, 264, 181);
				slider.setValue(100);
				slider.setEnabled(false);
				petrolb.setText("<HTML><h1> Petrol <br>" + Petrol.Rate + "/L </h1></html>");
				dieselb.setText("<HTML><h1> Diesel <br>" + Diesel.Rate + "/L </h1></html>");

				if (petrolb.isEnabled()) {
					dieselb.setEnabled(true);
					dieselb.setVisible(true);
				}
				if (dieselb.isEnabled()) {
					petrolb.setEnabled(true);
					petrolb.setVisible(true);
				}
				cont.setText("<HTML><h2>SELECT ONE FUEL</h2></html>");
				cont.setEnabled(true);
			}
		});

		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (!slider.getValueIsAdjusting()) {

					slider.setEnabled(true);
					int x = (int)slider.getValue();
					amt = x;

					if((petrolb.isEnabled() ^ dieselb.isEnabled())) {
						if(petrolb.isEnabled()) {

							cont.setText( "<HTML><h1><center>CONFIRM </center></h> <p> " 
									+val_print(1)+ "</html>" );
							System.out.println(x);	

							petrolb.setText("<HTML><h1> Petrol <br>" 
									+ val_print(1) +" L </h1></html>");
						}
						else {
							cont.setText( "<HTML><h1><center>CONFIRM </center></h> <p> " 
									+val_print(0)+ "</html>" );
							System.out.println(x);
							dieselb.setText("<HTML><h1> Diesel <br>" 
									+ val_print(0) +" L </h1></html>");
						}
					}
				}
			}
		});

		cont.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!(petrolb.isEnabled() ^ dieselb.isEnabled())){

					cont.setText("<HTML><h2>SELECT ONE FUEL</h2></html>");

				}

				if ((petrolb.isEnabled() ^ dieselb.isEnabled())) {

					if (petrolb.isEnabled()) {
						cont.setText("<HTML><h1><center>CONFIRM </center></h> <p> " +"\n AMOUNT " +
								amt + " CAP " + (amt / Petrol.Rate)+ "</html>");
						type = true;
					}
					else {
						cont.setText("<HTML><h1><center>CONFIRM </center></h> <p> " +"\n AMOUNT " +
								amt + " CAP " + (amt / Diesel.Rate)+ "</html>");
						type = false;
					}

					data_import();
					cont.setEnabled(false);
				}
				else {
					cont.setText("<HTML><h2>SELECT ONE FUEL</h2></html>");
				}
			}
		});

		admin_b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Pane_Manager_Class.clay.next(Pane_Manager_Class.main_panel);
			}
		});
	}

	static String val_print(int x) {

		switch (x) {
		case 1:
			return("\n "+ (Character.toString((char)0x20B9) + amt 
					+ " <br> " + Admin.approx.apply((amt / Petrol.Rate))));

		default:
			return("\n " + (Character.toString((char)0x20B9)+amt 
					+ " <br> " + Admin.approx.apply((amt / Petrol.Rate))));
		}
	}

	static void data_import() {

		Admin.db_update(amt, type , (Instant.now().toString()) ) ;
	}
}

class Admin_Panel_Class{

	public static JPanel admin_panel;
	public static DefaultTableModel tmod;
	public static DefaultTableModel tmod_2;

	private static JButton okb;
	private static JButton backb;
	private static JButton showtabb;
	private static JLabel panelnamel;
	private static JLabel passwordl;
	private static JLabel anamel;
	private static JPasswordField passf;
	private static JTable dbtab;
	private static JScrollPane scpane;
	private static JTable cap_tab;
	private static JScrollPane scpane_2;


	static void initialize_main_panel() {

		admin_panel = new JPanel();
		admin_panel.setOpaque(true);
		admin_panel.setBackground(Color.decode("#1572A1"));
		admin_panel.setLayout(Pane_Manager_Class.clay);

		panelnamel = new JLabel("<HTML><h1> ADMIN PANEL </h1></html>");
		passwordl = new JLabel("ENTER PASSWORD - ");
		anamel = new JLabel("<HTML><h3> ADMIN NAME - " + Admin.name + "</h3></html>");
		okb = new JButton("OK");
		backb = new JButton("BACK");
		passf = new JPasswordField();
		showtabb = new JButton("SHOW DATABASE");
		showtabb.setEnabled(false);

		passwordl.setBounds(16, 76, 132, 26);
		anamel.setBounds(16, 48, 265, 16);
		panelnamel.setBounds(16, 6, 219, 32);
		okb.setBounds(350, 77, 94, 26);
		backb.setBounds(327, 435, 117, 29);
		showtabb.setBounds(6, 114, 171, 29);
		passf.setBounds(160, 76, 163, 26);

		tmod = new  DefaultTableModel();
		Vector<String> col = new Vector<String>();
		col.addAll(Arrays.asList(Admin.column_head));

		tmod.setColumnCount(3);
		tmod.setColumnIdentifiers(col);

		dbtab = new JTable(tmod);
		TableColumnModel tcolmod = dbtab.getColumnModel();

		dbtab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tcolmod.getColumn(0).setPreferredWidth(30);
		tcolmod.getColumn(1).setPreferredWidth(50);
		tcolmod.getColumn(2).setPreferredWidth(100);
		tcolmod.getColumn(3).setPreferredWidth(240);

		dbtab.setVisible(false);
		scpane = new JScrollPane(dbtab);
		scpane.setBounds(16, 150, 420, 200);

		//----------------------------------------------------cap_table
		tmod_2 = new DefaultTableModel();
		Vector<String> col_2 = new Vector<String>();
		col_2.addAll(Arrays.asList(new String[] {"PETROL","DIESEL"}));
		tmod_2.setColumnCount(2);
		tmod_2.setColumnIdentifiers(col_2);

		cap_tab = new JTable(tmod_2);
		cap_tab.setVisible(false);
		scpane_2 = new JScrollPane(cap_tab);
		scpane_2.setBounds(16,360,420,50);


		admin_panel.add(passwordl);
		admin_panel.add(anamel);
		admin_panel.add(panelnamel);
		admin_panel.add(passf);
		admin_panel.add(backb);
		admin_panel.add(okb);
		admin_panel.add(showtabb);
		admin_panel.add(scpane);
		admin_panel.add(scpane_2);

	}
	static void listen_admin_panel() {

		okb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(passf.getPassword());
				//char[] input = passf.getPassword();
				if (Admin.isPasswordCorrect(passf.getPassword())) {
					showtabb.setEnabled(true);
					JOptionPane.showMessageDialog(admin_panel,
							"Success! You typed the right password.");
				} else {
					JOptionPane.showMessageDialog(admin_panel,
							"Invalid password. Try again.",
							"Error Message",
							JOptionPane.ERROR_MESSAGE);
				}
				passf.setText("");
			}
		});

		showtabb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dbtab.setVisible(true);
				cap_tab.setVisible(true);
				Admin.db_export();
			}
		});

		backb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dbtab.setVisible(false);
				showtabb.setEnabled(false);
				cap_tab.setVisible(false);
				Pane_Manager_Class.clay.next(Pane_Manager_Class.main_panel);
			}
		});
	}
}
