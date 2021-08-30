package com.telusko.hibernate;

import java.util.Collection;
import java.util.List;

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
//		Alien a1=new Alien();
//		a1.setAname("Navin");
//		a1.setAid(101);
//		Laptop l1=new Laptop();
//		l1.setLid(201);
//		l1.setBrand("Dell");
//		l1.setAlien(a1);
//		l1.setPrice(600);
//		a1.getLaptops().add(l1);
        Configuration cfg=new Configuration().configure()	//hibernate.cfg.xml is default configure file         									 
        									 .addAnnotatedClass(Laptop.class)
        									 .addAnnotatedClass(Alien.class);    
        ServiceRegistry sr=new ServiceRegistryBuilder().applySettings(cfg.getProperties())
        											   .buildServiceRegistry();
        //SessionFactory sf=cfg.buildSessionFactory();
        SessionFactory sf=cfg.buildSessionFactory(sr);
        Session session=sf.openSession();
        //must begin transaction, otherwise no table created
        session.beginTransaction();
        
        //save order matters
//        session.save(a1);
//        session.save(l1);
//        Lazy fetch is by default -> get alien won't get laptop by default
        Alien a1=(Alien)session.get(Alien.class, 1);        
        System.out.println(a1.getAname());
        System.out.println(a1.getAid());
        Collection<Laptop> laptops=a1.getLaptops();
        for(Laptop l:laptops) {
        	System.out.println(l);
        }
        
        session.getTransaction().commit();
        
    }
}
