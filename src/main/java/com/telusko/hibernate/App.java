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
        session.beginTransaction();
        //get()
//      query was run even if l isn't used -> obj, return null if not found        
        Laptop l=(Laptop) session.get(Laptop.class, 6);
//      System.out.println(l);
        //load()
//      query was not run if l1 wasn't used -> proxy obj, throw exception if not found        
        Laptop l1=(Laptop) session.load(Laptop.class, 6);
//        System.out.println(l1);
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(l1);		//sop was called after 3s
        session.getTransaction().commit();
        session.close();
        

    }
}
