package com.example.laptopstore.controllers;

import com.example.laptopstore.DTO.LaptopDTO;
import com.example.laptopstore.entity.Laptop;
import com.example.laptopstore.services.MyFilterSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/spec")
public class ControllerSpec {
    private final MyFilterSpecification<Laptop> filter;

    @PostMapping("/body")
    public Page<Laptop> getFromSpec(@RequestBody LaptopDTO dto,
                                    @RequestParam(name = "page", required = false,defaultValue = "0") int page,
                                    @RequestParam(name = "size", required = false, defaultValue = "10") int size){
        return filter.findBySpecDTO(dto, page, size)    ;
    }
}
