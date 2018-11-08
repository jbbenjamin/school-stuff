#include "my.h"

void sortArray(int ptr[], int size)
{


int j;
int temp;
int oddSwaps = 1;
int evenSwaps = 1;


while(oddSwaps !=0 || evenSwaps !=0)	//outer loop condition - stops when no odd or even swaps are made
{
oddSwaps = 0;
evenSwaps = 0;

for(j = 1; j < (size - 1); j += 2)	//odd sort loop
{

	if(ptr[j + 1] < ptr[j])
	{
	temp = ptr[j];
	ptr[j] = ptr[j + 1];
	ptr[j + 1] = temp;
	oddSwaps++;
	} //if	
} //end odd sort loop

for(j = 0; j < size; j += 2)	//even sort loop
{
	if(ptr[j + 1] < ptr[j])
	{
	temp = ptr[j];
	ptr[j] = ptr[j + 1];
	ptr[j + 1] = temp;
	evenSwaps++;
	} //if	
} //end even sort loop

} //while

return;

}	//end sortArray function





