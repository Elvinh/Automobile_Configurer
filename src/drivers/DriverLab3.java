package drivers;

import model.Automobile;
import exception.AutoException;
import adapter.BuildAuto;

public class DriverLab3 {
	
	public static void main(String[] args) throws AutoException
	{
		System.out.println("Lab 3 Driver created by Elton Vinh");
		BuildAuto test = new BuildAuto();
		test.buildAuto("AutomobileData.txt");
		test.printAuto("Ford Focus Wagon ZTW");
		test.updateOptionSetName("Ford Focus Wagon ZTW", "Colors", "COLORS");
		test.updateOptionPrice("Ford Focus Wagon ZTW", "COLORS", "Fort Knox Gold Clearcoat Metallic", 2);
		test.printAuto("Ford Focus Wagon ZTW");
	
		test.getAutoColl().getAutoMap().get("Ford Focus Wagon ZTW").setOptionChoice("COLORS", "Fort Knox Gold Clearcoat Metallic");
		test.getAutoColl().getAutoMap().get("Ford Focus Wagon ZTW").setOptionChoice("Transmission", "Transmission");
		test.getAutoColl().getAutoMap().get("Ford Focus Wagon ZTW").setOptionChoice("Brakes", "ABS");
		test.getAutoColl().getAutoMap().get("Ford Focus Wagon ZTW").setOptionChoice("Side Impact Air Bags", "present");
		test.getAutoColl().getAutoMap().get("Ford Focus Wagon ZTW").setOptionChoice("Power Moonroof", "present");
		
		System.out.println("\nOptions Choosen: ");
		System.out.print(test.getAutoColl().getAutoMap().get("Ford Focus Wagon ZTW").getOptionChoice("COLORS"));
		System.out.println(test.getAutoColl().getAutoMap().get("Ford Focus Wagon ZTW").getOptionChoicePrice("COLORS"));
		System.out.print(test.getAutoColl().getAutoMap().get("Ford Focus Wagon ZTW").getOptionChoice("Transmission"));
		System.out.println(test.getAutoColl().getAutoMap().get("Ford Focus Wagon ZTW").getOptionChoicePrice("Transmission"));
		System.out.print(test.getAutoColl().getAutoMap().get("Ford Focus Wagon ZTW").getOptionChoice("Brakes"));
		System.out.println(test.getAutoColl().getAutoMap().get("Ford Focus Wagon ZTW").getOptionChoicePrice("Brakes"));
		System.out.print(test.getAutoColl().getAutoMap().get("Ford Focus Wagon ZTW").getOptionChoice("Side Impact Air Bags"));
		System.out.println(test.getAutoColl().getAutoMap().get("Ford Focus Wagon ZTW").getOptionChoicePrice("Side Impact Air Bags"));
		System.out.print(test.getAutoColl().getAutoMap().get("Ford Focus Wagon ZTW").getOptionChoice("Power Moonroof"));
		System.out.println(test.getAutoColl().getAutoMap().get("Ford Focus Wagon ZTW").getOptionChoicePrice("Power Moonroof"));
		System.out.print("\nTotal price: $");
		System.out.println(test.getAutoColl().getAutoMap().get("Ford Focus Wagon ZTW").getTotalPrice());
		
		test.getAutoColl().removeAuto("Ford Focus Wagon ZTW");
		System.out.println("Attempting to print after model removed from hash map.");
		test.printAuto("Ford Focus Wagon ZTW");
		
		Automobile auto = new Automobile("The Elton Wagon", 99999.99f);
		test.getAutoColl().addAuto("The Elton Wagon", auto);
	}
}
