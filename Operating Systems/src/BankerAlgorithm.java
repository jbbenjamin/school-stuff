import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.StringReader;
import java.io.IOException;

public class BankerAlgorithm {

	public static void main(String[] args){
		
		String nameOfFile = args[0];
		int res1 = Integer.parseInt(args[1]);
		int res2 = Integer.parseInt(args[2]);
		int res3 = Integer.parseInt(args[3]);
		
		String currentLine;
		int i = 0, j = 0;
		
		Bank bank = new Bank(res1, res2, res3);
		
		BufferedReader bufInput = null;
		int currentChar;
		String temp = "";
		
		try {
			bufInput = new BufferedReader(new FileReader(nameOfFile));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
								//set up bufferedReader
		}
		
		try {
		currentLine = bufInput.readLine();
		
		while(currentLine != null){													//while not at end of file
			StringReader inputStream = new StringReader(currentLine);				//set up a stringReader for this line
			if(!currentLine.isEmpty()){												//if the line isn't blank...
				//System.out.println();
				//System.out.println("INPUT: " + currentLine);						//print "INPUT: (line that was just read)"
			}
			for(j = 0; j < 3; j++){
				currentChar = inputStream.read();									//read first character of line
				//while ((int)currentChar != 65535){		
					if(Character.isDigit(currentChar)){
						bank.maximum[i][j] = ((int)currentChar) - 48;
						//System.out.println("bank.maximum["+ i +"]["+ j +"] = " + bank.maximum[i][j]);
					}
					else{
						j--; 						//read next character
					}
				//}
			}
			i++;
			Bank.numOfCustomers++;
			//System.out.println("new process");
			currentLine = bufInput.readLine();
		}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		bank.fillAllocate();
		bank.computeNeed();
		int[] r = {1,0,2};
		bank.request(1, r);
	}
}

class Bank{
	
	static int numOfCustomers;
	int numOfResources;
	int[] available = new int[10];
	int[][] maximum = new int[10][3];
	int[][] allocation = new int[10][3];
	int[][] need = new int[10][3];
	
	int[] tempAvailable = new int[10];
	int[][] tempAllocation = new int[10][3];
	int[][] tempNeed = new int[10][3];
	
	Bank(int r1, int r2, int r3){
		available[0] = r1;
		available[1] = r2;
		available[2] = r3;
	}
	
	public int[][] computeNeed(){
		for(int i= 0; i < numOfCustomers; i++){
			need[i][0] = maximum[i][0] - allocation[i][0];
			need[i][1] = maximum[i][1] - allocation[i][1];
			need[i][2] = maximum[i][2] - allocation[i][2];
		}
		return need;
	}
	
	public int[][] fillAllocate(){
		
		allocation[0][0] = 0;
		allocation[0][1] = 1;
		allocation[0][2] = 0;
		
		allocation[1][0] = 2;
		allocation[1][1] = 0;
		allocation[1][2] = 0;
		
		allocation[2][0] = 3;
		allocation[2][1] = 0;
		allocation[2][2] = 2;
		
		allocation[3][0] = 2;
		allocation[3][1] = 1;
		allocation[3][2] = 1;
		
		allocation[4][0] = 0;
		allocation[4][1] = 0;
		allocation[4][2] = 2;
		
		return allocation;
	}
	
	public void addProcess(int customerNum, int[] maximumDemand){
		numOfCustomers++;
		maximum[customerNum][0] = maximumDemand[0];
		maximum[customerNum][1] = maximumDemand[1];
		maximum[customerNum][2] = maximumDemand[2];
	}
	
	public void request(int customerNumber, int[] request){
		int[] rRequest = new int[] {request[0], request[1], request[2]};
		if(rRequest[0] <= need[customerNumber][0] && rRequest[1] <= need[customerNumber][1] && rRequest[2] <= need[customerNumber][2]){
			if(rRequest[0] <= available[0] && rRequest[1] <= available[1] && rRequest[2] <= available[2]){
				
				for(int i = 0; i < numOfCustomers; i++){
					for(int j = 0; j < 3; j++){
						tempAllocation[i][j] = allocation[i][j];
						tempNeed[i][j] = need[i][j];
					}
					tempAvailable[i] = available[i];
				}
				
				for(int j = 0; j < 3; j++){
					tempAvailable[j] = available[j] - rRequest[j];
					tempAllocation[customerNumber][j] = allocation[customerNumber][j] + rRequest[j];
					tempNeed[customerNumber][j] = need[customerNumber][j] - rRequest[j];
				}
				
				safetyAlgorithm(tempAvailable, tempAllocation, tempNeed, maximum);
					
			} //end if request < available
			else{
				System.out.println("Resources requested by p" + customerNumber + " exceeds resources currently available. Requested denied.");
				return;
			}
		} //end if request < need
		else{
			System.out.println("Resources requested by p" + customerNumber + " exceeds maximum amount of resources that can be held by process. Requested denied.");
			return;
		}
	} //end request
	
	public void safetyAlgorithm(int[]testAvailable, int[][]testAllocation, int[][] testNeed, int[][] maximum){
		int count = 0;
		String pOrder = "";
		boolean allDone = false;
		
		int[] work = new int[10];
		for(int i = 0; i < 3; i++){
			work[i] = testAvailable[i];
		}
		
		boolean[] finish = new boolean[numOfCustomers];
		for(int i = 0; i < numOfCustomers; i++){
			finish[i] = false;
		}
		
		while(count <= numOfCustomers){
			for(int i = 0; i < numOfCustomers; i++){
				if(finish[i] == false){
					if(testNeed[i][0] <= work[0] && testNeed[i][1] <= work[1] && testNeed[i][2] <= work[2]){
						for(int j = 0; j < 3; j++){
							work[j] = work[j] + testAllocation[i][j];
							finish[i] = true;
						}
						pOrder = pOrder + "p" + i + " ";
					}
				}
			}
			allDone = true;
			for(int i = 0; i < numOfCustomers; i++){
				if(finish[i] == false){
					allDone = false;
					break;
				}
			}
			if(allDone == true){
				available = testAvailable;
				allocation = testAllocation;
				need = testNeed;
				System.out.println("Request granted. Possible safe afe sequence is: " + pOrder);
				return;
			}
			count++;
		}
		System.out.println("Granting request would not leave the system in a safe state. Requested denied.");
	}
}