#include "myheader.h"

void displayAdjacencyList(VERTEX* listOfVerticies[], int size){

int i = 0;
EDGE* current;

for(i = 0; i < size; i++){
   printf("\n %c" ,(*listOfVerticies[i]).c);
   current = (*listOfVerticies[i]).p;
   printf("%c", current->v->c);
   while((*current).q != NULL){
      current = (*current).q;
      printf("-->");
      printf("%c", current->v->c);
   }
}
return;
}
