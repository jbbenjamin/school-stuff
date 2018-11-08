import java.util.Scanner;

public class TestAverage {
	public static void main(String[] args){
		
		int score1, score2, score3;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter three test scores:");
		score1 = scan.nextInt();
		score2 = scan.nextInt();
		score3 = scan.nextInt();
		
		double average = (score1 + score2 + score3)/3.00;
		
		System.out.println("Test 1: " + score1);
		System.out.println("Test 2: " + score2);
		System.out.println("Test 3: " + score3);
		System.out.println("Average: " + average);
	}
}
