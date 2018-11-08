
public class Fan {
	
	public final int SLOW = 1;
	public final int MEDIUM = 2;
	public final int FAST = 3;
	
	private int speed = SLOW;
	private boolean on = false;
	private double radius = 5;
	private String color = "blue";
	
	public Fan() {
	}
	
	public boolean isOn() {
		return on;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public double getRaidus() {
		return radius;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setOn(boolean onOrOff) {
		on = onOrOff;
	}
	
	public void setSpeed(int newSpeed) {
		speed = newSpeed;
	}
	
	public void setRadius(double newRadius) {
		radius = newRadius;
	}
	
	public void setColor(String newColor) {
		color = newColor;
	}
	
	public String toString() {
		if (on == true) {
			return "Fan speed is " + speed + ", color is " + color  + ", and radius is " + radius;  
		}
			return "Fan color is " + color  + " and radius is " + radius + ". Fan is off.";
	}
	
}