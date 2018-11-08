import java.util.Scanner;
public class StringPermutation {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a word:");
		String word = input.nextLine();
		Permutation(word);
		
	}

	public static void Permutation(String s) {
		Permutation("", s);
	}
	
	public static void Permutation(String s1, String s2) {
			s1 = s1 + s2.charAt(0);
			s2 = s2.substring(1, s2.length());
			if(s2.length() < 1){
			System.out.println(s1);
		}
		else{
			
			for(int i = 0; i < s2.length(); i++){
				System.out.print(s2.charAt(i));
			}
			System.out.println(s1);
			
			
			Permutation(s1, s2);
		}	
	}
}


/*public static void Permutation(String s1, String s2) {
String[] wordArray = new String[s2.length()];
int i = 0;
String temp;
for(i = 0; i < s2.length(); i++){
	wordArray[i] = s2.substring(i, i);
}
	if(s2.length() < 1){
	System.out.println(s1);
}
else{
	for(i = 0; i < wordArray.length; i++){
		System.out.print(wordArray[i]);
	}
	temp = wordArray[i + 1];
	wordArray[i + 1] = wordArray[i];
	wordArray[i] = temp;
	System.out.println(wordArray);
	s1 = s1 + s2.charAt(0);
	s2 = s2.substring(1, s2.length());
	Permutation(s1, s2);*/