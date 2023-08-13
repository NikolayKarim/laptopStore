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
    public Integer CPU_model;
    public Double cpu_GHz;
    public Integer RAM;
    public Integer laptop_memory;
    public String hdd;
    public String ssd;
    public String keyboard_backlight;
    public Integer mwh;
    public String bluetooth_model;
    public Double wi_fi_model;
    public Double mpx_camera;
    public String web_cam;
    public Integer usb_port;
    public String opportunity_to_improve;
    public Double price;
//комит

}
