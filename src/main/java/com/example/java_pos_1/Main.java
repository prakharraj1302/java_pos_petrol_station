package com.example.java_pos_1;

public class Main {
    public static void main(String[] args) {
        // Extract.get_price();

        // public static void main(String[] args) {
            Admin.setup();
    
            Pump_Panel_Class.initialize_pump_panel();
            Admin_Panel_Class.initialize_main_panel();
            Pane_Manager_Class.initialize_main_frame();
    
            Pump_Panel_Class.listen_pump_panel();
            Admin_Panel_Class.listen_admin_panel();
    
        // }
        // System.out.println("Hello world!");
    }
}