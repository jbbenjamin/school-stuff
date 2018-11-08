import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.Stack;
public class n00853961 {

	public static void main(String[] args) throws IOException{
      String fileName = args[0];
      java.io.File file = new java.io.File(fileName);
      Scanner input = new Scanner(file);
      //BufferedReader br = new BufferedReader(new FileReader(fileName));
      //Scanner input = new Scanner(br);


/////////////////////////////////////////////////////////////////////////
//Part 1: Count occurrences of each letter

		int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0;
      
		while(input.hasNext()){
      String word = input.next();
         for(int i = 0; i < word.length(); i++){ 
			switch(word.charAt(i)){
			case 'A':
				a++;
            break;
			case 'B':
				b++;
            break;
			case 'C':
				c++;
            break;
			case 'D':
				d++;
            break;
			case 'E':
				e++;
            break;
			case 'F':
				f++;
            break;
			case 'G':
				g++;
            break;
			default:
            if(input.hasNext())
				   input.next();
            else
               break;
			}
         }
		}
      
		input.close();
      System.out.println("Letter            Frequency");
      System.out.println("---------------------------");
		System.out.println("  A			" +a);
		System.out.println("  B			" +b);
		System.out.println("  C			" +c);
		System.out.println("  D			" +d);
		System.out.println("  E			" +e);
		System.out.println("  F			" +f);
		System.out.println("  G			" +g);
		System.out.println("\nTotal number of bits: " +(8 * (a + b + c + d + e + f + g)));
      
//////////////////////////////////////////////////////////////////////////////////////
//Part 2: Make a node for each character
      
      Tree tree1 = new Tree();
      tree1.root = new Node();
      tree1.root.nodeChar = 'A';
      tree1.root.frequency = a;

      Tree tree2 = new Tree();
      tree2.root = new Node();
      tree2.root.nodeChar = 'B';
      tree2.root.frequency = b;
      
      Tree tree3 = new Tree();
      tree3.root = new Node();
      tree3.root.nodeChar = 'C';
      tree3.root.frequency = c;
      
      Tree tree4 = new Tree();
      tree4.root = new Node();
      tree4.root.nodeChar = 'D';
      tree4.root.frequency = d;
      
      Tree tree5 = new Tree();
      tree5.root = new Node();
      tree5.root.nodeChar = 'E';
      tree5.root.frequency = e;
      
      Tree tree6 = new Tree();
      tree6.root = new Node();
      tree6.root.nodeChar = 'F';
      tree6.root.frequency = f;
      
      Tree tree7 = new Tree();
      tree7.root = new Node();
      tree7.root.nodeChar = 'G';
      tree7.root.frequency = g;
            
	
////////////////////////////////////////////////////////////////
//Part 3: Make Priority queue with character nodes

      PriorityQueue PQ = new PriorityQueue(7);
      PQ.insert(tree1);
      PQ.insert(tree2);
      PQ.insert(tree3);
      PQ.insert(tree4);
      PQ.insert(tree5);
      PQ.insert(tree6);
      PQ.insert(tree7);
      
      
//////////////////////////////////////////////////////////////////
//Part 4: Make Huffman tree from priority queue
      int parentSum;
      
      while(PQ.nItems > 1){
         parentSum = PQ.queArray[0].root.frequency + PQ.queArray[1].root.frequency;
         Tree parentTree = new Tree();
         parentTree.root = new Node();
         parentTree.root.nodeChar = ' ';
         parentTree.root.frequency = parentSum;
         parentTree.root.leftChild = PQ.queArray[0].root;
         parentTree.root.rightChild = PQ.queArray[1].root;       
         PQ.removeFirst();
         PQ.removeFirst();
         PQ.insert(parentTree);
      }
      
      Tree huffmanTree = new Tree();
      huffmanTree = PQ.queArray[0];
      huffmanTree.displayTree();
//////////////////////////////////////////////////////////////////
//Part 5: Traverse tree, generate code
      String[] codeTable = new String[7];
      String bitString = "";
      bitString = traverseTree(huffmanTree.root, bitString);      
            /*if(localNode.leftChild == null && localNode.rightChild ==null){
               switch(localNode.nodeChar){
               case 'A':
                  codeTable[0] = bitString;
                  break;
               case 'B':
                  codeTable[1] = bitString;
                  break;
               case 'C':
                  codeTable[2] = bitString;
                  break;
               case 'D':
                  codeTable[3] = bitString;
                  break;
               case 'E':
                  codeTable[4] = bitString;
                  break;
               case 'F':
                  codeTable[5] = bitString;
                  break;
               case 'G':
                  codeTable[6] = bitString;
                  break;
               }
            }
   }
*/ 
   }  
//////////////////////////////////////////////////////////////
//Part 6:Encode original file using Huffman codes



//..........................................................
   public static String traverseTree(Node inputRoot, String inputString){

      Node localRoot = inputRoot;
      String bitString = inputString;
         
      if(localRoot.leftChild != null){
         String tempString = "0" + traverseTree(localRoot.leftChild, bitString);
         return tempString;
      }
   
      if(localRoot.rightChild != null){
         String tempString = "1" + traverseTree(localRoot.leftChild, bitString);
         return tempString;
         }
      else
         return bitString;

   }
}


////////////////////////////////////////////////////////////      					
class Node {
	public int frequency;
	public int leftOrRight;
	public char nodeChar;
	public Node leftChild;
	public Node rightChild;

//...................................................
	public Node(){
	}
}	//end class Node
/////////////////////////////////////////////////////

class Tree {
	public Node root;
	
//...................................................
	public Tree(){
		root = null;
}

//...................................................
   public void insert(char letter, int frequency){
      Node newNode = new Node();
      newNode.nodeChar = letter;
      newNode.frequency = frequency;
      
      if(root == null)
         root = newNode;
      else{
         Node current = root;
         Node parent;
      
         while(true){
            parent = current;
            if(frequency < current.frequency){
               current = current.leftChild;
               if(current == null){
                  parent.leftChild = newNode;
                  return;
                  }
               }
            else{
               current = current.rightChild;
               if(current == null){
                  parent.rightChild = newNode;
                  return;
               }
            }
         }   
       }
   }

//....................................................
public void displayTree()
      {
      Stack globalStack = new Stack();
      globalStack.push(root);
      int nBlanks = 32;
      boolean isRowEmpty = false;
      System.out.println(
      "......................................................");
      while(isRowEmpty == false)
         {
         Stack localStack = new Stack();
         isRowEmpty = true;

         for(int j = 0; j < nBlanks; j++)
            System.out.print(' ');

         while(globalStack.isEmpty() == false)
            {
            Node temp = (Node)globalStack.pop();
            if(temp != null)
               {
               System.out.print(temp.nodeChar);
               System.out.print(temp.frequency);
               localStack.push(temp.leftChild);
               localStack.push(temp.rightChild);

               if(temp.leftChild != null || temp.rightChild != null)
                  isRowEmpty = false;
               }
            else
               {
               System.out.print("--");
               localStack.push(null);
               localStack.push(null);
               }
            for(int j = 0; j < nBlanks * 2 - 2; j++)
               System.out.print(' ');
            }  // end while globalStack not empty
         System.out.println();
         nBlanks /= 2;
         while(localStack.isEmpty() == false)
            globalStack.push(localStack.pop() );
         }  // end while isRowEmpty is false
      System.out.println("......................................................");
      }  // end displayTree()

}

//////////////////////////////////////////////////////
class PriorityQueue{
	private int maxSize;
	public Tree[] queArray;
	public int nItems;

//....................................................
	public PriorityQueue(int size){
		maxSize = size;
		queArray = new Tree[maxSize];
		nItems = 0;
	}
	
//....................................................
	public void insert (Tree item){
		int j;
		
		if(nItems == 0)
			queArray[nItems++] = item;
		else{
         for(j = nItems - 1; j >= 0; j--){
            if(item.root.frequency < queArray[j].root.frequency)
               queArray[j + 1] = queArray[j];
            else
               break;
         }
         queArray[j + 1] = item;
         nItems++;
      }
         
	}
//.....................................................
   public Tree remove(){
      return queArray[--nItems];   
}

//.....................................................
   public Tree[] removeFirst(){
      nItems--;
   
      for(int i = 0; i < nItems; i++){
      queArray[i] = queArray[i + 1];
      }
   
      queArray[nItems] = null;
      return queArray;
   }

//.....................................................
   public boolean isEmpty(){
      return (nItems==0);   
   }

//.....................................................
   public boolean isFull(){
      return (nItems == maxSize);   
   }

}

