import java.util.Scanner;
public class ReverseString {
	public static void main(String[] args){
	Scanner input = new Scanner(System.in);
	
	System.out.println("Enter a word:");
	String word = input.nextLine();
	reverseDisplay(word);
	}
	
	public static void reverseDisplay(String value) {
		String reverse = "";
		char letter = value.charAt(value.length() - 1);
		reverse = reverse + letter;
		
		if(value.length() <= 1){
			System.out.println(reverse);
		}
		else{
			System.out.print(reverse);
			value = value.substring(0, value.length() - 1);
			reverseDisplay(value);
		}
	}

}
