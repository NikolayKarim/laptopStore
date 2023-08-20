package com.example.laptopstore.services;

import com.example.laptopstore.entity.Laptop;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class CriteriaService {
    private final EntityManager session;

    public Page<Laptop> getCriteriaWithPrincipal(Map<String, List<String>> map , int page, int size){

        PageRequest pageRequest = PageRequest.of(page,size);
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Laptop> cq = cb.createQuery(Laptop.class);
        Root<Laptop> root = cq.from(Laptop.class);

        Predicate[] predicates = new Predicate[map.size()];
        Iterator<Map.Entry<String,List<String>>> iterator = map.entrySet().iterator();

        for(int i =0; i<map.size();i++){
            Map.Entry<String, List<String>> entry = iterator.next();
            Expression<String> parentExpression = root.get(entry.getKey());
            Predicate parentPredicate = parentExpression.in(entry.getValue());
            predicates[i] = parentPredicate;

        }
        cq.select(root).where(predicates);
        List<Laptop> laptops = session.createQuery(cq).getResultList();
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), laptops.size());

        List<Laptop> pageContent = laptops.subList(start, end);
        return new PageImpl<>(pageContent, pageRequest, laptops.size());
    }
}
// new comment

