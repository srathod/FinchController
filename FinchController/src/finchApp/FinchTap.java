package finchApp;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class FinchTap {
    public static void main (String[] args) throws Exception
    {
        Finch myf = new Finch();

        myf.setWheelVelocities(100,100,5000);
        long before = System.currentTimeMillis();
	        while(System.currentTimeMillis() - before < 5000)
	        {
	            Thread.sleep(500);
	            if (myf.isTapped()) break;
	
	        }
	        myf.stopWheels();
	        myf.quit();
        
    }
}