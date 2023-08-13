package com.example.laptopstore.util;


import com.example.laptopstore.entity.Laptop;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Util {
    Util() {
    }

    public static SessionFactory getConnectionHiber(){
        Configuration con = new Configuration().configure();
        con.addAnnotatedClass(Laptop.class);
        StandardServiceRegistryBuilder sBuild = new StandardServiceRegistryBuilder()
                .applySettings(con.getProperties());
        return con.buildSessionFactory(sBuild.build());
    }

    //commit
}
