package drivers;
import java.io.*;

import exception.AutoException;
import model.Automobile;
import util.FileIO;
;
public class DriverLab1 {

	public static void main(String[] args) throws IOException, ClassNotFoundException, AutoException {
		FileIO io = new FileIO();
		
		Automobile automobile = io.readFile("AutomobileData.txt");
		automobile.printAll();
		io.serialize(automobile);
		
		System.out.print("\nDeserialized Object \n===================");
		Automobile automobile2 = io.deserialize();
		automobile2.printAll();
	}

	
}
// Output //
/*

Ford Focus Wagon ZTW
Base Price: 3132132.0

Colors:
"Fort Knox Gold Clearcoat Metallic" $0.0
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

Deserialized Object 
===================
Ford Focus Wagon ZTW
Base Price: 3132132.0

Colors:
"Fort Knox Gold Clearcoat Metallic" $0.0
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