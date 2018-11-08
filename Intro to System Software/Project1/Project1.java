import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Project1 {
	public static void main(String[] args) throws IOException{
	HashTable hshTable = new HashTable(83);	
	String[] list = new String[40];
	
	String fileName = args[0];
	readFile(fileName, list, hshTable);
	
	return;

	}
	
	private static String[] readFile(String fileName, String[] list, HashTable table)throws IOException{	
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
		String line = null;
		while((line = br.readLine()) != null){
		if(line.lastIndexOf(" ") != -1){
			if(table.check(line, table) != true){
			store(line, table);
			}
		}
		else
			search(line, table);
			
		}
		return list;
	}

	static void store(String sampleString, HashTable sampleTable){
		int x = sampleString.lastIndexOf(" ");
		String xToEnd = sampleString.substring(x);
		String valueString = xToEnd.trim();
		int value = Integer.parseInt(valueString);
		String chString = (sampleString.substring(0,x));
		DataItem stringItem = new DataItem(value, chString);
		sampleTable.insert(stringItem);
	}

	static void search(String searchString, HashTable sampleTable){
		sampleTable.find(searchString);
	}
}
//---------------------------------------------------------------------------
class DataItem {
	private int iData;
	private String sName;
	
	public DataItem(int num, String name){
		iData = num;
		sName = name;
	}
	
	public int getNum(){
		return iData;
	}
	
	public String getName(){
		return sName;
	}
}
//---------------------------------------------------------------------------
class HashTable {
	private DataItem[] inputList;
	private int listSize;
	
	HashTable(int size){
		listSize = size;
		inputList = new DataItem[listSize];
		
	}
	
	public int hashFunc(String key){
		int sum = ((int)key.charAt(0) + (int)key.charAt(1) + (int)key.charAt(2));
		sum = sum % listSize;
		return sum;
	}

	public void insert(DataItem item){
		String keyName = item.getName();
		int hashVal = hashFunc(keyName);
		while(inputList[hashVal] != null){
			++hashVal;
			hashVal %= listSize;
		}
	
		inputList[hashVal] = item;
		System.out.println("stored " + item.getName() + " " + item.getNum() + " at location " +hashVal);
	}


	public void find(String searchString){
		int hashVal = hashFunc(searchString);
		
		if(inputList[hashVal] != null){
			while(inputList[hashVal] != null){
				if((inputList[hashVal].getName()).equalsIgnoreCase(searchString)){
					System.out.println(searchString + " was found at location " + hashVal + " with value " + inputList[hashVal].getNum());
					return;
				}
				else{
					++hashVal;
					hashVal %= listSize;
				}
			}	

		}
		System.out.println("ERROR " + searchString +" not found!");
	}	

	public boolean check(String sampleString, HashTable sampleTable){
		int x = sampleString.lastIndexOf(" ");
		String chString = (sampleString.substring(0,x));
		int hashVal = hashFunc(chString);
	
		if(inputList[hashVal] != null){
			while(inputList[hashVal] != null){
				if((inputList[hashVal].getName()).equalsIgnoreCase(chString)){
					System.out.println(chString + " already exists at location " + hashVal);
					return true;
				}
				else{
					++hashVal;
					hashVal %= listSize;
				}	
			}
		}
		return false;
	}
}