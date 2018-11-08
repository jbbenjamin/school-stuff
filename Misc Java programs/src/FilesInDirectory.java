import java.util.Scanner;
public class FilesInDirectory {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the name of the directory:");
		java.io.File directory = new java.io.File(input.nextLine());
		if (directory.isDirectory() == true){
			java.io.File[] fileArray = directory.listFiles();
			System.out.println("Number of files in directory: " + fileArray.length); 
		}
		else{
			System.out.println("Directory does not exist.");
		}
	}
}
