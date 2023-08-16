package com.example.laptopstore.test;

import com.example.laptopstore.entity.Laptop;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/testLaptop")
public class TestController {
    private final TestServ serv;


//    @PostMapping("/body")
//    List<Laptop> getByBody(@RequestBody Map<String,List<String>> body) throws IllegalAccessException {
//
//        for(Map.Entry<String,List<String>> f: body.entrySet()) {
//            System.out.println(f.getKey()+" поля "+f.getValue().toString());
//        }
//
//            return serv.getAll();
//    }


    @GetMapping(value = "/")
    String index(@QuerydslPredicate(root = Laptop.class) Predicate predicate,
                 Pageable pageable, @RequestParam MultiValueMap<String, String> parameters) {

        serv.getAll(predicate, pageable);

        return "index";
    }



}
