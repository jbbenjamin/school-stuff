import java.util.Scanner;
public class Loops {
	public static void main (String[] args){
		int i = 0, sum = 0;
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		
		for (i = 0; i < n; i++) 
		{
			sum += 3;
		}
		System.out.println("The value of sum after adding the number 3 " + n + " times is " + sum + ".");
	}

}
