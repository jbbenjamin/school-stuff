import java.util.ArrayList;
import java.util.Scanner;
public class SortArrayList {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		ArrayList<Integer> intList = new ArrayList<Integer>();
		
		int p = 0;
		int q = 5;
		System.out.println("Enter five integers.");
		while (p < q){
			intList.add(input.nextInt());
			p++;
		}
		sort(intList);
	}

	public static void sort(ArrayList<Integer> list) {
		int small;
		for (int i = 0; i < list.size(); i++){
			small = list.get(i);
			for (int j = i; j < list.size(); j++){
				if(list.get(j) < small){
					small = list.get(j);
				}
			}
			list.remove(list.lastIndexOf(small));
			list.add(i, small);
		}
		System.out.println(list.toString());
	}
}
