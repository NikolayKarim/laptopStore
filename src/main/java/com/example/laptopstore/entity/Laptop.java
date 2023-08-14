package com.example.laptopstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.sql.Date;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="laptop" )
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country_made;

    private Date date_of_made;

    private String laptop_name;

    private String laptop_model;

    private String CPU_name;

    private Integer CPU_model;

    private Double cpu_GHz;

    private Integer RAM;

    private Integer laptop_memory;

    private String hdd;

    private String ssd;

    private String keyboard_backlight;

    private Integer mwh;

    private String bluetooth_model;

    private Double wi_fi_model;

    private Double mpx_camera;

    private String web_cam;

    private Integer usb_port;

    private String opportunity_to_improve;

    private Double price;

}
