import java.util.Scanner;
public class Question3 {
	public static void main(String[] args){
	
	Scanner input = new Scanner(System.in);
	double[] myArray = new double[5];
	
	for (int i = 0; i < myArray.length; i++)
		myArray[i] = input.nextDouble();
	
	double sum = sumArray(myArray);
	System.out.println("The sum of the numbers is " + sum);
	}
	
	public static double sumArray(double[] array) {
		double total = 0;
		for (int i = 0; i < array.length; i++)
			total += array[i];
		
		return total;
	}

}
