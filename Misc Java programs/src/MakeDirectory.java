import java.util.Scanner;
public class MakeDirectory {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a name for the directory:");
		java.io.File directory = new java.io.File(input.nextLine());
		if (directory.mkdirs() == true){
			System.out.println("Directory created successfully");
		}
		else{
			System.out.println("Directory already exists");
			System.out.println(directory.listFiles().toString());
		}
		/*java.io.File directory = new java.io.File("testDirectory1");
		System.out.println(directory.exists());
		System.out.println(directory.isDirectory());
		System.out.println(directory.getAbsolutePath());*/
	}
}
