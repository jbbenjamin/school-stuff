
public class TestSimpleCircle {
	public static void main(String[] ars){
		SimpleCircle circle1 = new SimpleCircle();
		System.out.println("The area of the circle of radius " + circle1.radius + " is " + circle1.getArea());
		SimpleCircle circle2 = new SimpleCircle(20);
		System.out.println("The area of the circle of radius " + circle2.radius + " is " + circle2.getArea());
		SimpleCircle circle3 = new SimpleCircle(50);
		System.out.println("The area of the circle of radius " + circle3.radius + " is " + circle3.getArea());
		circle2.radius = 100;
		System.out.println(circle2.getArea());
	}
}
	
	class SimpleCircle{
		
		double radius;
		SimpleCircle(){
			radius = 1;
		}
		SimpleCircle(double newRadius){
			radius = newRadius;
		}
		
		double getArea()
		{
			return radius * radius *Math.PI;
		}
		
		double getCircumference()
		{
			return 2 * Math.PI * radius;
		}
		
		void setRadius (double newRadius){
			radius = newRadius;
		}
	}

