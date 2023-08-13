package com.example.laptopstore.services;

import com.example.laptopstore.DTO.LaptopDTO;
import com.example.laptopstore.entity.Laptop;
import com.example.laptopstore.repository.LaptopRepo;
import com.example.laptopstore.util.Util;
import java.lang.reflect.Field;
import java.util.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

@Service
public class LaptopService {
    @Autowired
    private LaptopRepo laptopRepo;

    public List<Laptop> getAllLaptops(PageRequest pageRequest) {
        return laptopRepo.findAll(pageRequest).getContent();
    }


    public ResponseEntity findbyDTO(LaptopDTO laptopDTO)  {
        String query = "from Laptop where";
        try {
            Map<String,String> map = getLaptopDTOFields(laptopDTO);
            int count = map.size();
            Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                --count;
                Map.Entry<String, String> entry = iterator.next();
                query+=" "+entry.getKey()+"="+entry.getValue();
                if(count!=0){
                    query+=" and";
                }
            }

        }catch (IllegalAccessException e){
            e.getMessage();
        }

        List<Laptop> laptops = new ArrayList<>();
        try (Session session = Util.getConnectionHiber().openSession()) {
            Transaction transaction = session.beginTransaction();
            laptops =  session.createQuery(query)
                    .getResultList();
            transaction.commit();
            session.close();
            return ResponseEntity.status(HttpStatus.OK).body(laptops);
        }
    }



    public static Map<String, String> getLaptopDTOFields(LaptopDTO laptopDTO) throws IllegalAccessException {
        Class c = LaptopDTO.class;
        Field[] fs = c.getDeclaredFields();
        Map<String, String> mapFields = new HashMap<>();
        for (int i = 0; i < fs.length; i++) {

            if (fs[i].get(laptopDTO) instanceof Long ) {          //checking integer
                if (parseLong(fs[i].get(laptopDTO).toString()) != 0) {
                    mapFields.put(fs[i].getName(), fs[i].get(laptopDTO).toString());
                    continue;
                } else {
                    continue;
                }
            }


            if (fs[i].get(laptopDTO) instanceof Integer ) {          //checking integer
                if (parseInt(fs[i].get(laptopDTO).toString()) != 0) {
                    mapFields.put(fs[i].getName(), fs[i].get(laptopDTO).toString());
                    continue;
                } else {
                    continue;
                }
            }

            if (fs[i].get(laptopDTO) instanceof Double) {            //checking double
                if (parseDouble(fs[i].get(laptopDTO).toString()) != 0) {
                    mapFields.put(fs[i].getName(), fs[i].get(laptopDTO).toString());
                    continue;
                } else {
                    continue;
                }
            }

            if (fs[i].get(laptopDTO) != null) {                     //checking others
                mapFields.put(fs[i].getName(),"'"+ fs[i].get(laptopDTO).toString()+"'");
            }
        }
        return mapFields;
    }

}

