package com.telusko.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Alien telusko=new Alien();
        telusko.setAid(101);
        telusko.setAname("Navin");
        telusko.setColor("green");
        Configuration cfg=new Configuration().configure()	//hibernate.cfg.xml is default configure file 
        									 .addAnnotatedClass(Alien.class);       
        SessionFactory sf=cfg.buildSessionFactory();
        Session session=sf.openSession();
        //must begin transaction, otherwise no table created
        Transaction tx= session.beginTransaction();
        session.save(telusko);
        tx.commit();
    }
}
