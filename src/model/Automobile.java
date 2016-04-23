package model;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Automobile implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3476758163974636784L;
	private String name;
	private float basePrice;
	private ArrayList<OptionSet> opset;
	private String make;
	private String model;
	private Option choice;
	
/**Constructors**/
	public Automobile()
	{
		basePrice = 0;
		
	}
	
	public Automobile(String n)
	{
		this.name = n;
		basePrice = 0;
		opset = new ArrayList<>();
	}
	public Automobile(String n, float baseP)
	{
		this.name = n;
		this.basePrice = baseP;
		opset = new ArrayList<>();
	}
	public Automobile(String n, int sizeofOpset) {
		this.name = n;
		opset = new ArrayList<>(sizeofOpset);

	}
	
	public Automobile(String n, float baseP, int sizeofOpset)
	{
		this.name = n;
		this.basePrice = baseP;
		opset = new ArrayList<>(sizeofOpset);	
		for(int i=0;i<sizeofOpset;i++) {
			opset.add(new OptionSet());
		}
		
	}
	
/**Getter/Setters**/
	public void setOpset(int sizeofOpset)
	{
		opset = new ArrayList<>(sizeofOpset);
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public float getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}
	
	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Option getChoice() {
		return choice;
	}

	public void setChoice(Option choice) {
		this.choice = choice;
	}
	
	public void setChoices()
	{
		Scanner in = new Scanner(System.in);
		for(int i = 0; i < opset.size(); i++)
		{
			String setName = opset.get(i).getName();
			System.out.println("Enter choice for " + setName);
			String choice = in.nextLine();
			setOptionChoice(setName, choice);
		}
		System.out.println("Total price for configured car: " + getTotalPrice());
	}
/**OptionSet accessors, mutators, modifiers**/
	public OptionSet getOpset(int i) {
		return opset.get(i);
	}
	public ArrayList<OptionSet> getOpset()
	{
		return opset;
	}

	public void setOptionsetName(int pos, String n) 
	{
		opset.get(pos).setName(n);
		return;
		
	}
	public int findOpset(String name)
	{
		int targetIndex = -1;
		do{
			for(int i = 0; i < opset.size(); i++)
			{
				if(opset.get(i).getName().equals(name))
				{
					targetIndex = i;
				};
			}
			if(targetIndex == -1)
			{
				Scanner in = new Scanner(System.in);
				System.out.println("Optionset not found. Please enter valid Option name.");
				name = in.nextLine();
				in.close();
			}
		}while(targetIndex == -1);
		return targetIndex;
	}
	
	public void updateOpset() 
	{
		String name = null;
		int foundIndex = -1;
		System.out.println("Enter name to be updated.");
		Scanner in = new Scanner(System.in);
		name = in.nextLine();
		foundIndex = findOpset(name);
		System.out.println("Enter new name.");
		name = in.nextLine();
		opset.get(foundIndex).setName(name);
		in.close();
	}
	
	/*public void deleteOpset()
	{
		String name = null;
		int foundIndex = -1;
		System.out.println("Enter name to be deleted.");
		Scanner in = new Scanner(System.in);
		name = in.nextLine();
		foundIndex = findOpset(name);
		opset[foundIndex].clear();
		in.close();
	}*/
	
/** OptionSet accessors, mutators, modifiers **/
	public ArrayList<Option> getOption(int i,int j)
	{
		return opset.get(i).getOpt();
	}
	public Boolean addOption(int opsetIndex, String name)
	{
		try {
			opset.get(opsetIndex).addOption(name);
		}
		catch(IndexOutOfBoundsException e) {
			return false;
		}
		
		return true;
	}
	
	public void addOpset(String name)
	{
		opset.add(new OptionSet(name));
	}
	public String getOptionName(int i, int j)
	{
		return opset.get(i).getOpt().get(j).getName();
	}

	public void setOptionName(int i, int j, String n) 
	{
		opset.get(i).getOpt(j).setName(n);
		return;
	}
	
	public float getOptionPrice(int i, int j)
	{
		return opset.get(i).getOpt().get(j).getPrice();
	}
	
	public Boolean setOptionPrice(int i, int j, Float price) 
	{
		try {
			opset.get(i).getOpt(j).setPrice(price);
		}
		catch(IndexOutOfBoundsException e) {
			return false;
		}
		
		return true; 
	}
	
	public int findOption(String optionSetName, String optionName)
	{
		int targetIndex = -1;
		int opsetindex = -1;
		
		opsetindex = findOpset(optionSetName);
		
		do{
			for(int i = 0; i < opset.get(opsetindex).getOpt().size(); i++)
			{
				if(opset.get(opsetindex).getOpt(i).getName().equals(optionName))
				{
					targetIndex = i;
					//exit loop
				};
			}
			if(targetIndex == -1)
			{
				Scanner in = new Scanner(System.in);
				System.out.println("Optionset not found. Please enter valid Option name.");
				optionName = in.nextLine();
				in.close();
			}
		}while(targetIndex == -1);
		
		return targetIndex;
	}
	public int findOption(int opsetindex, String optionName)
	{
		int targetIndex = -1;
		
		do{
			for(int i = 0; i < opset.get(opsetindex).getOpt().size(); i++)
			{
				if(opset.get(opsetindex).getOpt(i).getName().equals(optionName))
				{
					targetIndex = i;
					//exit loop
				};
			}
			if(targetIndex == -1)
			{
				Scanner in = new Scanner(System.in);
				System.out.println("Optionset not found. Please enter valid Option name.");
				optionName = in.nextLine();
				in.close();
			}
		}while(targetIndex == -1);
		
		return targetIndex;
	}
	
	public void updateOption(int opsetIndex) 
	{
		String name = null;
		float price = 0;
		int foundIndex = -1;
		
		foundIndex = findOption(null, name);
		opset.get(opsetIndex).getOpt(foundIndex).setName(name);
		opset.get(opsetIndex).getOpt(foundIndex).setPrice(price);
		
	}
	
	public void getOption(String opsetName, String opName)
	{
		opset.get(this.findOpset(opsetName)).getOpt(this.findOption(opsetName, opName));
	}
	
	public void deleteOption()
	{
		
	}
	
/** Print Methods **/
	public void printAll() 
	{
		System.out.println("\n"+name); 
		System.out.print("Base Price: ");
		System.out.println(basePrice);
		for(int i=0;i<opset.size();i++)
		{
			opset.get(i).printAll();
		}
		
	}
	
	public String getOptionChoice(String setName)
	{
		int opsetIndex = this.findOpset(setName);
		return opset.get(opsetIndex).getChoice().getName();
	}
	public float getOptionChoicePrice(String setName)
	{
		int opsetIndex = this.findOpset(setName);
		return opset.get(opsetIndex).getChoice().getPrice();
	}
	public void setOptionChoice(String setName, String optionName)
	{
		int opsetIndex = this.findOpset(setName);
		Option op = opset.get(opsetIndex).getOpt((this.findOption(setName, optionName)));
		opset.get(opsetIndex).setChoice(op);
	}
	
	public int getTotalPrice()
	{
		int totalPrice = 0;
		for(int i=0;i<opset.size();i++)
		{
			totalPrice += opset.get(i).getChoice().getPrice();
		}
		totalPrice += basePrice;
		return totalPrice;
	}
}
