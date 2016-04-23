package scale;
import java.util.Scanner;

import adapter.proxyAutomobile;

public class EditOptions extends proxyAutomobile implements ScaleThread, Runnable{
	Thread t1;
	int ops;
	String threadName;
	
	public EditOptions(String tn, int ops)
	{
		threadName = tn;
		this.ops = ops;
	}
	
	public void run() {
		switch(ops)
        {
           case 0:  
        	   updateOptionSetName(); 
        	   break;
           case 1: 
        	   updateOptionPrice(); 
        	   break;
        }
		
	}
	
   public void start () {
	   System.out.println("\nStarting " +  threadName );
    	if(t1 == null)
    	{
	    	t1 = new Thread(this, threadName);
	    	t1.start();
            try {
          	   Thread.sleep(15000);
             } catch(InterruptedException e) {
                 System.out.println("Interrupted!");
             }
    	}
    }

    public void stop () {
    }    

	public synchronized void updateOptionSetName() 
	{
 	   	@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
 	   	System.out.println("modelname?");
 	    System.out.println("Ford Focus Wagon ZTW");
   	   	System.out.println("opname?");
   	   	String optionSetName = in.nextLine();
	   	System.out.println("newname?");
	   	String newName = in.nextLine();
	   	super.updateOptionSetName("Ford Focus Wagon ZTW", optionSetName, newName);
	}

	public synchronized void updateOptionPrice() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("modelname?");
		String modelName = in.nextLine();
		System.out.println("opsetName?");
		String optionSetName = in.nextLine();
		System.out.println("opName?");
		String optionName = in.nextLine();
		System.out.println("price?");
		int newPrice = in.nextInt();
		super.updateOptionPrice("Ford Focus Wagon ZTW", optionSetName, optionName, newPrice);
	}

}
