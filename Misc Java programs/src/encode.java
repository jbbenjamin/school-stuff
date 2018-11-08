import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
public class encode {

	public static void main(String[] args) throws IOException{
      String filename = args[0];
      Scanner input = new Scanner(new File(filename));
      //BufferedReader br = new BufferedReader(new FileReader(fileName));
      //Scanner input = new Scanner(br);

		int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0;
      
		while(input.hasNext()){
			switch((char) input.nextByte()){
			case 'A':
				a++;
			case 'B':
				b++;
			case 'C':
				c++;
			case 'D':
				d++;
			case 'E':
				e++;
			case 'F':
				f++;
			case 'G':
				g++;
			default:
				input.next();
			}
		}
		input.close();
		System.out.println("A			" +a);
		System.out.println("B			" +b);
		System.out.println("C			" +c);
		System.out.println("D			" +d);
		System.out.println("E			" +e);
		System.out.println("F			" +f);
		System.out.println("G			" +g);
		System.out.println("Total number of bits: " +(8 * (a + b + c + d + e + f + g)));
	}
}	
		/*Tree[] priorityQueue = new Tree[8];
		
		Node ANode = new Node();
		ANode.nodeChar = 'A';
		ANode.frequency = A;
			
		Node BNode = new Node();
		BNode.nodeChar = 'B';
		BNode.frequency = B;
		
		Node CNode = new Node();
		CNode.nodeChar = 'C';
		CNode.frequency = C;
		
		Node DNode = new Node();
		DNode.nodeChar = 'D';
		DNode.frequency = D;
		
		Node ENode = new Node();
		ENode.nodeChar = 'E';
		ENode.frequency = E;
		
		Node FNode = new Node();
		FNode.nodeChar = 'F';
		FNode.frequency = F;
		
		Node GNode = new Node();
		GNode.nodeChar = 'G';
		GNode.frequency = G;
		
		for(int i = 0; i <= priorityQueue.length; i++){
			priorityQueue[0].leftOrRight = 0;
			priorityQueue[1].leftOrRight = 1;
			Node Parent = new Node();
			Parent.frequency = (priorityQueue[0].frequency + priorityQueue[1].frequency);
			Parent.leftChild = priorityQueue[0];
			Parent.rightChild = priorityQueue[1];
			priorityQueue[0] = Parent;
			priorityQueue[1] = priorityQueue[(i + 2)];
		}
	}
}

class Node {
	public int frequency;
	public int leftOrRight;
	public char nodeChar;
	public Node leftChild;
	public Node rightChild;

//...................................................
	public Node(){
		//frequency = null;
		//leftOrRight = null;
		//nodeChar = null;
		leftChild = null;
		rightChild = null;

	}
}	//end class Node
/////////////////////////////////////////////////////

class Tree {
	private Node root;
	
//...................................................
	public Tree(){
		root = null;
}

//...................................................
	/*public void traverse(int traverseType){
		switch(traverseType){
			case 1: preOrder(root);
				break;
			case 2: inOrder(root);
				break;
			case 3: postOrder(root);
				break;
		}
		System.out.println();
	}
	
	
}

//...................................................
}

class PriorityQueue{
	private int maxSize;
	private Tree[] queArray;
	private int nItems;

//....................................................
	public PriorityQueue(int size){
		maxSize = size;
		queArray = new Tree[maxSize];
		nItems = 0;
	}
	
//....................................................
	public void insert (Node item){
		int j;
		
		if(nItems == 0)
			queArray[nItems++] = item;
		else{
         for(j = nItems - 1; j >= 0; j--){
            if(item > queArray[j])
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
public boolean isEmpty(){
   return (nItems==0);   
}

//.....................................................
public boolean isFull(){
   return (nItems == maxSize);   
}

}*/