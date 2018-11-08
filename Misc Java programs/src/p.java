import java.util.Arrays;
import java.util.Scanner;
public class p {

	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the capacity of the knapsack:");
		int capacity = input.nextInt();
		
		System.out.println("Enter the number of weights that will be used:");
		int numberOfWeights = input.nextInt();
		int[] weights = new int[numberOfWeights];
		
		System.out.println("Enter the value of the weights that will be used, from largest to smallest:");
		for(int i = 0; i < numberOfWeights; i++)
		weights[i] = input.nextInt();
		
		int[] knapsack = new int[numberOfWeights];
		int weightNumber = 0;
		
		
		for(int i = 0; i < weights.length; i++){
			putInKnapsack(capacity, weightNumber, weights, knapsack);
			weights[i] = 0;
			
			for(int j = 0; j < weights.length; j++){
				knapsack[j] = 0;
			}
		}
		
		return;
	}

	public static void putInKnapsack(int capacity, int weightNumber, int[] weights, int[] knapsack){
		if(capacity == 0){																//base case: check if knapsack is holding exactly its maximum capacity, if so...
			System.out.println(Arrays.toString(knapsack));								//...print successful combination of weights
			
			if(knapsack[0] == (capacity + weights[weightNumber - 1]))					
				return;
			
			if(weightNumber < weights.length){											//if there are still more (smaller) weights to be checked...
				capacity = (capacity + knapsack[weightNumber - 1]);
				knapsack[(weightNumber - 1)] = 0;										//...continue trying smaller combinations of weights
			}	
		}
																						//(not base case)
		if(weightNumber < weights.length){												//if there are still more (smaller) weights to be checked...
			if(capacity > 0){															//...and the knapsack can still hold more weight...
				knapsack[weightNumber] = weights[weightNumber];							//...put the next largest weight in the knapsack
				weightNumber++;
				
				if(capacity - knapsack[weightNumber - 1] < 0){							//if there is too much weight in the knapsack...
					knapsack[(weightNumber - 1)] = 0;									//...remove the most recent weight placed in the knapsack
				}
				
				putInKnapsack((capacity - knapsack[weightNumber - 1]), weightNumber, weights, knapsack);		//recursive call
			}
		}
		
		return;
	}
}