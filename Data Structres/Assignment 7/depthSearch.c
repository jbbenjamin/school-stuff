#include "myheader.h"

void depthSearch(VERTEX* listOfVerticies[], int size){

VERTEX* current;
STACK* vStack;

int i;
vStack = malloc(sizeof(STACK));

for(i = 0; i < size; i++){
   current = listOfVerticies[i];
   printf("\n %c" ,(*current).c);
   (*current).isVisited = true;
   pushVertex(vStack, (*listOfVerticies[i]).c);

   
   while((*current).p != NULL){
      current = current->p->v;
      printf("\n %c" ,(*current).c);
      (*current).isVisited = true;
      pushVertex(vStack, (*listOfVerticies[i]).c);
   }
   
   popVertex(vStack, &(*current).c);
   current = (*vStack).top;
   while((*current).p != NULL && current->p->v->isVisited == true){
      popVertex(vStack, &(*current).c);
   }
}
   return;
}
