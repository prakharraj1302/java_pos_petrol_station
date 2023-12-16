package com.example.java_pos_1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

public class Admin {

	private static char[] pass;
	static String name;
	static String[] column_head = new String[] { "ID", "TYPE", "AMT", "TIME" };
	static ArrayList<Database> db = new ArrayList<Database>();
	Database[] bdarr;

	// ---------=------------------------------------------------------
	static DecimalFormat df = new DecimalFormat("#.00");
	static public Function<Double, Double> approx = x -> Double.valueOf(df.format(x));

	// ---------=------------------------------------------------------

	public Admin(char[] pass, String name) {
		super();
		Admin.pass = pass;
		Admin.name = name;
	}

	public char[] getPass() {
		return Admin.pass;
	}

	public void setPass(char[] pass) {
		Admin.pass = pass;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		Admin.name = name;
	}

	static boolean isPasswordCorrect(char[] input) {
		boolean isCorrect = true;
		char[] correctPassword = pass;

		System.out.println(correctPassword);
		System.out.println("real");
		System.out.println(input);

		if (input.length != correctPassword.length) {
			isCorrect = false;
		} else {
			isCorrect = Arrays.equals(input, correctPassword);
		}

		// Arrays.fill(correctPassword,'0');

		return isCorrect;
	}

	static void setup() {
		new Admin(new char[] { 'a', 'd', 'm', 'i', 'n', '1' }, "admin1");
		get_current_prices();
	}

	static void get_current_prices() {

		// get price from web - by scrapping prices from BankBazaar Site.
		// on exception - set price to default
		try {
			Pump.set_rate_current(Extract.get_price());
			System.err.println("price extracted");

		} catch (Exception e) {
			//
			System.out.println("Excepition in Price Extraction - " + e);
			Pump.set_default_rate();

		}

	}

	static void db_update(int amount, boolean type, String time) {

		Pump.capacity_update(amount, type);
		db.add(new Database(Database.getCounter(), amount, type_conv(type), time));
		System.out.println(db);
		Database.c_updt();
	}

	static void db_export() {

		Admin_Panel_Class.tmod.setRowCount(0);
		Object[] obrow = new Object[4];

		for (int i = 0; i < db.size(); i++) {
			obrow[0] = db.get(i).idarr;
			obrow[1] = db.get(i).type;
			obrow[2] = db.get(i).value;
			obrow[3] = db.get(i).dtime;
			Admin_Panel_Class.tmod.addRow(obrow);
		}

		Admin_Panel_Class.tmod_2.setRowCount(0);
		Object[] caprow = new Object[2];
		caprow[0] = approx.apply(Petrol.Amount);
		caprow[1] = approx.apply(Diesel.Amount);
		Admin_Panel_Class.tmod_2.addRow(caprow);

	}

	static String type_conv(boolean t) {

		if (t)
			return ("Petrol");
		else
			return ("Diesel");

	}

	public static void main(String[] args) {
		Admin.setup();

		Pump_Panel_Class.initialize_pump_panel();
		Admin_Panel_Class.initialize_main_panel();
		Pane_Manager_Class.initialize_main_frame();

		Pump_Panel_Class.listen_pump_panel();
		Admin_Panel_Class.listen_admin_panel();

	}
}

class Database {

	int idarr;
	static int counter = 0;
	int value;
	String type;
	String dtime;

	static public int getCounter() {
		return counter;
	}

	static public void c_updt() {
		counter++;
	}

	public Database(int idarr, int value, String type, String dtime) {
		super();

		this.idarr = idarr;
		this.value = value;
		this.type = type;
		this.dtime = dtime;
	}

	@Override
	public String toString() {
		return "Database [c=" + counter + ", idarr=" + idarr + ", value=" + value + ", type=" + type + "]";
	}

}
