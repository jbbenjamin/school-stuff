import java.util.Scanner;
public class CreatingExceptions{
	
	public static void main(String[] args) throws OutOfRangeException{
		
		final int MIN = 25, MAX = 40;
		
		Scanner input = new Scanner(System.in);
		
		OutOfRangeException problem = new OutOfRangeException("Input is out of range!");
		System.out.println("Enter an integer between " + MIN + " and " + MAX + " inclusive");
		int value = input.nextInt();
		if(value < MIN || value > MAX)
			throw problem;
		System.out.println("End of main method.");
	}

}
