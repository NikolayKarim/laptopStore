package com.example.laptopstore.repository;

import com.example.laptopstore.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepo extends JpaRepository<Laptop, Long>{

}







//    List<Laptop> getLaptopByRAMAndCountry_madeAAndLaptop_memoryAndPriceBetweenAndUsb_portAndKAndKeyboard_backlightAndOpportunity_to_improveAndHddAndWeb_camAndSsd(String RAM,
//      String country, int laptop_memory, int price_from , int price_to, int usb_ports, boolean keyboard_backlight,
//      boolean improve, boolean hdd, boolean web_cam, boolean ssd);
