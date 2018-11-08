#include "my.h"

void sortArray(int ptr[], int size);

int main (int argc, char* argv[])
{

FILE* spFile;
int size = 0;


spFile = fopen(argv[1], "r");

fscanf(spFile, "%d", &size);	//read size of array from file

int* ptr;
ptr = (int*)calloc (size, sizeof(int));	//allocate space for array


int i;
for(i = 0; i < size; i++)
{
	fscanf(spFile, "%d", &ptr[i]);	//fill array with numbers read from file	
}


sortArray(ptr, size);	//call sortArray function while passing the "ptr" array and "size" vriable



int searchIndex;

do
   {
   scanf("%d", &searchIndex);	//scan for index input
   if (searchIndex >= 0)
      printf("%d\n\n", ptr[searchIndex]);	//prints the element in the requested index 
   } while (searchIndex >= 0);


return 0;
}	//end main
