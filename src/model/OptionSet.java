package model;

import java.io.Serializable;
import java.util.ArrayList;

public class OptionSet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2392822486445356573L;
	private ArrayList<Option> opt;
	private String name;
	private Option choice;
	
	public OptionSet() {
		opt = new ArrayList<>();
	}
	public OptionSet(String n)
	{
		opt = new ArrayList<>();
		this.name = n;
	}
	public OptionSet(String n, int size)
	{
		opt = new ArrayList<>(size);
		name = n;
	}
	protected ArrayList<Option> getOpt() {
		return opt;
	}
	protected Option getOpt(int i)
	{
		return opt.get(i);
	}
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	
	protected Option getChoice() {
		return choice;
	}
	protected void setChoice(Option choice) {
		this.choice = choice;
	}
	protected void printAll()
	{
		System.out.println("\n" + name + ":");
		for(int i=0;i<opt.size();i++)
		{
			opt.get(i).printAll();
		}
	}
	
	protected void addOption(String name)
	{
		opt.add(new Option(name));
	}
	
}
