class Link {
   private int iData;
   private Link next;               //next is a Link object
   
// ---------------------------------------------------
   private Link(int value){         //constructor
   value = iData;                   //initialize data in Link
   }
}                                   //end class Link
   
////////////////////////////////////////////////////////////////
class LinkList {
   private Link first;

// ----------------------------------------------------   
  private LinkList() {               //constructor
  first = null;                      //LinkList is empty  
  }
  
// ----------------------------------------------------  
   private insertFirst(int value) {  //Inserts a link at the beginning of the list
   Link newLink = new Link(value);   //creates a new link object
   newLink.next = first;
   first = newLink;
   }
   
// ----------------------------------------------------
   private deleteFirst() {           //Deletes the link at the beginning of the list

   Link temp = first;
   first = first.next;
   return temp;
   }
   
}                                    //end class LinkList    