package client;

import java.net.*;
import java.io.*;

import server.CreateServer;

public class DefaultSocketClient 
    extends Thread implements SocketClientInterface,
                              SocketClientConstants 
{
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Socket sock;
    private String strHost;
    @SuppressWarnings("unused")
	private InetAddress address;
    private int iPort;
    private CreateClient clientHelper;
    private CreateServer serverHelper;
    
    //Constructors
    public DefaultSocketClient(String strHost, int iPort) 
    {       
    	setPort (iPort);
    	setHost (strHost);
    }//constructor
    
    public DefaultSocketClient(int iPort) 
    {      
    	
    	try {
    		setPort (iPort);
			setHost(InetAddress.getLocalHost());
			System.out.println("Successfully opened socket on port " + iPort + " : " +  InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }//constructor
    
    public DefaultSocketClient(Socket socket)
    {
    	sock = socket;
    }//constructor
    
    //Getter/Setters
    private void setHost(InetAddress inetAddress) 
    {
		this.address = inetAddress;
	}
    private void setHost(String strHost1) 
    {
		this.strHost = strHost1;
	}
	private void setPort(int iPort2) 
	{
		this.iPort = iPort2;
	}
	
	public void setClientHelper(CreateClient cc) {
		this.clientHelper = cc;
	}
	public void setServerHelper(CreateServer cs) {
		// TODO Auto-generated method stub
		this.serverHelper = cs;
	}
	public Socket getSocket() 
	{
		return this.sock;
	}

	//----------------------------------------------------------
	public void run(){
		System.out.println("Attempting to open connection.");
       if (openConnection())
       {
    	   handleSession();
    	   closeSession();
       }
    }

	public boolean openConnection()
	{
    	if(sock == null)
    	{
    		try
    		{
    			sock = new Socket(strHost, iPort);
    		}
    		catch(IOException e)
    		{
    			e.printStackTrace();
    			return false;
    		}
    	}
    	try 
    	{
    		oos = new ObjectOutputStream(this.getSocket().getOutputStream());
 			ois = new ObjectInputStream(this.getSocket().getInputStream());
 			System.out.println("Successfully opened streams to/from " + iPort);
    	}
    	catch (Exception e)
    	{
    	     if (DEBUG) 
    	    	 System.err.println("Unable to obtain stream to/from " + strHost);
    	     return false;
    	}
    	return true;
	}

	public void handleSession() 
	{
    	Object input = null;
    	if(clientHelper != null)
    		clientHelper.selectServiceOption();
    	
    	while(true)
    	{
    		try
    		{
    			Thread.sleep(10);
    		}
    		catch(InterruptedException i)
    		{
    			i.printStackTrace();
    		}
	    	while((input = getInput()) != null)
		    {
	    		if(input.equals(CLIENT_EXIT))
	    		{
	    			//Exit handleSession()
	    			return;
	    		}
	    		else
	    			handleInput(input);
		    }
    	}


	}

	private void handleInput(Object input) {
		int cmd;
		cmd = readCommand(input);
		switch(cmd) {
			case SELECT_SERVICE:
			{
				clientHelper.selectServiceOption();
				break;
			}
			case PREFORM_SERVICE:
			{
				clientHelper.preformService(cmd);
    			break;
			}
			case PROP_TO_AUTO:
			{
				serverHelper.handleConnection(PROP_TO_AUTO);
				break;
			}
			case PRINT_AUTO:
			{
				serverHelper.handleConnection(PRINT_AUTO);
				break;
			}
			case SEND_AUTO:
			{
				serverHelper.handleConnection(SEND_AUTO);
				break;
			}
		}
	
	}
	
	public int readCommand(Object input)
	{
		int cmd = (Integer)input;
		return cmd;
	}
	
	public void sendOutput(Object out) {
		if(out == null)
		{
			System.out.println("Output is null.");
			return;
		}
		try {
			oos.writeObject(out);
			oos.flush();
		}
	    catch (IOException e)
	    {
	    	if (DEBUG) 
	    	{
	    		System.out.println("Error writing to " + strHost);
	    	}
	    }
	}
	
    public Object getInput()
    {
    	Object input = null;
		try {
			input = ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(input == null)
    	{
    		System.out.println("Null object recieved.");
    	}
    	return input;
    }
    
	public void closeSession()
	{
		try
		{
			oos.close();;
			ois.close();
			sock.close();
		}
		catch (IOException e)
		{
			if (DEBUG) System.err.println("Error closing socket to " + strHost);
		}       
	}//run


}


