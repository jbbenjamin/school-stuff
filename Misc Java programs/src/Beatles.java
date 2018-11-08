import java.util.ArrayList;

public class Beatles {
	public static void main(String[] args){
		
		ArrayList<String> band = new  ArrayList<String>();
		band.add("Paul");
		band.add("Pete");
		band.add("John");
		band.add("George");
		
		System.out.println(band);
		
		
		int location = band.indexOf("Pete");
		band.remove(location);
		System.out.println(band);
		
		int index = 0;
		while(index < band.size()){
			System.out.println(band.get(index));
			index += 1;
		}
	}

}
