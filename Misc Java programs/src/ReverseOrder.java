import java.util.Scanner;
public class ReverseOrder {
	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);
		double[] numbers = new double[5];
		
		System.out.println("The size of the array is " + numbers.length);
		
		for (int i = 0; i < numbers.length; i++){
			System.out.println("Enter the number " + (i+1) + ": ");
			numbers[i] = input.nextDouble();
		}
		System.out.println("The numbers in reverse order:");
		for (int i = numbers.length - 1; i >= 0; i--){
			System.out.println("" + numbers[i]);
		}
	}

}
