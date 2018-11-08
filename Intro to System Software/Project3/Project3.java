import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class Project3 {
	public static void main(String[] args) throws IOException{
	//HashTable hshTable = new HashTable(83);
   HashTable addrTable = new HashTable(83);                                                                 // make table to store instruction lines
	String[][] list = new String[50][6];
   
	String fileName = args[0];                                                                               // args[0] is file in SIC/XE format
   String tableFile = args[1];                                                                              // args[1] is SICOPS.txt file
      
   OpcodeTable opTable = new OpcodeTable();
   opTable.makeOpTable(tableFile);                                                                          // make opcode table from SICOPS file
	readFile1(fileName, list, opTable, addrTable);                                                           // initiate pass 1
   	
	return;

	}
	
//......................................................................................................
   private static String[][] readFile1(String fileName, String[][] list, OpcodeTable codeTable, HashTable table)throws IOException{	
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
      String[][] symbolTable = new String[40][6];                                                           // make two dimensional array for symbol table
		String line = null;
      int locctr;                                                                                           // 'locctr' keeps track of current address
      
      line = br.readLine();                                                                                 // read first line
      symbolTable[0][0] = line.substring(0, 7);                                                             // separate line into substrings for the columns
      symbolTable[0][1] = line.substring(9, 10);
      symbolTable[0][2] = line.substring(10, 16);
      symbolTable[0][3] = line.substring(18, 19);
      symbolTable[0][4] = line.substring(19, 28);
      symbolTable[0][5] = line.substring(31, 79);
      
      System.out.println("Address");
      System.out.println("---------");
      
      if(symbolTable[0][2].contains("START")){                                                              // If first line contains "START"...
         locctr = Integer.parseInt(symbolTable[0][4].trim(), 16);                                           //...initialize 'locctr' at address of "START"
         store(line, locctr, table);                                                                        // store line in symbol table and print to console
         //store(symbolTable[0][0] + " " + symbolTable[0][2] + " " + symbolTable[0][3] + symbolTable[0][4], locctr, table);
      }
      else{                                                                                                 // If first line does not contain start...
         locctr = Integer.parseInt("000", 16);                                                              //...initialize 'locctr' to 0
         store(line, locctr, table);                                                                        // store line in symbol table and print to console
         //store(symbolTable[0][0] + " " + symbolTable[0][2] + " " + symbolTable[0][3] + symbolTable[0][4], locctr, table);     
      }
      
      int i = 0;         
      while((line = br.readLine()) != null){                                                                // while there are still more lines to be read form SIC/XE file...
        if(!line.trim().equals("")){                                                                        //...if line is not blank...
         i++;
         symbolTable[i][0] = line.substring(0, 7);                                                          //...separate line into substrings for the columns
         symbolTable[i][1] = line.substring(9, 10);
         symbolTable[i][2] = line.substring(10, 16);
         symbolTable[i][3] = line.substring(18, 19);
         symbolTable[i][4] = line.substring(19, 28);
         symbolTable[i][5] = line.substring(31, 79);
         
         String label = symbolTable[i][0].trim();                                                           // 'label' is the first section of the line
         if(!label.equals("")){                                                                             // If the line has a label...
            if(!symbolTable[i][0].contains(".")){                                                           //...If label is not a comment...      
               for(int j = 0; j < i - 1; j++){
                  if(label.equals(symbolTable[j][0].trim())){                                               //...check to see if label already exists...
                     System.out.println("ERROR: " + label + " already exists !");                           //...if it does, print error
                  }
               }
            }
            else{                                                                                           // if line is a comment line
               store(line, locctr, table);                                                                  //...just store and print it
            }      
         }
            
         if(symbolTable[i][1].equals("+")){                                                                 // if mneumonic begins with a "+" 
            if(checkOpcode(symbolTable[i][1] + symbolTable[i][2], codeTable) != false){                     //...check if mneumonic is valid...
               store(line, locctr, table);                                                                  //...if so, store line in symbol table and print to console
               //store(symbolTable[i][0] + symbolTable[i][1] + symbolTable[i][2] + " " + symbolTable[i][3] + symbolTable[i][4], locctr, table);
               locctr += 4;                                                                                 // add 4 to locctr
            }
            else{                                                                                           // if mneumonic begins with a "+" but isn't valid...
                  System.out.println("ERROR: Invalid operation code!");                                     //...print error
            }                                                                             
         }
         
         else{
            if(!symbolTable[i][2].isEmpty()){                                                               // check for a mneumonic
               if(checkOpcode(symbolTable[i][2], codeTable) != false){                                      // if one is found...
                   if(checkFormat(symbolTable[i][2], codeTable) == 2){                                      //...and is a format 2 instruction...
                        store(line, locctr, table);                                                         // store line in symbol table and print to console
                        //store(symbolTable[i][0] + " " + symbolTable[i][2] + " " + symbolTable[i][3] + symbolTable[i][4], locctr, table);
                        locctr += 2;                                                                        // add 2 to locctr 
                   }
                   else{                                                                                    // if not a format 2 instruction....
                     store(line, locctr, table);                                                            // store line in symbol table and print to console
                     //store(symbolTable[i][0] + " " + symbolTable[i][2] + " " + symbolTable[i][3] + symbolTable[i][4], locctr, table);
                     locctr += 3;                                                                           // add 3 to locctr
                   } 
                  
                   
               }
   
               
               else if(symbolTable[i][2].contains("WORD")){                                                 // if mneumonic is "WORD"...
                  store(line, locctr, table);                                                               // store line in symbol table and print to console
                  //store(symbolTable[i][0] + symbolTable[i][1] + symbolTable[i][2] + " " + symbolTable[i][3] + symbolTable[i][4], locctr, table);
                  locctr += 3;                                                                              // add 3 to locctr
               }
               else if(symbolTable[i][2].contains("RESW")){                                                 // if mneumonic is "RESW"...
                  String num = symbolTable[i][4].trim();
                  store(line, locctr, table);                                                               // store line in symbol table and print to console
                  //store(symbolTable[i][0] + symbolTable[i][1] + symbolTable[i][2] + " " + symbolTable[i][3] + symbolTable[i][4], locctr, table);
                  locctr = locctr + (3 * Integer.parseInt(num));                                            // add number of RESW's to locctr
               }
               else if(symbolTable[i][2].contains("RESB")){                                                 // if mneumonic is "RESB"...
                  String num = symbolTable[i][4].trim();
                  store(line, locctr, table);                                                               // store line in symbol table and print to console
                  //store(symbolTable[i][0] + symbolTable[i][1] + symbolTable[i][2] + " " + symbolTable[i][3] + symbolTable[i][4], locctr, table);
                  locctr = locctr + Integer.parseInt(num);                                                  // add number of RESB's to locctr
               }
               else if(symbolTable[i][2].contains("BYTE")){                                                 // if mneumonic is "BYTE"...
                  String constant = symbolTable[i][4].substring(2);
                  constant = constant.trim();
                  store(line, locctr, table);                                                               // store line in symbol table and print to console
                  //store(symbolTable[i][0] + symbolTable[i][1] + symbolTable[i][2] + " " + symbolTable[i][3] + symbolTable[i][4], locctr, table);
                  locctr = locctr + constant.length();                                                      // add number of bytes of constant to locctr
               }
               else{                                                                                        // if mneumonic cannot be fouund in opcode table...
                  System.out.println("ERROR: Invalid operation code!");                                     //...print error
               }
            }
            else{                                                                                           // if line contains no mneumonic...
               System.out.println(line);                                                                    //...store and print line 
            }
         }
        }
        else{                                                                                               // if line is blank...
        System.out.println("");                                                                             //...store and print a blank line
        }
      }
      return list;                                                                                          // return symbol table to main        
   }      
      
      
//...................................................................................................... 
	static void store(String sampleString, int inLocctr, HashTable sampleTable){
		//int x = sampleString.lastIndexOf(" ");
		//String xToEnd = sampleString.substring(x);
		//String operandString = xToEnd.trim();
		
      //if(operandString.isInt()){
        // int value = Integer.parseInt(operandString);
      //}
      //else{
         //int value = table.hashfunc(operandString);
      //}
      //int value = table.hashfunc(sampleString);
		//String mneumonicString = (sampleString.substring(0,x));
      //sampleString = sampleString.trim();
      String address = Integer.toHexString(inLocctr);
		DataItem item = new DataItem(address, sampleString);
		sampleTable.insert(item);
	}
   
//.................................................................................
	static void search(String searchString, HashTable sampleTable){
		sampleTable.find(searchString);
	}
   
//.................................................................................      
   static boolean checkOpcode(String operation, OpcodeTable table){
      for(int i = 0; i < table.numOfRows; i++){
         if(operation.contains(table.optable[i][0])){
            return true;
         }
      }
         return false;
   }

//..................................................................................   
   static int checkFormat(String operation, OpcodeTable table){
      for(int i = 0; i < table.numOfRows; i++){
            if(operation.contains(table.optable[i][0])){
               return Integer.parseInt(table.optable[i][2]);
            }
      }
   return 0;
   }
}
   
//---------------------------------------------------------------------------
class DataItem {
	private String iData;
	private String sName;
	
	public DataItem(String num, String name){
		iData = num;
		sName = name;
	}

//...........................................................................	
	public String getNum(){
		return iData;
	}

//...........................................................................	
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
      key = key.concat(key);
		int sum = ((int)key.charAt(0) + (int)key.charAt(1) + (int)key.charAt(2));
		sum = sum % listSize;
		return sum;
	}

//............................................................................
	public void insert(DataItem item){
		String keyName = item.getName();
		int hashVal = hashFunc(keyName);
		while(inputList[hashVal] != null){
			++hashVal;
			hashVal %= listSize;
		}
	
		inputList[hashVal] = item;
		System.out.println(item.getNum().toUpperCase() + "  " + item.getName());
      //System.out.println("stored " + item.getNum() + " " + item.getName() + " at location " +hashVal);
	}

//.............................................................................
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

//..............................................................................
	public boolean check(String sampleString, HashTable sampleTable){
		//int x = sampleString.lastIndexOf(" ");
		//String chString = (sampleString.substring(0,x));
		//int hashVal = hashFunc(chString);
	   int hashVal = hashFunc(sampleString);
      
		if(inputList[hashVal] != null){
			while(inputList[hashVal] != null){
				if((inputList[hashVal].getName().substring(0, 7).equalsIgnoreCase(sampleString.substring(0, 7)))){
					//System.out.println(chString + " already exists at location " + hashVal);
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
//-------------------------------------------------------------------------------------------------------
class OpcodeTable {
   protected String[][] optable = new String[130][4];
   private String operation;
   private String opcode;
   private int formatNum;
   private int something;
   protected int numOfRows;
   private int width;
   
   OpcodeTable(){
      numOfRows = 0;
      width = 4;
   }
   
   public void makeOpTable(String tableFile)throws IOException{
      java.io.File file = new java.io.File(tableFile);
      Scanner input = new Scanner(file);
      String line = null;
      int i = 0;
         while(i < 125){
         if(input.hasNext() == true){
            line = input.nextLine();
            optable[i][0] = line.substring(0, 5);
            optable[i][1] = line.substring(8, 9);
            optable[i][2] = line.substring(14, 15);
            optable[i][3] = line.substring(18, 19);
            numOfRows++;
            i++;
         }
         }
      return;
   }
}

   