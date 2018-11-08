import java.util.Scanner;
public class AdditionQuiz {

	public static void main(String[] args){
		int number1 = (int)(Math.random() * 10);
		int number2 = (int)(Math.random() * 10);
		
		Scanner input = new Scanner(System.in);
		System.out.println("What is the value of " + number1 + " + " + number2 + "?");
		int answer = input.nextInt();
		
		while(number1 + number2 != answer)
		{
			System.out.println("Wrong anwser. Try again!");
			answer = input.nextInt();
		}
		System.out.println("Correct!");
	}
}
