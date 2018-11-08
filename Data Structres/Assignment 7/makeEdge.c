#include "myheader.h"

void makeEdge(VERTEX* listOfVerticies[], int runningCount)
{
int i = (runningCount / 2);
EDGE* ei;
(*ei).v = listOfVerticies[(runningCount - 1)];
(*ei).q = NULL;

if(listOfVerticies[(runningCount - 2)]->p == NULL){
   listOfVerticies[(runningCount - 2)]->p = ei;
}

else{
EDGE* current;
current = listOfVerticies[(runningCount - 2)]->p;
   while((*current).q != NULL){
      current = (*current).q;
   }
   ei = (*current).q;
   
}
printf("there is an edge from %c -> %c\n", listOfVerticies[(runningCount - 2)]->c, listOfVerticies[(runningCount - 1)]->p->v->c);          
return;
}