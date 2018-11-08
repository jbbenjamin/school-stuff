import java.util.Scanner;
public class InputOutputTest {
	public static void main(String[] args) throws Exception {
		java.io.File file = new java.io.File("Question2.txt");
		
		if (file.exists() == true){
			System.out.println("File already exists.");
			System.exit(1);
		}
		
		int[] array = new int[100];
		java.io.PrintWriter output = new java.io.PrintWriter(file);
		java.util.Random random = new java.util.Random();
		for(int i = 0; i < 100; i++){
			int numi = random.nextInt();
			array[i] = numi;
			output.print(numi);
			output.print(" ");
		}
		
		output.close();
		
		java.util.Arrays.sort(array);
		Scanner input = new Scanner(file);
		for (int i = 0; i < 100; i++){
			System.out.print(input.nextInt() + " ");
		}
		
		input.close();
	}

}
