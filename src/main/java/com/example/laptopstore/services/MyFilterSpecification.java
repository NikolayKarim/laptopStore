package com.example.laptopstore.services;

import com.example.laptopstore.DTO.LaptopDTO;
import com.example.laptopstore.entity.Laptop;
import com.example.laptopstore.repository.LaptopSpecRepo;
import jakarta.persistence.criteria.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyFilterSpecification<T> {
    private final LaptopSpecRepo laptopSpecRepo;


    public Page<Laptop> findBySpecDTO(LaptopDTO dto, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return laptopSpecRepo.findAll(getSpecification(dto), pageRequest);
    }


    Specification<Laptop> getSpecification(LaptopDTO dto) {
        return ((root, query, criteriaBuilder) -> {
            Field[] fields = LaptopDTO.class.getDeclaredFields();
            List<Predicate> predicates = new ArrayList<>();
            try {
                for (Field field : fields) {
                    if (Objects.nonNull(field)) {
                        List<Object> test = (List<Object>) field.get(dto);
                        if (Objects.isNull(test)) continue;
                        if (test.size()==0) continue;

                        Predicate parentPredicate = root.get(field.getName()).in((List<Object>)field.get(dto));
                        predicates.add(parentPredicate);
                    }
                }
                } catch(IllegalAccessException e){
                    throw new RuntimeException(e.getCause() + " !!!! DTO's field is impty ");
                }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }
        );
    }
}