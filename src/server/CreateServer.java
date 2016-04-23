package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import model.Automobile;
import adapter.BuildAuto;
import adapter.CreateAuto;
import adapter.GetAuto;
import client.DefaultSocketClient;
import client.SocketClientConstants;

public class CreateServer implements SocketClientConstants{
	
	private ServerSocket serverSock;
	DefaultSocketClient dsc;
	
	public CreateServer(int portNumber) {
		serverSock = null;
		try {
			serverSock = new ServerSocket(portNumber);
			serverSock.setSoTimeout(3600000);
			System.out.println("Successfully created server...");
		} catch (IOException e) {
		    System.err.println("Could not listen on port: "+portNumber+".");
		}
	}
	
	public void startServer()
	{
		while(true)
		{
			try {
				System.out.println("Waiting for a client to connect...");
				Socket clientSocket = serverSock.accept();
				dsc = new DefaultSocketClient(clientSocket);
				dsc.setServerHelper(this);
			}
			catch(IOException e)
				{
					System.err.println("Error --- " + e);
					System.out.println("Could not listen on port: 4040");
					System.exit(1);
				}
			System.out.println("Client has connected.");
			dsc.start();
		}
	}
	
	public void handleConnection(int request)
	{
		switch(request) {
			case PROP_TO_AUTO:
	    	{
				processPropertiesObj();
				break;
	    	}
			/*case PRINT_AUTO:
	    	{
	    		printAutoInfo();
	    		break;
	    	}*/
			case SEND_AUTO:
			{
				getAuto();
				break;
			}
		}
	}
	
	
	public void processPropertiesObj()
	{
		Object propertiesObj = null;
		
		propertiesObj = dsc.getInput();
		System.out.println("Properties object recieved from client.");
        AutoServer bAuto = new BuildAuto();
        bAuto.addAuto(propertiesObj);
        CreateAuto aAuto = new BuildAuto();
        System.out.println("Automobile added: ");
        aAuto.printAuto("Ford Focus Wagon ZTW");
	}
	
	/*public void printAutoInfo() {
		String autoName = null;
		System.out.println("Please enter name of automobile you would like information about: ");
		Scanner in = new Scanner(System.in);
		autoName = in.nextLine();
		Automobile auto;
		GetAuto gAuto = new BuildAuto();
		auto = gAuto.getAutoObj(autoName);
		auto.printAll();
	}*/
	
	public void getAuto() {
		GetAuto gAuto = new BuildAuto();
		System.out.println("Sending client list of automobile names.");
		Set<?> autoList = gAuto.getKeys();
		java.util.Iterator<?> iter = autoList.iterator();
		ArrayList<String> names = new ArrayList<>();
		while(iter.hasNext())
		{
			String s = (String) iter.next();
			names.add(s);
		}
		dsc.sendOutput(names);

		
		Automobile a1 = gAuto.getAutoObj((String) dsc.getInput());
		System.out.println("Sending client Automobile object.");
		dsc.sendOutput(a1);
	}
	
	public static void main (String arg[])
	{
		CreateServer server = new CreateServer(4040);
		server.startServer();
		server.stopServer();
	}

	private void stopServer() 
	{
		try
		{
			serverSock.close();
			dsc.closeSession();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
/*
Successfully created server...
Waiting for a client to connect...
Client has connected.
Waiting for a client to connect...
Attempting to open connection.
Successfully opened streams to/from 0
Properties object recieved from client.
Automobile object created from properties file.
Automobile added: 

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
Sending client list of automobile names.
Sending client Automobile object.

*/


