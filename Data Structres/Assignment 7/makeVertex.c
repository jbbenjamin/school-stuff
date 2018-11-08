#include "myheader.h"

void makeVertex (VERTEX* listOfVerticies[], int size, char currentLetter)
{
int i = size;
(i += 1);
VERTEX* vi;
(*vi).c = currentLetter;
(*vi).isVisited = false;
(*vi).p = NULL;

listOfVerticies[size] = vi;
printf("%c", (*vi).c);
return;
}