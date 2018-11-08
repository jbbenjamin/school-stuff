import java.util.Scanner;
public class RightShift {
	public static void main(String[] args){
		int[] sampleArray = new int[10];
		int temp;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter " + sampleArray.length + " numbers:");
		
		for (int i = 0; i < sampleArray.length; i++)
		{
			sampleArray[i] = input.nextInt();
		}
		System.out.println("The array before right-shifting is:");
		
		for (int i = 0; i < sampleArray.length; i++)
			System.out.print(sampleArray[i] + " ");
		
		//Right Shift the elements in the array
		temp = sampleArray[sampleArray.length - 1];
		for (int i = sampleArray.length - 1; i > 0; i--)
		{
			sampleArray[i] = sampleArray[i - 1];
		}
		sampleArray[0] = temp;
		
		System.out.println("\n\nThe array after right-shifting is:");
		for (int i = 0; i < sampleArray.length; i++)
			System.out.print(sampleArray[i] + " ");
	}

}
