import java.util.Scanner;

public class CalculateAverage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Create a Scanner object
		Scanner input = new Scanner(System.in);
		
		//Prompt user to input numbers
		System.out.println("Enter four numbers: ");
		double number1 = input.nextDouble();
		double number2 = input.nextDouble();
		double number3 = input.nextDouble();
		double number4 = input.nextDouble();
		
		//Calculates Average
		double average = (number1 + number2 + number3 + number4)/4;
		
		//Displays results
		System.out.println("The average of " + number1 + " " + number2 + " " + number3 + " " + number4 + " is " + average);
	}

}
