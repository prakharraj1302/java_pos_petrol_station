package com.example.java_pos_1;

public class Pump {
	static Double Rate;
	static Double Amount;
	
	static void set_default_rate() {
		
		Petrol.Rate=101.0;
		Diesel.Rate=201.0;
		Petrol.Amount = 100.0;
		Diesel.Amount = 100.0;
		
	}
	
	static void set_rate_current(Double[] rate_arr ) {
		
		Petrol.Rate = (rate_arr[0]);
		Diesel.Rate = (rate_arr[1]);
		Petrol.Amount = 100.0;
		Diesel.Amount = 100.0;
	}
	
	static void capacity_update(int amt , boolean type) {
		
		if(type) {
			Petrol.Amount = Petrol.Amount - (amt/Petrol.Rate); 
		}
		else {
			Diesel.Amount = Diesel.Amount - (amt/Diesel.Rate);
		}
	}
}

class Petrol extends Pump{
	static Double Rate;
	static Double Amount;
	
	Petrol(Double rate , Double cap){
		Rate = rate;
		Amount = cap;
		
	}
	
	public static void set_rate(double rate) {
		Petrol.Rate = rate;
	}
	public static void set_cap(double capacity) {
		Petrol.Amount  = capacity;
	}
	
}

class Diesel extends Pump{
	
	static Double Rate;
	static Double Amount;
	
	Diesel(Double rate , Double cap){
		Diesel.Rate = rate;
		Diesel.Amount = cap;
		
	}
	public static void set_rate(double rate) {
		Diesel.Rate = rate;
	}
	public static void set_cap(double capacity) {
		Diesel.Amount  = capacity;
	}
}
