#include "myheader.h"

bool checkExistence(VERTEX* listOfVerticies[], char currentLetter, int size)
{

int i = 0;

 
for(i = 0; i < size; i++){
   if(currentLetter == (*listOfVerticies[i]).c){
      return true;
   }   
   //else
      //i++;
}
return false;
}