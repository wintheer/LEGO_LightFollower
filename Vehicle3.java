import lejos.nxt.*;
import java.util.*;

public class Vehicle3 {
	public long time = System.currentTimeMillis();
	public double power1;
	public double power2;
	public double light1;
	public double light2;
	
	public int afstand1;
	public int afstand2;
	
	public int count = 0;

	public int max;
	public int min;
	
	public int[] samples = new int[1000];
	
	public LightSensor l1 = new LightSensor(SensorPort.S1);
	public LightSensor l2 = new LightSensor(SensorPort.S2);
	public UltrasonicSensor s1 = new UltrasonicSensor(SensorPort.S3);
	public UltrasonicSensor s2 = new UltrasonicSensor(SensorPort.S4);

	
	public static void main (String[] Arg)
			  throws Exception{
		  Vehicle3 vehicle = new Vehicle3();
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
		  if(s1.getDistance() < 40){
			  afstand1 =   60 - s1.getDistance();
		  }
		  else
			  afstand1 = 0;
		  
		  if(s2.getDistance() < 40){
			  afstand2 =   60 - s2.getDistance();
		  }
		  else
			  afstand2 = 0;
		  
		  light1 = (l1.getLightValue()- (double)min) / ((double)max - (double)min);
		  light2 = (l2.getLightValue()- (double)min)/ ((double)max - (double)min);

		  power1 =  40 + (60*light1);
		  power2 =  40 +(60*light2);

		  LCD.drawString(String.valueOf(light1), 5, 1);
		  LCD.drawString(String.valueOf(light2), 5, 2);

		  LCD.drawInt(max, 5, 4);
		  LCD.drawInt(min, 5, 5);
		  
		  LCD.drawInt(l1.getLightValue(), 5, 6);
		  LCD.refresh();
		  
		  Car.forward((int) power1 - afstand1, (int) power2 - afstand2);
		  
	  }
	
}
