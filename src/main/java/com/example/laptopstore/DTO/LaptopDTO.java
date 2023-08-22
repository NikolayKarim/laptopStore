package com.example.laptopstore.DTO;

import java.sql.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaptopDTO {

    public List<Long> id;
    public List<String> country_made;
    public List<Date> date_of_made;
    public List<String> laptop_name;
    public List<String> laptop_model;
    public List<String> CPU_name;
    public List<Integer> CPU_model;
    public List<Double> cpu_GHz;
    public List<Integer> RAM;
    public List<Integer> laptop_memory;
    public List<String> hdd;
    public List<String> ssd;
    public List<String> keyboard_backlight;
    public List<Integer> mwh;
    public List<String> bluetooth_model;
    public List<Double> wi_fi_model;
    public List<Double> mpx_camera;
    public List<String> web_cam;
    public List<Integer> usb_port;
    public List<String> opportunity_to_improve;
    public List<Double> price;
}
