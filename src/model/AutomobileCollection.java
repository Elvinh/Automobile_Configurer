package model;

import java.util.LinkedHashMap;

public class AutomobileCollection {

	private static LinkedHashMap<String,Automobile> autoMap;
	
	public AutomobileCollection()
	{
		autoMap = new LinkedHashMap<String,Automobile>();
	}
	
	public void addAuto(String modelName, Automobile auto)
	{
		autoMap.put(modelName, auto);
	}
	public void removeAuto(String modelName)
	{
		autoMap.remove(modelName);
	}
	public LinkedHashMap<String,Automobile> getAutoMap() {
		return autoMap;
	}
	public void setAutoMap(LinkedHashMap<String,Automobile> autoMap) {
		AutomobileCollection.autoMap = autoMap;
	}
	
	public Automobile getAuto(String modelName)
	{
		return autoMap.get(modelName);
	}
}
