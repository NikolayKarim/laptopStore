package com.example.laptopstore.services;

import com.example.laptopstore.entity.Laptop;
import com.example.laptopstore.repository.LaptopRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class KafkaService {
    private final LaptopRepo laptopKafka;


    public void save(Laptop laptopDTOKafka) {
        laptopKafka.save(laptopDTOKafka);
        log.info("Created new laptop : {}", laptopDTOKafka.toString());
    }
}







