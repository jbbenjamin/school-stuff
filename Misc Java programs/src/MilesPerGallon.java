import java.util.Scanner;

public class MilesPerGallon {
	public static void main(String[] args){
		
		int miles, gallons;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter the number of miles driven:");
		miles = scan.nextInt();
		
		System.out.println("Enter the number of gallons used:");
		gallons = scan.nextInt();
		
		double mpg = (double) miles/gallons;
				
		System.out.println("Your car gets " + mpg + " miles per gallon.");
	}
}
