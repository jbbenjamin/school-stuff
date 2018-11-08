#include "myheader.h"

bool dequeue (QUEUE* queue, char* dataOut){

QUEUE_NODE* deleteLoc;

if(!(*queue).count)
   return false;
   
   *dataOut = queue->front->data;
   deleteLoc = (*queue).front;
   
   if((*queue).count == 1)
      (*queue).rear = (*queue).front = NULL;
   else
      (*queue).front = queue->front->next;
   ((*queue).count)--;
return true;
}