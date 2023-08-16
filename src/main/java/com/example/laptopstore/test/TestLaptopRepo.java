package com.example.laptopstore.test;

import com.example.laptopstore.DTO.LaptopDTO;
import com.example.laptopstore.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestLaptopRepo extends
        JpaRepository<Laptop, Long>, QuerydslPredicateExecutor<LaptopDTO> {
}
