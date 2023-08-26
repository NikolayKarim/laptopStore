package com.example.laptopstore.controllers;

import com.example.laptopstore.DTO.LaptopDTO;
import com.example.laptopstore.entity.Laptop;
import com.example.laptopstore.services.LaptopJDBCService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/laptop")
@RequiredArgsConstructor
public class JDBCController {
    private final LaptopJDBCService laptopJDBCService;



    @GetMapping
    public Page<Laptop> getAllLaptops(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        try {
            return laptopJDBCService.getAllLaptops(PageRequest.of(page, size));
        } catch (Exception e) {
            throw new RuntimeException("Not found");
        }
    }

    @PostMapping("/jdbcWithPage")
    public Page<Laptop>
    getLaptopPostFiltersPage(@RequestBody LaptopDTO laptopDTO,
                             @RequestParam(required = false, defaultValue = "0") int page,
                             @RequestParam(required = false, defaultValue = "10") int size)
            throws IllegalAccessException {

        return    laptopJDBCService
                .getLaptopPage(laptopDTO,page,size);
    }

    @PostMapping("/jdbc")
    public ResponseEntity<List<Laptop>>
    getLaptopPostFilters(@RequestBody LaptopDTO laptopDTO,
                         @RequestParam(required = false, defaultValue = "0") int page,
                         @RequestParam(required = false, defaultValue = "10") int size)
            throws IllegalAccessException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(laptopJDBCService.findbyDTOAndPagerequest(laptopDTO,PageRequest.of(page,size)));
    }
}




