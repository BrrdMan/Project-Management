package com.jrp.pma.springExample;

public class Car {
	
	Engine e;
	Doors d;
	Tyres t;
	
	public Car(Engine e, Doors d, Tyres t) {
		super();
		this.e = e;
		this.d = d;
		this.t = t;
	}
	
	public void printCarDetails() {
		System.out.println(this.e + " " + this.d);
	}

}
