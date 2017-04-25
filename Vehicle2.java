import lejos.nxt.*;
import java.util.*;

public class Vehicle2 {
	public long time = System.currentTimeMillis();
	public double power1;
	public double power2;
	public double light1;
	public double light2;
	
	public int count = 0;

	public int max;
	public int min;
	
	public int[] samples = new int[1000];
	
	public LightSensor l1 = new LightSensor(SensorPort.S1);
	public LightSensor l2 = new LightSensor(SensorPort.S2);

	public static void main (String[] Arg)
			  throws Exception{
		  Vehicle2 vehicle = new Vehicle2();
		  while(!Button.ESCAPE.isDown()){
			  vehicle.callibrate();
			  vehicle.search();
			  
			 
			  
		  }
	  }
      public void callibrate(){
    	 
    	  samples[count] = l1.getLightValue();
    	  count ++;
    	  samples[count] = l2.getLightValue();
    	  count ++;
    	  
    	  max = samples[0];
    	  min = samples[0];
    	  for (int ktr = 0; ktr < samples.length; ktr++) {
		        if (samples[ktr] > max) {
		            max = samples[ktr];
		        }
		        if (samples[ktr] < min) {
		            min = samples[ktr];
		        }
    	  }
    	  
    	  if(count == 1000)
    		  count = 0;
      }
	
	  public  void search(){
		  light1 = (l1.getLightValue()- (double)min) / ((double)max - (double)min);
		  light2 = (l2.getLightValue()- (double)min)/ ((double)max - (double)min);

		  power1 = 30 + (70*light1);
		  power2 = 30 + (70*light2);

		  LCD.drawString(String.valueOf(light1), 5, 1);
		  LCD.drawString(String.valueOf(light2), 5, 2);

		  LCD.drawInt(max, 5, 4);
		  LCD.drawInt(min, 5, 5);
		  
		  LCD.drawInt(l1.getLightValue(), 5, 6);
		  LCD.refresh();
		  
		  Car.forward((int) power1, (int) power2);
		  
	  }
	
}
