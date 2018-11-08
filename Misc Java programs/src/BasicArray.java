import java.util.Scanner;
public class BasicArray {
	public static void main(String[] args){
		
		final int LIMIT = 15, MULTIPLE = 10;
		int[] list = new int[LIMIT];
		
		//initialize array values
		for (int i = 0; i < LIMIT; i++){
			list[i] = i * MULTIPLE;
		}
		//print array values
		//modify array value with "list[index] = newValue"
		for (int i = 0; i < list.length; i++){
			System.out.println(list[i]);
		}
	}

}
