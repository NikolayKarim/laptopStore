package com.example.laptopstore.services;

import com.example.laptopstore.DTO.LaptopDTO;
import com.example.laptopstore.entity.Laptop;
import com.example.laptopstore.repository.LaptopRepo;
import java.lang.reflect.Field;
import java.util.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LaptopJDBCService {
    private final LaptopRepo laptopRepo;
    private final SessionFactory sessionFactory;

    public Page<Laptop> getAllLaptops(PageRequest pageRequest) {
        return laptopRepo.findAll(pageRequest);
    }

    public Page<Laptop> getLaptopPage(LaptopDTO laptopDTO, int page, int size) {

        Pageable pageRequest = PageRequest.of(page, size);

        List<Laptop> allLaptops = findbyDTO(laptopDTO);
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), allLaptops.size());

        List<Laptop> pageContent = allLaptops.subList(start, end);
        return new PageImpl<>(pageContent, pageRequest, allLaptops.size());
    }

    public List<Laptop> findbyDTOAndPagerequest(LaptopDTO laptopDTO, PageRequest pageRequest) {
        List<Laptop> laptops = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            laptops = session.createQuery(getQuery(laptopDTO)).setFirstResult(pageRequest.getPageNumber())
                    .setMaxResults(pageRequest.getPageSize())
                    .getResultList();
            transaction.commit();
            session.close();
            return laptops;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Laptop> findbyDTO(LaptopDTO laptopDTO) {
        List<Laptop> laptops = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            laptops = session.createQuery(getQuery(laptopDTO)).getResultList();
            transaction.commit();
            session.close();
            return laptops;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getQuery(LaptopDTO laptopDTO) throws IllegalAccessException {
        String query = "from Laptop where";
        Map<String, String> map = getLaptopDTOFields(laptopDTO);
        int count = map.size();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            count--;
            query += " " + entry.getKey() + "=" + entry.getValue();
            if (count != 0) {
                query += " and";
            }
        }
        return query;
    }

    public static Map<String, String> getLaptopDTOFields(LaptopDTO laptopDTO) throws IllegalAccessException {
        Class c = LaptopDTO.class;
        Field[] fs = c.getDeclaredFields();
        Map<String, String> map = new HashMap<>();
        for (Field field : fs) {
            if (Objects.nonNull(field)) {
                if (field.get(laptopDTO) == null) continue;
                if (field.get(laptopDTO) instanceof String) {
                    map.put(field.getName(), "'" + field.get(laptopDTO).toString() + "'");
                } else {
                    map.put(field.getName(), field.get(laptopDTO).toString());
                }
            }
        }
        return map;
    }
}
// commit thirth
