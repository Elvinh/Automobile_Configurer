package drivers;
import scale.EditOptions;
import exception.AutoException;
import adapter.BuildAuto;

public class DriverLab4 {

	public static void main(String[] args) throws AutoException {
		BuildAuto auto = new BuildAuto();
		auto.buildAuto("AutomobileData.txt");
		auto.printAuto("Ford Focus Wagon ZTW");
		
		EditOptions EO1 = new EditOptions("Thread - 1 ",0);
		EditOptions EO2 = new EditOptions("Thread - 2 ",0);
	    
		EO1.start();
		EO2.start();	
      
		auto.printAuto("Ford Focus Wagon ZTW");
	}

}
