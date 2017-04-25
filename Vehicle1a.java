import lejos.nxt.*;


public class Vehicle1a {
	public SoundSensor sound = new SoundSensor(SensorPort.S1);
	public double volume;
	public double power;
	public boolean direction = true;
	public long time = System.currentTimeMillis();
	
	
	public static void main (String[] Arg) throws Exception {
		Vehicle1a vehicle = new Vehicle1a();

		while(!Button.ESCAPE.isDown()) {
			vehicle.dance(); 
		}
	}
	  
	public  void move() {
		volume = (double) SensorPort.S1.readRawValue()/1023;
		power = -100 + 200*volume;
		 
		if(power > 0) {
			Car.forward(Math.abs((int) power),Math.abs((int) power));
		}
		else {
			Car.backward(Math.abs((int) power),Math.abs((int) power));
		}
	}
}
