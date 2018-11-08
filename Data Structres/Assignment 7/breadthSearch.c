#include "myheader.h"

void breadthSearch(VERTEX* listOfVerticies[], int size){

VERTEX* current;
EDGE* currentEdge;
QUEUE* vQueue;

current = listOfVerticies[0];
printf("\n %c" ,(*current).c);
(*current).isVisited = true;
enqueue(vQueue, (*current).c);
   while((*vQueue).front != NULL){                       //while queue is not empty
      if((*current).p != NULL){                             //if the vertext has an edge...
         currentEdge = (*current).p;                        
      }
      dequeue(vQueue, &(*current).c);                         //remove vertex from queue
      
      currentEdge->v->isVisited = true;
      printf("\n %c" ,(*currentEdge).v);
      enqueue(vQueue, currentEdge->v->c);
      while((*currentEdge).q != NULL && currentEdge->v->isVisited == false){   
         currentEdge = (*currentEdge).q;
         currentEdge->v->isVisited = true;
         printf("\n %c" , currentEdge->v->c);
         enqueue(vQueue, currentEdge->v->c);
      }
   }
return;
}      
   