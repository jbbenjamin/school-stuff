import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class n00853961 {
	public static void main(String[] args) throws IOException{
	
	String fileName1 = args[0];
   String fileName2 = args[1];
   String fileName3 = args[2];
	int numOfStrings = readAndCount(fileName1);
	
   int tableSize = ((2 * numOfStrings) + 1);
   for(int i = 3; i > (2 * numOfStrings); i = i + 2){
      if(((2 * numOfStrings) % i) == 0){
         tableSize += 2;
      }
   }  
   
   
   HashTable hshTableA = new HashTable(tableSize);	
   HashTable hshTableB = new HashTable(tableSize);
      
   readAndLinearStore(fileName1, hshTableA);
   readAndQuadraticStore(fileName1, hshTableB);
   
   linearSearch(fileName2, hshTableA);
   quadraticSearch(fileName2, hshTableB);
	
   linearReadAndDel(fileName3, hshTableA);
   quadraticReadAndDel(fileName3, hshTableB);
   
   return;

	}
	
   
   private static int readAndCount(String fileName)throws IOException{	
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
		String line = null;
      int stringCount = 0;
      
		while((line = br.readLine()) != null){
         stringCount++;
      }
      
      return stringCount;
   }
   
	private static HashTable readAndLinearStore(String fileName, HashTable hTable)throws IOException{	
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
      double probeLengthSum = 0;
		String line = null;
      System.out.println("Array A: Linear Insert");
      System.out.println("---------------");
      System.out.println("index  " + "String             " + "probe length for insertion");

		while((line = br.readLine()) != null){
			DataItem item = new DataItem(0, line);
         probeLengthSum = probeLengthSum + hTable.linearInsert(item);
		}
	   System.out.println("avg probe length " + (probeLengthSum/readAndCount(fileName)));
		return hTable;
	}

   private static HashTable readAndQuadraticStore(String fileName, HashTable hTable)throws IOException{	
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
      double probeLengthSum = 0;
		String line = null;
      System.out.println("\nArray B: Quadratic Insert");
      System.out.println("---------------");
      System.out.println("index  " + "String             " + "probe length for insertion");

		while((line = br.readLine()) != null){
			DataItem item = new DataItem(0, line);
         probeLengthSum = probeLengthSum + hTable.quadraticInsert(item);
		}
		System.out.println("avg probe length " + (probeLengthSum/readAndCount(fileName)));	
		return hTable;
	}

	static void linearSearch(String fileName, HashTable sampleTable)throws IOException{
      System.out.println("\n\nArray A: Linear Search");
      System.out.println("---------------");
      System.out.println("String" + "  Success" + "  Failure" + "  Probe length for success" + " Probe length for failure");
      BufferedReader br = new BufferedReader(new FileReader(fileName));
		
      double probeLengthSum = 0;
		String line = null;
      while((line = br.readLine()) != null){
         probeLengthSum = probeLengthSum + sampleTable.linearFind(line);
      }
      System.out.println("avg probe length " + (probeLengthSum/readAndCount(fileName)));
	}
   
   static void quadraticSearch(String fileName, HashTable sampleTable)throws IOException{
		System.out.println("\nArray B: Quadratic Search");
      System.out.println("---------------");
      System.out.println("String" + "  Success" + "  Failure" + "  Probe length for success" + " Probe length for failure");
      BufferedReader br = new BufferedReader(new FileReader(fileName));
		
      int probeLengthSum = 0;
		String line = null;
      while((line = br.readLine()) != null){
         probeLengthSum = probeLengthSum + sampleTable.quadraticFind(line);
      }
      System.out.println("avg probe length " + (probeLengthSum/readAndCount(fileName)));
	}

   static void linearReadAndDel(String fileName, HashTable sampleTable)throws IOException{
      System.out.println("\n\nArray A: Linear Delete");
      System.out.println("---------------");
      System.out.println("String" + "  Success" + "  Failure" + "  Probe length for success" + " Probe length for failure");
      BufferedReader br = new BufferedReader(new FileReader(fileName));
		
      double probeLengthSum = 0;
		String line = null;
      while((line = br.readLine()) != null){
         probeLengthSum = probeLengthSum + sampleTable.linearDelete(line);
      }
      System.out.println("avg probe length " + (probeLengthSum/readAndCount(fileName)));
	}


   
   static void quadraticReadAndDel(String fileName, HashTable sampleTable)throws IOException{
		System.out.println("\nArray B: Quadratic Delete");
      System.out.println("---------------");
      System.out.println("String" + "  Success" + "  Failure" + "  Probe length for success" + " Probe length for failure");
      BufferedReader br = new BufferedReader(new FileReader(fileName));
		
      double probeLengthSum = 0;
		String line = null;
      while((line = br.readLine()) != null){
         probeLengthSum = probeLengthSum + sampleTable.quadraticDelete(line);
      }
      System.out.println("avg probe length " + (probeLengthSum/readAndCount(fileName)));
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
	private DataItem nonItem;
	HashTable(int size){
		listSize = size;
		inputList = new DataItem[listSize];
		nonItem = new DataItem(-1, "-1");
	}
	
   public void displayTable(){
      System.out.print("Table: ");
      for(int j = 0; j < listSize; j++){
         if(inputList[j] != null)
            System.out.print(inputList[j].getName() + " ");
         else
            System.out.print("** ");
      }
      System.out.println("");
   }
   
	public int hashFunc(String key){
		int hashVal = key.charAt(0);
      for(int j = 1; j < key.length(); j++){
         int letter = key.charAt(j);
         hashVal = (hashVal * 26 + letter) % listSize;
      }
		return hashVal;
	}

	public double linearInsert(DataItem item){
		String keyName = item.getName();
		int hashVal = hashFunc(keyName);
      int probeLength = 1;
		while(inputList[hashVal] != null && inputList[hashVal].getNum() != -1){
			probeLength++;
         ++hashVal;
			hashVal %= listSize;
		}
	
		inputList[hashVal] = item;
		System.out.println("  " + hashVal + "      " + inputList[hashVal].getName() + "       " + probeLength );
      return probeLength;
	}

   public double quadraticInsert(DataItem item){
		String keyName = item.getName();
		int hashVal = hashFunc(keyName);
      int probeLength = 1;
      int i = 1;
		while(inputList[hashVal] != null && inputList[hashVal].getNum() != -1){
			probeLength++;
         hashVal = hashVal + (i * i);
			i++;
         hashVal %= listSize;
		}
      
      inputList[hashVal] = item;
	   System.out.println("  " + hashVal + "      " + inputList[hashVal].getName() + "       " + probeLength);
      return probeLength;
  	}

	public int linearFind(String searchString){
		int hashVal = hashFunc(searchString);
		int probeLength = 1;
      
		if(inputList[hashVal] != null){
			while(inputList[hashVal] != null){
				if((inputList[hashVal].getName()).equalsIgnoreCase(searchString)){
					System.out.println(inputList[hashVal].getName() + "  yes" + "  " + " " + probeLength + "   0");
					return probeLength;
				}
				else{
               probeLength++;
					++hashVal;
					hashVal %= listSize;
				}
			}	

		}
		System.out.println(searchString + "  yes " + "    0     " + probeLength);
      return probeLength;
	}	

   public int quadraticFind(String searchString){
		int hashVal = hashFunc(searchString);
		int probeLength = 1;
      int i = 1;
      
		if(inputList[hashVal] != null){
			while(inputList[hashVal] != null){
				if((inputList[hashVal].getName()).equalsIgnoreCase(searchString)){
					System.out.println(inputList[hashVal].getName() + "  yes" + "  " + " " + probeLength + "  " + 0);
					return probeLength;
				}
				else{
               probeLength++;
					hashVal = hashVal + (i * i);
			      i++;
					hashVal %= listSize;
				}
			}	

		}
		System.out.println(searchString + "  yes" + "    0     " + probeLength);
      return probeLength;
   }
	
   public double linearDelete(String key){
      int hashVal = hashFunc(key);
      int probeLength = 1;
      
      while(inputList[hashVal] != null){
         if(inputList[hashVal].getName().equalsIgnoreCase(key)){
            System.out.println(inputList[hashVal].getName() + "  yes" + "  " + " " + probeLength + "   0");
            DataItem temp = inputList[hashVal];
            inputList[hashVal] = nonItem;
            return probeLength;
         }
         ++hashVal;
         hashVal %= listSize;
      }
   System.out.println(key + "  yes " + "    0     " + probeLength);
   return probeLength;
   }
   
   public double quadraticDelete(String key){
      int hashVal = hashFunc(key);
      int i = 0;
      int probeLength = 1;
      
      while(inputList[hashVal] != null){
         if(inputList[hashVal].getName().equalsIgnoreCase(key)){
            System.out.println(inputList[hashVal].getName() + "  yes" + "  " + " " + probeLength + "   0");
            DataItem temp = inputList[hashVal];
            inputList[hashVal] = nonItem;
            return probeLength;
         }
         hashVal = hashVal + (i * i);
			i++;
         hashVal %= listSize;
      }
   System.out.println(key + "  yes " + "    0     " + probeLength);
   return probeLength;
   }

}