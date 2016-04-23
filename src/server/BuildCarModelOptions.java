package server;

import java.util.Enumeration;
import java.util.Properties;

import model.Automobile;
import util.FileIO;

public class BuildCarModelOptions
{
	public BuildCarModelOptions() {	
	}
	
	public Automobile createAutomobile(Properties prop)
	{
		FileIO fi = new FileIO();
		prop = fi.parseProperties("FordZTWProperties.txt");
		Automobile auto = null;
	
		String modelName = prop.getProperty("CarMake") + " " + prop.getProperty("CarModel");
		Float basePrice = Float.parseFloat(prop.getProperty("BasePrice"));
		int numOfOpsets = Integer.parseInt(prop.getProperty("NumberOfOpsets"));
		auto = new Automobile(modelName, basePrice, numOfOpsets);
		
		Enumeration<?> e = prop.propertyNames();
		
		while(e.hasMoreElements())
		{
			String key = (String) e.nextElement();
			//System.out.println(key + " -- " + prop.getProperty(key));

			if(key.contains("OptionsetName")) {
				int opsetIndex = Character.getNumericValue(key.charAt(13))-1;
				String opsetName = prop.getProperty(key);
				auto.setOptionsetName(opsetIndex, opsetName);
			}
			if(key.contains("OptionValue")) {		
				int opsetIndex = Character.getNumericValue(key.charAt(11))-1;
				auto.addOption(opsetIndex,  prop.getProperty(key));

			}
			if(key.contains("OptionPrice")) {		
				int opsetIndex = Character.getNumericValue(key.charAt(11))-1;
				int oIndex = auto.findOption(opsetIndex, prop.getProperty("OptionValue" + key.charAt(11) + key.charAt(12)));
				if(!auto.setOptionPrice(opsetIndex, oIndex, Float.parseFloat(prop.getProperty(key)))) {
					auto.addOption(opsetIndex, prop.getProperty("OptionsetName" + key.charAt(11)));
				}
			}
		}
		
		return auto;
	}
	
	public void addAutoToLHM(Automobile a1)
	{
	}
}
