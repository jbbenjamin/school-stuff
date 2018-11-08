import java.util.Scanner;
public class MiniQuiz {
	public static void main(String[] args){
		
		Question q1, q2;
		
		String possible;
		Scanner input = new Scanner(System.in);
		
		q1 = new Question("What is capitol of Illinois?", "Springfield");
		q1.setComplexityLevel(4);
		
		System.out.println(q1.getQuestion());
		System.out.println(" (Level: " + q1.getComplexity() + ")");
		possible = input.nextLine();
		
		if(q1.answerCorrect(possible))
			System.out.println("Correct");
		else
			System.out.println("No, the answer is :" + q1.getAnswer());
	}

}
