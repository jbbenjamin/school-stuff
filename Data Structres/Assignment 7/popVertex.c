#include "myheader.h"

bool popVertex (STACK* vStack, char* dataOut)
{

   STACK_NODE* vNew;
   bool        success;
   
   if ((*vStack).top)
   {
      success = true;
      *dataOut = vStack->top->data;
      vNew = (*vStack).top;
      (*vStack).top = vStack->top->link; 
      (*vStack).count--;
      free (vNew);
   }
   else
      {
      success = false;
      }
   return success;
}