
public class Inc {
	public static void main(String[] args){
		int a = 4;
		System.out.println("Before calling, the value of 'a' is : " + a);
		increment(a);
		System.out.println("After calling, the value of 'a' is : " + a);
	}

	public static void increment(int a){
		a = a + 1;
		System.out.println("The value of 'a' inside the menthod is : " + a);
		
	}
}
