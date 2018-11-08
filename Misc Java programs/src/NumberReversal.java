import java.util.Scanner;

public class NumberReversal {
	public static void main(String[] args){

		int number, lastDigit, reverse = 0;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a positive integer.");
		number = input.nextInt();
		
		do
		{
			lastDigit = number % 10;
			reverse = (reverse * 10) + lastDigit;
			number = number / 10;
		}
		while (number > 0);
		
		System.out.println("The reversed number is: " + reverse);
	}
}
