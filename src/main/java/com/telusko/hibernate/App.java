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
    	Alien telusko=new Alien();		
		telusko.setAid(101); 
		AlienName an=new AlienName();
		an.setFname("Amy");
		an.setLname("Duke");
		an.setMname("Kaven");
		telusko.setAname(an); 
		telusko.setColor("white");		 
        Configuration cfg=new Configuration().configure()	//hibernate.cfg.xml is default configure file 
        									 .addAnnotatedClass(Alien.class);    
        ServiceRegistry sr=new ServiceRegistryBuilder().applySettings(cfg.getProperties())
        											   .buildServiceRegistry();
        //SessionFactory sf=cfg.buildSessionFactory();
        SessionFactory sf=cfg.buildSessionFactory(sr);
        Session session=sf.openSession();
        //must begin transaction, otherwise no table created
        Transaction tx= session.beginTransaction();
        session.save(telusko);
        telusko=(Alien) session.get(Alien.class, 101);
        tx.commit();
        System.out.println(telusko.toString());
    }
}
