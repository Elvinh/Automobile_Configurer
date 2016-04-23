package drivers;
import java.io.IOException;

import exception.AutoException;
import adapter.*;


public class DriverLab2 {

	public static void main(String[] args) throws IOException, AutoException 
	{
		System.out.println("Lab 2 Driver created by Elton Vinh");
		BuildAuto test = new BuildAuto();
		test.buildAuto("AutomobileData.txt");//Wrong file name to test exception handling
		test.printAuto("Ford Focus Wagon ZTW");
		System.out.println("\nUpdated values:\n===============");
		test.updateOptionSetName("Ford Focus Wagon ZTW", "Colors", "Color");
		test.updateOptionPrice("Ford Focus Wagon ZTW", "Color", "Fort Knox Gold Clearcoat Metallic", 1);
		test.printAuto("Ford Focus Wagon ZTW");
	}
}

//Output //
/*
Lab 2 Driver created by Elton Vinh
AutoException[errorNo=101, errormsg=File name not found.]
Enter file name(HINT: AutomobileData.txt).
AutomobileData.txt

Ford Focus Wagon ZTW
Base Price: 3132132.0

Colors:
Fort Knox Gold Clearcoat Metallic $0.0
"Liquid Grey Clearcoat Metallic" $0.0
"Infra Red Clearcoat" $0.0
"Grabber Green Clearcoat Metallic" $0.0
"Sangria Red Clearcoat Metallic" $0.0
"French Blue Clearcoat Metallic" $0.0
"Twilight Blue Clearcoat Metallic" $0.0
"CD Silver Clearcoat Metallic" $0.0
"Pitch Black Clearcoat" $0.0
"Cloud 9 White Clearcoat" $0.0

Transmission:
Automatic $0.0
Transmission $-815.0

Brakes:
standard $0.0
ABS $400.0
ABSAdvTrac $1625.0

Side Impact Air Bags:
notPresent $0.0
present $350.0

Power Moonroof:
notPresent $0.0
present $595.0

Updated values:
===============

Ford Focus Wagon ZTW
Base Price: 3132132.0

Color:
Fort Knox Gold Clearcoat Metallic $1.0
"Liquid Grey Clearcoat Metallic" $0.0
"Infra Red Clearcoat" $0.0
"Grabber Green Clearcoat Metallic" $0.0
"Sangria Red Clearcoat Metallic" $0.0
"French Blue Clearcoat Metallic" $0.0
"Twilight Blue Clearcoat Metallic" $0.0
"CD Silver Clearcoat Metallic" $0.0
"Pitch Black Clearcoat" $0.0
"Cloud 9 White Clearcoat" $0.0

Transmission:
Automatic $0.0
Transmission $-815.0

Brakes:
standard $0.0
ABS $400.0
ABSAdvTrac $1625.0

Side Impact Air Bags:
notPresent $0.0
present $350.0

Power Moonroof:
notPresent $0.0
present $595.0

*/