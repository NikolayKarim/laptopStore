package com.example.laptopstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
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

    @Column(nullable = true)
    private String country_made;

    @Column(nullable = true)
    private Date date_of_made;

    @Column(nullable = true)
    private String laptop_name;

    @Column(nullable = true)
    private String laptop_model;


    @Column(nullable = true)
    private String CPU_name;

    @Column(nullable = true)
    private int CPU_model;

    @Column(nullable = true)
    private double cpu_GHz;

    @Column(nullable = true)
    private int RAM;

    @Column(nullable = true)
    private int laptop_memory;

    @Column(nullable = true)
    private String hdd;

    @Column(nullable = true)
    private String ssd;

    @Column(nullable = false)
    private String keyboard_backlight;

    @Column(nullable = true)
    private int mwh;

    @Column(nullable = true)
    private String bluetooth_model;

    @Column(nullable = true)
    private double wi_fi_model;

    @Column(nullable = true)
    private double mpx_camera;

    @Column(nullable = true)
    private String web_cam;

    @Column(nullable = true)
    private int usb_port;

    @Column(nullable = true)
    private String opportunity_to_improve;

    @Column(nullable = true)
    private double price;

}
