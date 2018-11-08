
import java.util.Scanner;
public class Subtraction {

	public static void main(String[] args)
	{
		int number1 = (int) (Math.random() * 100);
		int number2 = (int) (Math.random() * 100);
		
		if (number1 < number2)
		{
			int temp = number1;
			number1 = number2;
			number2 = temp;
		}
		
		System.out.println("What is " + number1 + " - " + number2 + "?" );
		Scanner input = new Scanner (System.in);
		int answer = input.nextInt();
		
		if (number1 - number2 == answer)
			System.out.println("Yes, you are correct!");
		else
		{	
			System.out.println("Nope, your answer is wrong!");
			System.out.println("Here is the correct answer " + (number1 - number2));
		}	
	}
}
