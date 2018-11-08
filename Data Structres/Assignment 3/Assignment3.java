import java.util.Scanner;

class Link {
   public int iData;
   public Link next;               //next is a Link object
// ---------------------------------------------------
   public Link(int value){         //constructor
   iData = value;                   //initialize data in Link
   }
}                                   //end class Link
   
////////////////////////////////////////////////////////////////
class LinkList {
   public Link first;
   public Link last;
   public Link current;
   public Link previous;
// ----------------------------------------------------   
  public LinkList() {               //constructor
  first = null;                      //LinkList is empty  
  }
  
// ----------------------------------------------------  
   public void insertFirst(int value) {  //Inserts a link at the beginning of the list
   Link newLink = new Link(value);   //creates a new link object
      
      if(first == null){
         first = newLink;
         current = first;
         last = current;
      }
      else{   
         newLink.next = first;
         first = newLink;
      }
   }
// ----------------------------------------------------
   public Link deleteFirst() {           //Deletes the link at the beginning of the list

   Link temp = first;
   first = first.next;
   return temp;
   }
   
// ----------------------------------------------------
   public Link deleteCurrent(Link current, Link previous) {           //Deletes the link at the current of the list
   Link temp2 = current;

      if(current == last){
         current = first;
         previous.next = current;
         last = previous;
         return current;
      }
      else if(current == first){
         first = current.next;
         current = current.next;
         previous.next = current;
         return current;
      }
      else{   
         current = current.next;
         previous.next = current;
         return current;
      }   
   }
   
   
}                                    //end class LinkList


class Assignment3 {
   public static void main(String[] args){
   
   
   System.out.println("Enter 3 integers:");
   
   Scanner input = new Scanner(System.in);
   
   LinkList newLinkList = new LinkList();
   
   int sizeInt, holderInt, hotPotatoInt, j;
   
   sizeInt = input.nextInt();
   holderInt = input.nextInt();
   hotPotatoInt = input.nextInt();
   j = sizeInt;
   
   for(int i = 0; i < sizeInt; i++){
      newLinkList.insertFirst(j);
      j--;
   }
   
    Link currentLink = newLinkList.first;       // start at beginning of list
    Link previousLink = newLinkList.first;
    if(currentLink.next == null){               // if there is only one link in the list
      System.out.println("1");              // print 1
    }
    else
       for(int i = 1; i < holderInt; i++){  // beginning at the first link and going until the holder value
         Link temp = currentLink;
         previousLink = temp;
         currentLink = currentLink.next;            // move current forward one link
         previousLink.next = currentLink;
       }
       
    while(currentLink != newLinkList.first || currentLink != newLinkList.last){
      for(int i = 0; i < hotPotatoInt; i++){ // starts at holder value and stop when the elimination value is reached
         Link temp = currentLink;
          
         if(currentLink == newLinkList.last){          // if current is currently the last link
            currentLink.next = newLinkList.first;  // move next to the first link in the list
            previousLink = temp;
            currentLink = currentLink.next;
            previousLink.next = currentLink;
         }
         else{
            previousLink = temp;
            currentLink = currentLink.next;         // move to the next adjacent link
            previousLink.next = currentLink;
         }   
      }
      currentLink = newLinkList.deleteCurrent(currentLink, previousLink);
    }
    
    System.out.println(currentLink.iData);
          
   }
}         