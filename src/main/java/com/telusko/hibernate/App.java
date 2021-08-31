package com.telusko.hibernate;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.hibernate.Query;
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
        									 .addAnnotatedClass(Student.class);        									 ;   
        ServiceRegistry sr=new ServiceRegistryBuilder().applySettings(cfg.getProperties())
        											   .buildServiceRegistry();
        //SessionFactory sf=cfg.buildSessionFactory();
        SessionFactory sf=cfg.buildSessionFactory(sr);
        Session session=sf.openSession();
        //must begin transaction, otherwise no table created
        session.beginTransaction();
//        Random r=new Random();
//        for(int i=0;i<10;i++) {
//        	Student s=new Student();
//        	s.setRollno(i);
//        	s.setName("Name"+i);
//        	s.setMarks(r.nextInt(20));
//        	session.save(s);
//        }
//        HQL
//        Query q=session.createQuery("from Student");
//        Query q=session.createQuery("from Student where marks>14");
//        List<Student> students=q.list();
//        students.forEach(System.out::println);
        Query q=session.createQuery("from Student where rollno=7");
        Student s=(Student) q.uniqueResult();
        System.out.println(s);
        
        session.getTransaction().commit();
        session.close();
        

    }
}
