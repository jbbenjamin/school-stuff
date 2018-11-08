import java.util.Arrays;


public class RandomShuffling {
	public static void main(String[] args){
		int[] myList = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		System.out.println("Original: " + Arrays.toString(myList));
		
		for(int i = myList.length - 1; i > 0; i--){
			int j = (int) (Math.random() * (i + 1));
			
			int temp = myList[i];
			myList[i] = myList[j];
			myList[j] = temp;
		}
		
		System.out.println("Shuffled: " + Arrays.toString(myList));
	}

}
