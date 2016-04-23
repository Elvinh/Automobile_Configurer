package util;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.StringTokenizer;

import exception.AutoException;
import model.*;

public class FileIO {

	public FileIO(){
	}
	
	@SuppressWarnings("resource")
	public Automobile readFile(String fileName) throws AutoException, IOException
	{
		//Open File for reading
		
		FileReader file;
		float baseP;
		int numOfOpsets;
		float optionPrice;
		int numOfOps;
		String optionName;
		
		String AutomobileName;
		
		try {
			file = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			throw new AutoException(101,"File name not found.");
		}
		
		BufferedReader buff = new BufferedReader(file);
		boolean eof = false;
		
		//Parse for Automobile Name, Base Price, and Number of Options
		String ln = buff.readLine();
		StringTokenizer firstLine = new StringTokenizer(ln, ",");
		AutomobileName = firstLine.nextToken();
		try {
			baseP = Integer.parseInt(firstLine.nextToken());
		} catch (NumberFormatException e)
		{
			throw new AutoException(102,"Missing Automobile Base Price");
		}
		try {
			numOfOpsets = Integer.parseInt(firstLine.nextToken());		
		} catch (NoSuchElementException e)
		{
			throw new AutoException(103,"Missing number of optionsets");
		}
		
		Automobile car = new Automobile(AutomobileName, baseP, numOfOpsets);
	
		//Parse Automobile Options
		int i = 0; //Optionset index position
		while (!eof || i < numOfOpsets) 
		{
			String line = buff.readLine();
			if (line == null)
				eof = true;
			else
			{
				//Get Optionset Name
				StringTokenizer st = new StringTokenizer(line, "=,");
				optionName = st.nextToken();
				try {
					numOfOps = Integer.parseInt(st.nextToken());
				} catch (NumberFormatException e)
				{
					throw new AutoException(104,"Missing number of options");
				}
				car.getOpset().add(i, new OptionSet(optionName, numOfOps));
				int j = 0;//Option index position
				//Get Options
				while(st.hasMoreTokens()) 
				{
					car.addOption(i, st.nextToken());
					try{
					optionPrice = Float.parseFloat(st.nextToken());
					} catch (NumberFormatException e)
					{
						throw new AutoException(105,"Missing Option Price");
					}
					car.setOptionPrice(i,j,optionPrice);
					j++;
				}
			}
			i++;
		}	
		buff.close();
		
		return car;
	}
	
	public void serialize(Automobile auto) throws IOException
	{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("A.dat"));
		out.writeObject(auto);
		out.close();
	}
	
	public Automobile deserialize() throws IOException, ClassNotFoundException
	{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("A.dat"));
		Automobile Automobile = (Automobile) in.readObject();
		in.close();
		return Automobile;
	}
	
	@SuppressWarnings("unused")
	public Properties parseProperties(String fileName)
	{
		Properties props= new Properties();
				
		FileInputStream in = null;
		try {
			in = new FileInputStream(fileName);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			props.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //This loads the entire file in memory.

		return props;
	}
	
	
}
