/* Jeremiah Akins
 * N00974366
 * Assignment #6
 * This program demonstrates hashing three .txt files, accessed from args[i] input, that contain multiple
 * lines of strings with anonymous length.
 */
import java.io.*;
///////////////////////////////////////////////////////////////////////////////////////////////////////////
public class n00974366 {
	public static void main(String[] args) throws IOException{
		File firstFile = new File(args[0]);
		File secondFile = new File(args[1]);
		File thirdFile = new File(args[2]);
		
		HashTable hashTest = new HashTable();
		String[] A = hashTest.linearProbing(firstFile);
		hashTest.searchLinear(secondFile, A);
		hashTest.deleteLinear(thirdFile, A);
		System.out.print("\n" + "\n" + "\n" + "\n" + "\n");
		String[] B = hashTest.quadraticProbing(firstFile);
		hashTest.searchQuadratic(secondFile, B);
		hashTest.deleteQuadratic(thirdFile, B);
	}
} //end n00974366
///////////////////////////////////////////////////////////////////////////////////////////////////////////
class HashTable {
	public HashTable() {}
//---------------------------------------------------------------------------------------------------------
	private int getPrime(int doubleCount) {// returns 1st prime > min
		for(int j = doubleCount + 1; true; j++) {// for all j > min 
			if( isPrime(j) ) {// is j prime?
				return j; // yes, return it
			}
		}
	} 
//---------------------------------------------------------------------------------------------------------
	private boolean isPrime(int n) { // is n prime?
		for(int j = 2; (j * j <= n); j++) { // for all j
			if( n % j == 0) { // divides evenly by j?
				return false; // yes, so not prime
			}
		}
			return true; // no, so prime
	}
//---------------------------------------------------------------------------------------------------------	
	public int getLineCount(File file) throws IOException {
		
		int lineCount = 0; //number of lines in .txt
		BufferedReader Input = new BufferedReader(new FileReader(file));
		while(Input.readLine() != null) {
			lineCount++;
		}
		//System.out.println(lineCount);		//OPTIONAL PRINTING...
		Input.close();
		return lineCount;
	}
//---------------------------------------------------------------------------------------------------------
	public int hashKey(String item, int length) { // insert a DataItem
		int key = 0;
		for(int i = 0; i < item.length(); i++) {
			key = ((26 * key) + item.charAt(i)) % length;
		}
		return key;
	} // end hashKey()
//---------------------------------------------------------------------------------------------------------
	public void deleteLinear(File file, String[] A) throws IOException {
			String[] searchedItems = new String[this.getLineCount(file)];
			String[] itemsFoundSuccess = new String[searchedItems.length];
			String[] itemsFoundFailure = new String[searchedItems.length];
			int[] probeDepthSuccesses = new int[searchedItems.length];
			int[] probeDepthFailures = new int[searchedItems.length];
			int i = 0;
			//System.out.println(A.length);		//OPTIONAL PRINTING...
			BufferedReader buffFile = new BufferedReader(new FileReader(file));
			String eachItem = "";
			while ((eachItem = buffFile.readLine()) != null) {
				//System.out.println(this.hashKey(eachItem, A.length));		//OPTIONAL PRINTING...
				int h = this.hashKey(eachItem, A.length);
				int probeDepth = 1;
				while(A[h] != null && A[h] != "-1") {
					if(A[h].equals(eachItem)) {
						A[h] = "-1";
						itemsFoundSuccess[i] = "Yes";
						itemsFoundFailure[i] = "--";
						probeDepthSuccesses[i] = probeDepth;
						searchedItems[i] = eachItem;
						i++;
						break;
					} else {
						++h;
						h %= A.length;
						probeDepth++;
					}
				}
				if(!eachItem.equals(A[h]) && !"-1".equals(A[h])) {
					itemsFoundFailure[i] = "Yes";
					itemsFoundSuccess[i] = "--";
					probeDepthFailures[i] = probeDepth;
					searchedItems[i] = eachItem;
					i++;
				}
			}
			/*for(int j = 0; j < searchedItems.length; j++) {		//OPTIONAL PRINTING...
				System.out.println(searchedItems[j]);
			}
			for(int k = 0; k < itemsFoundSuccess.length; k++) {		//OPTIONAL PRINTING...
				System.out.println(itemsFoundSuccess[k]);
			}
			for(int l = 0; l < probeDepthSuccesses.length; l++) {		//OPTIONAL PRINTING...
				System.out.println(probeDepthSuccesses[l]);
			}*/
			buffFile.close();
			this.displaySearchTable(searchedItems,itemsFoundSuccess, itemsFoundFailure, probeDepthSuccesses, probeDepthFailures, 'A');
	} // end deleteLinear()			
//---------------------------------------------------------------------------------------------------------
	public void deleteQuadratic(File file, String[] B) throws IOException {
		String[] searchedItems = new String[this.getLineCount(file)];
		String[] itemsFoundSuccess = new String[searchedItems.length];
		String[] itemsFoundFailure = new String[searchedItems.length];
		int[] probeDepthSuccesses = new int[searchedItems.length];
		int[] probeDepthFailures = new int[searchedItems.length];
		int i = 0;
		//System.out.println(B.length);		//OPTIONAL PRINTING...
		BufferedReader buffFile = new BufferedReader(new FileReader(file));
		String eachItem = "";
		while ((eachItem = buffFile.readLine()) != null) {
			//System.out.println(this.hashKey(eachItem, B.length));		//OPTIONAL PRINTING...
			int h = this.hashKey(eachItem, B.length);
			int oH = this.hashKey(eachItem, B.length);		//original hashKey
			int probeDepth = 1;
			while(B[h] != null && B[h] != "-1") {
				if(B[h].equals(eachItem)) {
					B[h] = "-1";
					itemsFoundSuccess[i] = "Yes";
					itemsFoundFailure[i] = "--";
					probeDepthSuccesses[i] = probeDepth;
					searchedItems[i] = eachItem;
					i++;
					break;
				} else {
					h = oH + (probeDepth * probeDepth);
					h %= B.length;
					probeDepth++;
				}
			}
			if(!eachItem.equals(B[h]) && !"-1".equals(B[h])) {
				itemsFoundFailure[i] = "Yes";
				itemsFoundSuccess[i] = "--";
				probeDepthFailures[i] = probeDepth;
				searchedItems[i] = eachItem;
				i++;
			}
		}
		/*for(int j = 0; j < searchedItems.length; j++) {		//OPTIONAL PRINTING...
			System.out.println(searchedItems[j]);
		}
		for(int k = 0; k < itemsFoundSuccess.length; k++) {		//OPTIONAL PRINTING...
			System.out.println(itemsFoundSuccess[k]);
		}
		for(int l = 0; l < probeDepthSuccesses.length; l++) {		//OPTIONAL PRINTING...
			System.out.println(probeDepthSuccesses[l]);
		}*/
		buffFile.close();
		this.displaySearchTable(searchedItems,itemsFoundSuccess, itemsFoundFailure, probeDepthSuccesses, probeDepthFailures, 'B');
	} // end deleteQuadratic()			
//---------------------------------------------------------------------------------------------------------
	public void searchLinear(File file, String[] A) throws IOException {
		String[] searchedItems = new String[this.getLineCount(file)];
		String[] itemsFoundSuccess = new String[searchedItems.length];
		String[] itemsFoundFailure = new String[searchedItems.length];
		int[] probeDepthSuccesses = new int[searchedItems.length];
		int[] probeDepthFailures = new int[searchedItems.length];
		int i = 0;
		//System.out.println(A.length);		//OPTIONAL PRINTING...
		BufferedReader buffFile = new BufferedReader(new FileReader(file));
		String eachItem = "";
		while ((eachItem = buffFile.readLine()) != null) {
			//System.out.println(this.hashKey(eachItem, A.length));		//OPTIONAL PRINTING...
			int h = this.hashKey(eachItem, A.length);
			int probeDepth = 1;
			while(A[h] != null && A[h] != "-1") {
				if(A[h].equals(eachItem)) {
					itemsFoundSuccess[i] = "Yes";
					itemsFoundFailure[i] = "--";
					probeDepthSuccesses[i] = probeDepth;
					searchedItems[i] = eachItem;
					i++;
					break;
				} else {
					++h;
					h %= A.length;
					probeDepth++;
				}
			}
			if(!eachItem.equals(A[h])) {
				itemsFoundFailure[i] = "Yes";
				itemsFoundSuccess[i] = "--";
				probeDepthFailures[i] = probeDepth;
				searchedItems[i] = eachItem;
				i++;
			}
		}
		/*for(int j = 0; j < searchedItems.length; j++) {		//OPTIONAL PRINTING...
			System.out.println(searchedItems[j]);
		}
		for(int k = 0; k < itemsFoundSuccess.length; k++) {		//OPTIONAL PRINTING...
			System.out.println(itemsFoundSuccess[k]);
		}
		for(int l = 0; l < probeDepthSuccesses.length; l++) {		//OPTIONAL PRINTING...
			System.out.println(probeDepthSuccesses[l]);
		}*/
		buffFile.close();
		this.displaySearchTable(searchedItems,itemsFoundSuccess, itemsFoundFailure, probeDepthSuccesses, probeDepthFailures, 'A');
	} // end searchLinear()
//---------------------------------------------------------------------------------------------------------
	public void displaySearchTable(String[] array, String[] foundS, String[] foundF, int[] depthArrayS, int[] depthArrayF, char c ) {
		
		double sumS = 0;
		double sumF = 0;
		double countS = 0;
		double countF = 0;
		System.out.println("---------------------------------------------"+ " Table for: " + c + " " + "----------------------------------------------");
		System.out.printf("%-42s%-10s%-10s%-22s%-10s\n", "String", "Success", "Failure", "Success Probe Depth", "Failure Probe Depth");
		System.out.println("---------------------------------------------------------------------------------------------------------");
		for(int i = 0; i < array.length; i++) {
			if(array[i] != null && array[i] != "-1") {
				System.out.printf("%-42s%-10s%-22s%-22d%-10d\n", array[i], foundS[i], foundF[i], depthArrayS[i], depthArrayF[i]);
				sumS += depthArrayS[i];
				countS++;
				sumF += depthArrayF[i];
				countF++;
			}
		}
		double avgS = sumS/countS;
		double avgF = sumF/countF;
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.println("Average Probe Depth for Success: " + avgS);
		System.out.println("Average Probe Depth for Failure: " + avgF);
		System.out.println("");
	}
//---------------------------------------------------------------------------------------------------------
	public String[] linearProbing(File file) throws IOException {
		String[] A = new String[this.getPrime(2 * this.getLineCount(file))];
		int[] probeDepths = new int[this.getPrime(2 *this.getLineCount(file))];
		//System.out.println(A.length);		//OPTIONAL PRINTING...
		BufferedReader buffFile = new BufferedReader(new FileReader(file));
		String eachItem = "";
		while ((eachItem = buffFile.readLine()) != null) {
			//System.out.println(eachItem);		//OPTIONAL PRINTING...
			//System.out.println(this.hashKey(eachItem, A.length));		//OPTIONAL PRINTING...
			int h = this.hashKey(eachItem, A.length);
			int probeDepth = 1;
			while(A[h] != null && A[h] != "-1") {
				++h;
				h %= A.length;
				probeDepth++;
			}
			A[h] = eachItem;
			probeDepths[h] = probeDepth;
		}
		/*for(int j = 0; j < A.length; j++) {		//OPTIONAL PRINTING...
			System.out.println(A[j]);
		}
		for(int k = 0; k < probeDepths.length; k++) {		//OPTIONAL PRINTING...
			System.out.println(probeDepths[k]);
		}*/
		buffFile.close();
		this.displayInsertTable(A, probeDepths, 'A');
		return A;
	}
//---------------------------------------------------------------------------------------------------------
	public void searchQuadratic(File file, String[] B) throws IOException {
		String[] searchedItems = new String[this.getLineCount(file)];
		String[] itemsFoundSuccess = new String[searchedItems.length];
		String[] itemsFoundFailure = new String[searchedItems.length];
		int[] probeDepthSuccesses = new int[searchedItems.length];
		int[] probeDepthFailures = new int[searchedItems.length];
		int i = 0;
		//System.out.println(B.length);		//OPTIONAL PRINTING...
		BufferedReader buffFile = new BufferedReader(new FileReader(file));
		String eachItem = "";
		while ((eachItem = buffFile.readLine()) != null) {
			//System.out.println(this.hashKey(eachItem, B.length));		//OPTIONAL PRINTING...
			int h = this.hashKey(eachItem, B.length);
			int oH = this.hashKey(eachItem, B.length);		//original hashKey
			int probeDepth = 1;
			while(B[h] != null && B[h] != "-1") {
				if(B[h].equals(eachItem)) {
					itemsFoundSuccess[i] = "Yes";
					itemsFoundFailure[i] = "--";
					probeDepthSuccesses[i] = probeDepth;
					searchedItems[i] = eachItem;
					i++;
					break;
				} else {
					h = oH + (probeDepth * probeDepth);
					h %= B.length;
					probeDepth++;
				}
			}
			if(!eachItem.equals(B[h])) {
				itemsFoundFailure[i] = "Yes";
				itemsFoundSuccess[i] = "--";
				probeDepthFailures[i] = probeDepth;
				searchedItems[i] = eachItem;
				i++;
			}
		}
		/*for(int j = 0; j < searchedItems.length; j++) {		//OPTIONAL PRINTING...
			System.out.println(searchedItems[j]);
		}
		for(int k = 0; k < itemsFoundSuccess.length; k++) {		//OPTIONAL PRINTING...
			System.out.println(itemsFoundSuccess[k]);
		}
		for(int l = 0; l < probeDepthSuccesses.length; l++) {		//OPTIONAL PRINTING...
			System.out.println(probeDepthSuccesses[l]);
		}*/
		buffFile.close();
		this.displaySearchTable(searchedItems,itemsFoundSuccess, itemsFoundFailure, probeDepthSuccesses, probeDepthFailures, 'B');
	} // end searchQuadratic()
//---------------------------------------------------------------------------------------------------------
	public String[] quadraticProbing(File file) throws IOException {
		String[] B = new String[this.getPrime(2 * this.getLineCount(file))];
		int[] probeDepths = new int[this.getPrime(2 *this.getLineCount(file))];
		//System.out.println(B.length);		//OPTIONAL PRINTING...
		BufferedReader buffFile = new BufferedReader(new FileReader(file));
		String eachItem = "";
		while ((eachItem = buffFile.readLine()) != null) {
			//System.out.println(eachItem);		//OPTIONAL PRINTING...
			//System.out.println(this.hashKey(eachItem, B.length));		//OPTIONAL PRINTING...
			int h = this.hashKey(eachItem, B.length);
			int oH = this.hashKey(eachItem, B.length);		//original hashKey
			int probeDepth = 1;
			while(B[h] != null && B[h] != "-1") {
				h = oH + (probeDepth * probeDepth);
				h %= B.length;
				probeDepth++;
			}
			B[h] = eachItem;
			probeDepths[h] = probeDepth;
		}
		/*for(int j = 0; j < B.length; j++) {		//OPTIONAL PRINTING...
			System.out.println(B[j]);
		}
		for(int k = 0; k < probeDepths.length; k++) {		//OPTIONAL PRINTING...
			System.out.println(probeDepths[k]);
		}*/
		buffFile.close();
		this.displayInsertTable(B, probeDepths, 'B');
		return B;
	}
//---------------------------------------------------------------------------------------------------------
	public void displayInsertTable(String[] hashArray, int[] depthArray, char c ) {
		
		double sum = 0;
		double count = 0;
		System.out.println("--------------------------"+ " Table for: " + c + " " + "---------------------------");
		System.out.printf("%-10s%-40s%-25s\n", "Index", "String", "Probe Depth");
		System.out.println("-------------------------------------------------------------------");
		for(int i = 0; i < hashArray.length; i++) {
			if(hashArray[i] != null && hashArray[i] != "-1") {
				System.out.printf("%-10d%-40s%-25d\n", i, hashArray[i], depthArray[i]);
				sum += depthArray[i];
				count++;
			}
		}
		double avg = sum/count;
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Average Probe Depth: " + avg);
		System.out.println("");
	}
//---------------------------------------------------------------------------------------------------------
} //end HashTable
///////////////////////////////////////////////////////////////////////////////////////////////////////////