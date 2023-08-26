package com.example.laptopstore.controllers;

import com.example.laptopstore.entity.Laptop;
import com.example.laptopstore.repository.LaptopRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("simple/")
public class SimpleController {
    private final LaptopRepo laptopRepo;


    @PostMapping("/create")

    public ResponseEntity<Laptop> createLaptop(@RequestBody Laptop laptop){
        laptopRepo.save(laptop);
        log.info("Created laptop: {}", laptop);
        return ResponseEntity.status(HttpStatus.OK).body(laptop);
    }
}
