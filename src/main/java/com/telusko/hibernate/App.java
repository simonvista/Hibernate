package com.telusko.hibernate;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class App 
{
    public static void main( String[] args )
    {

        Configuration cfg=new Configuration().configure()	//hibernate.cfg.xml is default configure file         									 
        									 .addAnnotatedClass(Laptop.class);        									 ;   
        ServiceRegistry sr=new ServiceRegistryBuilder().applySettings(cfg.getProperties())
        											   .buildServiceRegistry();
        //SessionFactory sf=cfg.buildSessionFactory();
        SessionFactory sf=cfg.buildSessionFactory(sr);
        Session session=sf.openSession();
        Laptop l=new Laptop();
        Random r=new Random();
        for(int i=0;i<15;i++) {
        	l.setLid(i);
        	l.setBrand("Brand"+i);
        	l.setPrice(r.nextInt(1000));
        	session.save(l);
        }
        session.getTransaction().commit();
        session.close();
        

    }
}
