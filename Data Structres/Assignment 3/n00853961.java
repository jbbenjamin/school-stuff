import java.util.Scanner;

class Link {
   public int iData;
   public Link next;                //next is a Link object
// ---------------------------------------------------
   public Link(int value){          //constructor
   iData = value;                   //initialize data in Link
   }
}                                   //end class Link
   
////////////////////////////////////////////////////////////////
class LinkList {
   public Link first;
   public Link last;
   private Link current;
   private Link previous;
// ----------------------------------------------------   
  public LinkList() {                  //constructor
  first = null;                        //LinkList is empty  
  }
  
// ----------------------------------------------------  
   public void insertFirst(int value) {                               //Inserts a link at the beginning of the list
   Link newLink = new Link(value);                                    //creates a new link object
      
      if(first == null){                                              //if linked list is empty...
         first = newLink;
         current = first;
         last = current;                                              //...puts one link in the list
      }
      else{                                                           //if linked list is not empty...
         newLink.next = first;
         first = newLink;                                             //...inserts link at beginning of list and calls it first
      }
   }     //end delete first
// ----------------------------------------------------
   public Link deleteFirst() {                                        //Deletes the link at the beginning of the list

   Link temp = first;
   first = first.next;
   return temp;
   }     //end delete first
   
// ----------------------------------------------------
   public Link deleteCurrent(Link current, Link previous) {           //Deletes the link referenced by "current"
   Link temp2 = current;

      if(current == last){                                            //if current is the last link in the list...
         current = first;
         previous.next = current;
         last = previous;
         return current;                                              //...last points to the second-to-last link, then the last link is deleted, and current now points to the first link
      }
      else if(current == first){                                      //if current is the first link in the list...
         first = current.next;
         current = current.next;
         previous.next = current;
         return current;                                              //...first points to the second link, then the first link is deleted, and current points to new first link 
      }
      else{                                                           //if current is neither the first nor last link...
         current = current.next;
         previous.next = current;
         return current;                                              //...that link is deleted and current now points to the next link
      }   
   }     //end deleteCurrent
   
//-----------------------------------------------------   
}     //end class LinkList


class n00853961 {
   public static void main(String[] args){
   
   Scanner input = new Scanner(System.in);
   
   String sentinel = "continue";
    
   while(sentinel.equalsIgnoreCase("stop") == false){             //ends program when user enters "stop"
   System.out.println("Enter 'continue' to run program. Enter 'stop' to end program."); 
   sentinel = input.nextLine();
   
   if(sentinel.equalsIgnoreCase("continue")){                     //continues program if user enters "continue"
   System.out.println("Enter 3 integers:");
   
   LinkList newLinkList = new LinkList();                         //creates a new linked list
   
   int sizeInt, holderInt, hotPotatoInt, j;
   
   sizeInt = input.nextInt();                                     //list size
   holderInt = input.nextInt();                                   //holder starting value
   hotPotatoInt = input.nextInt();                                //elimination count number
   j = sizeInt;
   
   for(int i = 0; i < sizeInt; i++){
      newLinkList.insertFirst(j);                                 //fill linked list with links numbered 1 - sizeInt
      j--;
   }
   
    Link currentLink = newLinkList.first;                         // start at beginning of list
    Link previousLink = newLinkList.first;
    if(currentLink.next == null){                                 // if there is only one link in the list...
      System.out.println("1");                                    // ...print 1
    }
    else                                                          // if there is more than one link in the list...
       for(int i = 1; i < holderInt; i++){            
         Link temp = currentLink;
         previousLink = temp;
         currentLink = currentLink.next;                          // ...current will point to the link with the same number as holderInt 
         previousLink.next = currentLink;
       }
       
    while(currentLink != newLinkList.first || currentLink != newLinkList.last){        //while there is more than one link in the list
      for(int i = 0; i < hotPotatoInt; i++){                      // counter starts at holder value and stop when the elimination value is reached
         Link temp = currentLink;
          
         if(currentLink == newLinkList.last){                     // if current is pointing to the last link...
            currentLink.next = newLinkList.first;    
            previousLink = temp;
            currentLink = currentLink.next;
            previousLink.next = currentLink;                      // ...the counter moves from the last link to the first link
         }
         else{
            previousLink = temp;                                  // if current is not the last link...
            currentLink = currentLink.next;         
            previousLink.next = currentLink;                      // ...the counter moves to the next adjacent link
         }   
      }
      currentLink = newLinkList.deleteCurrent(currentLink, previousLink);     //deletes the link current is pointing to
    }
    
    System.out.println("\n" + currentLink.iData);                 // prints the value of the last remaining link
    }
    
    }
    return;      
   }     //end main
}     // end n00853961    