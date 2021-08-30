package com.telusko.hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;

//POJO
//entity and table names will be aliens
@Entity(name = "aliens")
public class Alien {
	@Id
	private int aid;
	private String aname;
	private String color;
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
}
