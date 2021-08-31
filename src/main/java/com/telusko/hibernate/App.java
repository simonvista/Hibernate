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
        									 .addAnnotatedClass(Student.class);        									 ;   
        ServiceRegistry sr=new ServiceRegistryBuilder().applySettings(cfg.getProperties())
        											   .buildServiceRegistry();
        //SessionFactory sf=cfg.buildSessionFactory();
        SessionFactory sf=cfg.buildSessionFactory(sr);
        Session session=sf.openSession();
        //must begin transaction, otherwise no table created
        session.beginTransaction();
        //SQL -> native query
//        SQLQuery query=session.createSQLQuery("select * from student where marks>10");
//        query.addEntity(Student.class);
//        List<Student> students=query.list();
//        for(Student s:students) {
//        	System.out.println(s);
//        }
        SQLQuery query=session.createSQLQuery("select name,marks from student where marks>10");
//        query.addEntity(Student.class);
//        List<Student> students=query.list();
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List students=query.list();
        for(Object s:students) {
        	Map m=(Map) s;
        	System.out.println(m.get("name")+", "+m.get("marks"));
        }
        
        session.getTransaction().commit();
        session.close();
        

    }
}
