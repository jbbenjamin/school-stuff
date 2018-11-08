#include "myheader.h"

bool pushVertex (STACK* vStack, char dataIn)
{

   STACK_NODE* vNew;
   bool        success;
   
   vNew = (STACK_NODE*)malloc(sizeof (STACK_NODE));
   if (!vNew)
      success = false;
   else
      {
      (*vNew).data = dataIn;
      (*vNew).link = (*vStack).top;
      (*vStack).top = vNew;
      (*vStack).count++;
      success = true;
   }
 return success;
}