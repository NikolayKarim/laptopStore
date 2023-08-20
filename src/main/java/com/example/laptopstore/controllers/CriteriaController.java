package com.example.laptopstore.controllers;


import com.example.laptopstore.entity.Laptop;
import com.example.laptopstore.services.CriteriaService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/criteria")
public class CriteriaController {
    private final CriteriaService serv;


    @PostMapping("/correct")
    Page<Laptop> criteriaWithPrincipal(@RequestBody Map<String, List<String>> map,
                                       @RequestParam(value = "page",defaultValue = "0") int page,
                                       @RequestParam(value = "size",defaultValue = "10") int size){
        return serv.getCriteriaWithPrincipal(map,page,size);
    }
}
//new comment
