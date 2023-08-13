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
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

@Service
@RequiredArgsConstructor
public class LaptopService {
    private final  LaptopRepo laptopRepo;
    private final SessionFactory sessionFactory;


    public List<Laptop> getAllLaptops(PageRequest pageRequest) {
        return laptopRepo.findAll(pageRequest).getContent();
    }


    public List<Laptop> findbyDTOWithHibernate(LaptopDTO laptopDTO, PageRequest pageRequest) throws IllegalAccessException {
        List<Laptop> laptops = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            laptops = session.createQuery(getQuery(laptopDTO)).setFirstResult(pageRequest.getPageNumber())
                    .setMaxResults(pageRequest.getPageSize())
                    .getResultList();
            transaction.commit();
            session.close();
            return laptops;
        }
    }



    public static String getQuery(LaptopDTO laptopDTO) throws IllegalAccessException{
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


    public static Map<String, String> getLaptopDTOFields(LaptopDTO laptopDTO) throws IllegalAccessException{
            Class c = LaptopDTO.class;

            Field[] fs = c.getDeclaredFields();
            Map<String, String> mapFields = new HashMap<>();
            for (int i = 0; i < fs.length; i++) {

                if (fs[i].get(laptopDTO) instanceof Long) {          //checking integer
                    if (parseLong(fs[i].get(laptopDTO).toString()) != 0) {
                        mapFields.put(fs[i].getName(), fs[i].get(laptopDTO).toString());
                        continue;
                    }
                        continue;

                }

                if (fs[i].get(laptopDTO) instanceof Integer) {          //checking integer
                    if (parseInt(fs[i].get(laptopDTO).toString()) != 0) {
                        mapFields.put(fs[i].getName(), fs[i].get(laptopDTO).toString());
                        continue;
                    }
                        continue;
                }

                if (fs[i].get(laptopDTO) instanceof Double) {            //checking double
                    if (parseDouble(fs[i].get(laptopDTO).toString()) != 0) {
                        mapFields.put(fs[i].getName(), fs[i].get(laptopDTO).toString());
                        continue;
                    }
                    continue;
                }
                if (fs[i].get(laptopDTO) != null) {                     //checking others
                    mapFields.put(fs[i].getName(), "'" + fs[i].get(laptopDTO).toString() + "'");
                }
            }
            return mapFields;

    }
}

