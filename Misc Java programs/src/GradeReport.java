import java.util.Scanner;

public class GradeReport {
	public static void main(String[] args){
		
		int grade, category;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the numeric grade (0 to 100):");
		grade = scan.nextInt();
		category = grade/10;
		System.out.println("That grade is");
		switch(category)
		{
		case 10:
			System.out.println("A perfect score. Well done.");
			break;
		case 9:
			System.out.println("Well above average. Excellent.");
			break;
		case 8:
			System.out.println("Above average. Nice job.");
			break;
		case 7:
			System.out.println("Average");
			break;
		case 6:
			System.out.println("Below Average. You need to work hard");
			break;
		default:
			System.out.println("Not passing.");
		}
		
		}
	}
