package com.example.laptopstore.test;


import com.example.laptopstore.entity.Laptop;
import java.awt.print.Pageable;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.stereotype.Repository;
@Repository
public interface TestRepo extends TestLaptopRepo {
    List<Laptop> findAll(Predicate predicate, Pageable pageble);
}
