package com.example.laptopstore.DTO;



import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaptopDTO {

    public Long id;
    public String country_made;
    public Date date_of_made;
    public String laptop_name;
    public String laptop_model;
    public String CPU_name;
    public int CPU_model;
    public double cpu_GHz;
    public int RAM;
    public int laptop_memory;
    public String hdd;
    public String ssd;
    public String keyboard_backlight;
    public int mwh;
    public String bluetooth_model;
    public double wi_fi_model;
    public double mpx_camera;
    public String web_cam;
    public int usb_port;
    public String opportunity_to_improve;
    public double price;


}
