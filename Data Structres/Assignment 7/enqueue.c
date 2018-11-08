#include "myheader.h"

bool enqueue (QUEUE* queue, char dataIn){

QUEUE_NODE* newPtr;

if(!(newPtr = malloc(sizeof(QUEUE_NODE))))
   return false;
   
   (*newPtr).data = dataIn;
   (*newPtr).next = NULL;
   
   if((*queue).count == 0)
      (*queue).front = newPtr;
   else
      queue->rear->next = newPtr;
   ((*queue).count)++;
   (*queue).rear = newPtr;
return true;
}