package com.example.laptopstore.controllers;

import com.example.laptopstore.DTO.LaptopDTO;
import com.example.laptopstore.services.LaptopService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/laptop")
public class Controller {
    @Autowired
    public LaptopService laptopService;

    @GetMapping
    public ResponseEntity getAllLaptops(@RequestBody LaptopDTO laptopDTO,
                                        @RequestParam(required = false, defaultValue = "0") int page,
                                        @RequestParam(required = false, defaultValue = "10") int size) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(laptopService.getAllLaptops(PageRequest.of(page, size)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

    @PostMapping("/test")
    public ResponseEntity getLaptopPostFilters(@RequestBody LaptopDTO laptopDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(laptopService.findbyDTO(laptopDTO));
    }

}



