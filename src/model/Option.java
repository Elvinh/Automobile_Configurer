package model;

import java.io.Serializable;
	
public class Option implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2909960678728858239L;
	private String name;
	private float price;
	
	public Option(){
	}
	
	public Option(String name, float price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	public Option(String name)
	{
		this.name = name;
	}
	
	public Option(float price)
	{
		this.price = price;
	}
	
	protected String getName() {
		return name;
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	
	protected float getPrice() {
		return price;
	}
	
	protected void setPrice(float price) {
		this.price = price;
	}
	protected void printAll()
	{
		System.out.print(name);
		System.out.print(" $");
			System.out.println(price);
		}
		
}

