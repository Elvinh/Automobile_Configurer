package client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import model.Automobile;

public class CreateClient implements SocketClientConstants{
	private DefaultSocketClient dsocketClient;
	
	public CreateClient()
	{
		@SuppressWarnings("unused")
		String strLocalHost = "";
		try{
			strLocalHost = 
				InetAddress.getLocalHost().getHostName();
		}
		catch (UnknownHostException e){
			System.err.println ("Unable to find local host");
		}
		dsocketClient = new DefaultSocketClient(4040);
		dsocketClient.setClientHelper(this);
		dsocketClient.start();
	}
	
	public void selectServiceOption() {
		System.out.println("Menu");
		System.out.println("(1) Send Properties Object to be added to database.");
		//System.out.println("(2) Get details of specific model.");
		System.out.println("(2) Configure a car.");
		Scanner in = new Scanner(System.in);
		int choice = in .nextInt();
		preformService(choice);

	}
	
	public void preformService(int choice)
	{
		switch(choice) {
			case 1:
				buildAutoFromPropObj();
				break;
			/*case 2:
				dsocketClient.sendOutput(PRINT_AUTO);
				break;*/
			case 2: 
				carConfiguration();
				break;
		}
		exit();
	}
	
	public void exit()
	{
		System.out.println("Would you like to continue? (Y/N) ");
		Scanner in = new Scanner(System.in);
		String response = in .nextLine();
		if(response.equalsIgnoreCase("y"))
		{
			selectServiceOption();
		}
		else if(response.equalsIgnoreCase("n"))
		{
			System.out.println("Ending session...");
			dsocketClient.closeSession();
			in.close();
		}
		else
		{
			System.out.println("Invalid input. Please Try again.");
			exit();
		}
		
	}
	
	public void buildAutoFromPropObj()
	{
		CarModelOptionsIO cmoio = new CarModelOptionsIO();
		System.out.println("Please enter file name.(FordZTWProperties.txt)");
		Scanner in = new Scanner(System.in);
		String fileName = in.nextLine();
		Properties props = cmoio.readData(fileName);
		dsocketClient.sendOutput(PROP_TO_AUTO);
		dsocketClient.sendOutput(props);

	}
	public void  carConfiguration()
	{
		dsocketClient.sendOutput(SEND_AUTO);
		@SuppressWarnings("unchecked")
		ArrayList<String> autoList = (ArrayList<String>) dsocketClient.getInput();
		java.util.Iterator<?> iter = autoList.iterator();
		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}
		
		System.out.println("Enter the name of the car you wish to configure.");
		Scanner in = new Scanner(System.in);
		String autoName = in.nextLine();
		dsocketClient.sendOutput(autoName);
		
		Automobile a1 = (Automobile) dsocketClient.getInput();
		a1.printAll();
		a1.setChoices();

	}
	public static void main(String [] args)
	{		
		@SuppressWarnings("unused")
		CreateClient client = new CreateClient();
	}
}
/*
 * Output:
 * Successfully opened socket on port 4040 : EltonsDesktopPC/192.168.1.34
Attempting to open connection.
Successfully opened streams to/from 4040
Menu
(1) Send Properties Object to be added to database.
(2) Configure a car.
1
Please enter file name.
FordZTWProperties.txt
Would you like to continue? (Y/N) 
y
Menu
(1) Send Properties Object to be added to database.
(2) Configure a car.
2
Ford Focus Wagon ZTW
Enter the name of the car you wish to configure.
Ford Focus Wagon ZTW

Ford Focus Wagon ZTW
Base Price: 18445.0

Color:
Cloud 9 White Clearcoat $0.0
Pitch Black Clearcoat $0.0
CD Silver Clearcoat Metallic $0.0
Twilight Blue Clearcoat Metallic $0.0
French Blue Clearcoat Metallic $0.0
Sangria Red Clearcoat Metallic $0.0
Grabber Green Clearcoat Metallic $0.0
Infra-Red Clearcoat $0.0
Liquid Grey Clearcoat Metallic $0.0
Fort Knox Gold Clearcoat Metallic $0.0

Transmission:
Manual $-815.0
Automatic $0.0

Brakes/Traction Control:
ABS with Advance Trac $1625.0
ABS $400.0
Standard $0.0

Side Impact Air Bags:
Not Present $0.0
Present $350.0

Power Moonroof:
Not Present $0.0
Present $595.0
Enter choice for Color
Cloud 9 White Clearcoat
Enter choice for Transmission
Manual
Enter choice for Brakes/Traction Control
ABS
Enter choice for Side Impact Air Bags
Present
Enter choice for Power Moonroof
Present
Total price for configured car: 18975
Would you like to continue? (Y/N) 
n
Ending session...
*/

