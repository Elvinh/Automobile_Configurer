package adapter;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import exception.AutoException;
import server.BuildCarModelOptions;
import util.FileIO;
import model.*;

public abstract class proxyAutomobile {
	private static AutomobileCollection autoColl = new AutomobileCollection();
	
	public AutomobileCollection getAutoColl() {
		return autoColl;
	}

	public void updateOptionSetName(String modelName, String optionSetName,
			String newName) 
	{
		int opsetIndex = autoColl.getAutoMap().get(modelName).findOpset(optionSetName);
		autoColl.getAuto(modelName).setOptionsetName(opsetIndex, newName);
	}

	public void updateOptionPrice(String modelName, String optionSetName,
			String optionName, float newPrice) 
	{
		int opsetIndex = autoColl.getAutoMap().get(modelName).findOpset(optionSetName);
		int opIndex = autoColl.getAutoMap().get(modelName).findOption(optionSetName, optionName);
		autoColl.getAuto(modelName).setOptionPrice(opsetIndex, opIndex, newPrice);
	}

	public void buildAuto(String fileName) throws AutoException 
	{
		Automobile a1 = null;
		FileIO io = new FileIO();
		do {
			try {
				a1 = io.readFile(fileName);
			} catch (AutoException e) {
				if( e.geterrorNo()==101)
				{
					fileName = e.fixFileNotRead();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while(a1 == null);
		autoColl.addAuto(a1.getName(), a1);
	}

	public void printAuto(String modelName) 
	{
		try {
			autoColl.getAutoMap().get(modelName).printAll();
		} catch (NullPointerException e) {
			 new AutoException(106,"Model not found.");
		}
	}
	
	public void addAuto(Object obj)
	{
		Properties prop = (Properties)obj;
		BuildCarModelOptions s = new BuildCarModelOptions();
		Automobile auto = s.createAutomobile(prop);
		System.out.println("Automobile object created from properties file.");
		autoColl.addAuto(auto.getName(), auto);
        //call methods from BuildCarModelOptions
	}
	
	public Automobile getAutoObj(String name) {
		return autoColl.getAutoMap().get(name);
	}
	
	public Set<?> getKeys() {
		return autoColl.getAutoMap().keySet();
	}
}
