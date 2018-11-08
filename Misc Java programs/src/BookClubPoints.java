import java.util.Scanner;

public class BookClubPoints {
	public static void main(String[] args){

		int books;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Please enter the number of books you have purchased this month:");
		books = scan.nextInt();
		
		if (books == 0)
			System.out.println("You have earned 0 points.");
		
		else if (books == 1)
			System.out.println("You have earned 5 points.");
		
		else if (books == 2)
			System.out.println("You have earned 15 points.");
		
		else if (books == 3)
			System.out.println("You have earned 30 points.");
		
		else
			System.out.println("You have earned 60 points.");
	}
}
