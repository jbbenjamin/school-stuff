import java.util.Scanner;
public class Question2 {
	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);
		double[] temperatureArray = new double[10];
		double total = 0;
		double average;
		int count = 0;
	
		System.out.println("Enter " + temperatureArray.length + " temperatures:");
		for(int i = 0; i < temperatureArray.length; i++)
			temperatureArray[i] = input.nextDouble();
	
		for(int i = 0; i < temperatureArray.length; i++)
			total += temperatureArray[i];
	
		average = total/temperatureArray.length;
	
		for(int i = 0; i < temperatureArray.length; i++)
		{
			if (temperatureArray[i] > average)
				count++;
		}
	
		System.out.println("There were " + count + " days hotter than the average temperature.");
	}

}
