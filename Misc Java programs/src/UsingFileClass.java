import java.io.File;
public class UsingFileClass {
	
	public static void main(String[] args){
		
		File file = new File("C:\\Users\\Justyn\\Desktop\\hash.txt");
		System.out.println("Does it exist? " + file.exists());
		System.out.println("The file has length " + file.length());
		System.out.println("Can it be read? " + file.canRead());
		System.out.println("Is it hidden? " + file.isHidden());
		System.out.println("Absolute path is " + file.getAbsolutePath());
		
		file.mkdir();
		File file2 = new File("C:\\Users\\Justyn\\Desktop\\hashnew.txt");
		file.renameTo(file2);
	}

}
