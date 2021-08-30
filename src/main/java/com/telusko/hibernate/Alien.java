package com.telusko.hibernate;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

//POJO
//only table names will be aliens
@Entity
//@Table(name="alien_table")
public class Alien {
	@Id
	private int aid;
	private String aname;
	//lazy fetch by default
	@OneToMany(mappedBy = "alien",fetch = FetchType.EAGER)
	private Collection<Laptop> laptops=new ArrayList<>();
	
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	
	public Collection<Laptop> getLaptops() {
		return laptops;
	}
	public void setLaptops(Collection<Laptop> laptops) {
		this.laptops = laptops;
	}
	@Override
	public String toString() {
		return "Alien [aid=" + aid + ", aname=" + aname + "]";
	}
	
	
}
