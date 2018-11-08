import java.lang.InterruptedException;
import java.util.Random;

public class ProducerConsumer{
	
	public static Random random = new Random();
	public static int[] intArray = new int[11];
	public static int MUTEX = 1;
	public static int EMPTY = 10;
	public static int FULL = 0;
	static int i = 0;
	
	public static void main(String[] args){	
	
		consumer.start();
		producer.start();
	}


static Thread producer = new Thread(){
	public void run(){
	
		synchronized(intArray){
		
		do{
				while(EMPTY == 0 || FULL == 10){
					try{
						System.out.println("producer: intArray is full. waiting......");
						intArray.notify();
						intArray.wait();
					}
					catch(InterruptedException e){
						System.out.println("Interrupted Exception in producer");
					}
				}	
				try{
					Thread.sleep(random.nextInt(2000));
				}
				catch(InterruptedException e){
					System.out.println("Interrupted Exception in producer");
				}
				if(random.nextInt(10) > 4){
					try{
						intArray.notify();
						intArray.wait();
					}
					catch(InterruptedException e){
						System.out.println("Interrupted Exception in producer");
					}
				}
				intArray[i] = 1;
				EMPTY--;
				FULL++;
				i++;
				System.out.println("producing......");
				if(FULL == 1){
					System.out.println(FULL + " element in array");
				}
				else{
					System.out.println(FULL + " elements in array");
				}
				intArray.notify();
	}while(true);
	}
}	//end run
};	//end thread

static Thread consumer = new Thread(){
public void run(){
	
	synchronized(intArray){
	
	do{
		while(EMPTY == 9 || FULL == 0){
			try{
				System.out.println("consumer: intArray is empty. waiting......");
				intArray.notify();
				intArray.wait();
			}
			catch(InterruptedException e){
				System.out.println("Interrupted Exception in consumer");
			}	
		}
		try{
			Thread.sleep(random.nextInt(2000));
		}
		catch(InterruptedException e){
			System.out.println("Interrupted Exception in producer");
		}
		if(random.nextInt(10) > 4){
			try{
				intArray.notify();
				intArray.wait();
			}
			catch(InterruptedException e){
				System.out.println("Interrupted Exception in producer");
			}
		}
		intArray[i] = 0;
		EMPTY++;
		FULL--;
		i--;
		System.out.println("consuming......");
		if(FULL == 1){
			System.out.println(FULL + " element in array");
		}
		else{
			System.out.println(FULL + " elements in array");
		}
		intArray.notify();
	}while(true);
	}
}
};
}