package com.telusko.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Laptop laptop=new Laptop();
    	laptop.setLid(101);
		laptop.setLname("Dell");
		Student student=new Student();
		student.setName("Amy");
		student.setRollno(101);
		student.setMarks(50);
		student.setLaptop(laptop);
		
        Configuration cfg=new Configuration().configure()	//hibernate.cfg.xml is default configure file 
        									 .addAnnotatedClass(Student.class)
        									 .addAnnotatedClass(Laptop.class);    
        ServiceRegistry sr=new ServiceRegistryBuilder().applySettings(cfg.getProperties())
        											   .buildServiceRegistry();
        //SessionFactory sf=cfg.buildSessionFactory();
        SessionFactory sf=cfg.buildSessionFactory(sr);
        Session session=sf.openSession();
        //must begin transaction, otherwise no table created
        session.beginTransaction();
        session.save(laptop);
        session.save(student);
        session.getTransaction().commit();
        
    }
}
