import lejos.nxt.*;


public class Vehicle1 {
	private SoundSensor sound = new SoundSensor(SensorPort.S1);
	private double volume;
	private double power;
	private boolean direction = true;
	private long time = System.currentTimeMillis();
	
	
	public static void main (String[] Arg) throws Exception {
		Vehicle1 vehicle = new Vehicle1();

		while(!Button.ESCAPE.isDown()) {
			vehicle.dance();	 
		}
	}
	  
	public  void dance() {
		if(System.currentTimeMillis() - time > 300){
			direction = !direction;

			time = System.currentTimeMillis();
		} 
		  
		if(SensorPort.S1.readRawValue() > 900) {
			volume = 0;
		}

		else {
			volume = 1 - ( (double) SensorPort.S1.readRawValue()/1023);
		}

		double displayValue = volume * 100;
	  
		LCD.drawInt((int) displayValue, 5, 5);
		LCD.refresh();
		  
		power = 100 - (volume*60);
		 
	 	if(direction) {
			Car.forward((int) power, -(int) power);
		}

		else {
			Car.forward(-(int) power, (int) power);
		}
	}
}
