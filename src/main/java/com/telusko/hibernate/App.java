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
    	Alien a=null;
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
        									 .addAnnotatedClass(Alien.class);    
        ServiceRegistry sr=new ServiceRegistryBuilder().applySettings(cfg.getProperties())
        											   .buildServiceRegistry();
        //SessionFactory sf=cfg.buildSessionFactory();
        SessionFactory sf=cfg.buildSessionFactory(sr);
        Session session1=sf.openSession();
        //must begin transaction, otherwise no table created
        session1.beginTransaction();
        a=(Alien) session1.get(Alien.class, 101);        
        System.out.println(a);
      //1st-level caching (provided by Hibernate by default) -> same query was run only once in the same session
        a=(Alien) session1.get(Alien.class, 101);
        System.out.println(a);
        session1.getTransaction().commit();
        session1.clear();
        
//        new session
        Session session2=sf.openSession();
        session2.beginTransaction();
//        same query will be run in different session
        a=(Alien) session2.get(Alien.class, 101);        
        System.out.println(a);
        session2.getTransaction().commit();
        session2.clear();
//        2nd-level caching (3rd party apps, e.g. ehcache) will solve this issue in different session
    }
}
