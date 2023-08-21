package com.example.laptopstore.repository;

import com.example.laptopstore.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LaptopSpecRepo extends JpaRepository<Laptop,Long>, JpaSpecificationExecutor<Laptop> {


}
