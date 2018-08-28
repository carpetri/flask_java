package com.example.app;

import java.io.Serializable;

public class ExObject implements Serializable{
	public static final long serialVUID = 1234567890L;
	private String name;
	private double value;

	public ExObject(String name, double value){
		this.name = name;
		this.value = value;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public double getValue(){
		return value;
	}

	public void setValue(double value){
		this.value = value;
	}

	public String toJSON(){
		return "{\"name\":\""+ name + "\", \"value\":" + value + "}";
	}

	@Override
	public String toString(){
		return "ExObject [name=" + name + ", value=" + value + "]";
	}

}