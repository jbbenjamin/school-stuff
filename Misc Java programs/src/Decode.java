import java.util.Stack;
import java.util.Scanner;
public class Decode {
	public static void main(String[]args){
		
		Scanner input = new Scanner(System.in);
		Stack word = new Stack();
		
		String message;
		int index = 0;
		System.out.println("Enter the coded message: ");
		
		message = input.nextLine();
		
		System.out.println("The decoded message: ");
		
		while(index < message.length()){
			
			while(index < message.length() && message.charAt(index) != ' '){
				word.push(new Character (message.charAt(index)));
				index++;
			}
		System.out.println(word.toString());
		/*while (!word.empty()){
			//System.out.println(((Character)word.pop()).charValue());
			index = 0;
			System.out.println(word.pop());
		
		System.out.println(" ");
		index++;
		}*/
		}
	}

}
