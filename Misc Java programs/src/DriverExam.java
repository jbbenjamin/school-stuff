import java.util.Scanner;
import java.util.Arrays;
public class DriverExam {
	private char[] correct = {'B', 'D', 'A', 'A', 'C', 'A', 'B', 'A', 'C', 'D', 'B', 'C', 'D', 'A', 'D', 'C', 'C', 'B', 'D', 'A'};
	private char[] student = new char[correct.length];
	private int[] missed;
	private int numCorrect = 0;
	private int numIncorrect = 0;
	
	public DriverExam(char[] s){
		for(int i = 0; i < correct.length; i++)
			student[i] = s[i];
	}
	
	private void gradeExam(){
		for(int i = 0; i < correct.length; i++){
			if(student[i] == correct[i])
				numCorrect++;
			else
				numIncorrect++;
		}
	}
	
	private void makeMissedArray(){
		missed = new int[numIncorrect];
		for(int i = 0, j = 0; i < student.length; i++){
			if(student[i] != correct[i]){
				missed[j] = i + 1;
				j++;
			}
		}
	}
	
	public boolean passed(){
		if(numCorrect >= 15)
			return true;
		else
			return false;
	}
	
	public int totalCorrect(){
		return numCorrect;
	}
	
	public int totalIncorrect(){
		return numIncorrect;
	}
	
	public int[] questionsMissed(){
		return missed;
	}

	
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		char[] array = new char[20];
		
		System.out.println("Enter your answers to the exam questions. (Make sure Caps Lock is ON!)\n");
		for(int i = 0; i < 20; i++){
			final String choiceA = "A"; 
			final String choiceB = "B"; 
			final String choiceC = "C";
			final String choiceD = "D";
			System.out.print("Question " + (i + 1) + ": ");
			String answeri = input.nextLine();
			if(answeri.equals(choiceA) || answeri.equals(choiceB) || answeri.equals(choiceC) || answeri.equals(choiceD))
				array[i] = answeri.charAt(0);
			else{
				System.out.println("ERROR: Valid answers are A, B, C, or D.");
				i--;
			}
		}
		
		DriverExam Exam1 = new DriverExam(array);
		Exam1.gradeExam();
		System.out.println("Correct answers: " + Exam1.totalCorrect());
		System.out.println("Incorrect answers: " + Exam1.totalIncorrect());
		
		if (Exam1.passed() == true)
			System.out.println("You passed the exam.");
		else
			System.out.println("You did not pass the exam.");
		
		if(Exam1.numIncorrect != 0){
			Exam1.makeMissedArray();
			System.out.println("You missed the following questions:");
			System.out.println(Arrays.toString(Exam1.questionsMissed()));
		}	
	}
}