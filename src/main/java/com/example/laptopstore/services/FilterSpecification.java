package com.example.laptopstore.services;

import com.example.laptopstore.DTO.LaptopDTO;
import com.example.laptopstore.entity.Laptop;
import com.example.laptopstore.repository.LaptopSpecRepo;
import jakarta.persistence.criteria.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilterSpecification<T> {
    LaptopSpecRepo laptopSpecRepo;


    public Page<Laptop> findBySpecDTO(LaptopDTO dto, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Predicate> listPredicates = new ArrayList<>();


        return laptopSpecRepo.findAll(getSpecification(dto), pageRequest);
    }

    Specification<Laptop> getSpecification(LaptopDTO dto) {
        return ((root, query, criteriaBuilder) -> {
            List<LaptopDTO> listDTO = new ArrayList<>();
            Field[] fields = LaptopDTO.class.getDeclaredFields();
            List<Predicate> predicates = new ArrayList<>();
            for (Field field : fields) {
                if (field != null) {
                    Expression<String> parentExpression = root.get(field.getName());
                    Predicate parentPredicate = null;
                    try {
                        parentPredicate = parentExpression.in(field.get(dto));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    predicates.add(parentPredicate);
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
             throw new RuntimeException("filter not correct");
        }
        );
    }
}
