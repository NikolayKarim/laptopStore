package com.example.laptopstore.test;

import com.example.laptopstore.DTO.LaptopDTO;
import com.example.laptopstore.entity.Laptop;
import java.awt.print.Pageable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class TestServ {
   private final TestRepo testRepo;


    List<Laptop> getAll(Predicate predicate , Pageable pageble){
        return testRepo.findAll(predicate, pageble);
    }


    Map<String,List<String>> getFields(LaptopDTO laptopDTO) throws IllegalAccessException {
        Class c = LaptopDTO.class;
        Field[] fs = c.getDeclaredFields();
        Map<String, List<String>> map = new HashMap<>();
//        Predicate predicate = ;

        return map;

//        Predicate predicate = user.firstname.equalsIgnoreCase("dave")
//                .and(user.lastname.startsWithIgnoreCase("mathews"));
//
//        userRepository.findAll(predicate);
    }
}
