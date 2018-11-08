//#include <my.h>
#include <stdio.h>
#include <stdlib.h>

//int makeArray(int* ptr)

int main(int argc, char* argv[])
{

FILE* spFile;
int size = 0;

spFile = fopen(argv[1], "r");

fscanf(spFile, "%d", &size);
printf("The size is: %d", size);

int* ptr;
ptr = (int*)calloc (size, sizeof(int));

int i;
int temp;
for(i = 0; i < size; i++)
{
	fscanf(spFile, "%d", &ptr[i]);	
}
printf("\nThe first element is: %d", ptr[0]);
printf("\nThe fifth element is: %d", ptr[4]);
printf("\nThe eighth element is: %d\n\n", ptr[7]); 


int j;
int oddSwaps = 1;
int evenSwaps = 1;
while(oddSwaps !=0 || evenSwaps !=0)
{
oddSwaps = 0;
evenSwaps = 0;

for(j = 1; j < (size - 1); j += 2)
{

	if(ptr[j + 1] < ptr[j])
	{
	temp = ptr[j];
	ptr[j] = ptr[j + 1];
	ptr[j + 1] = temp;
	oddSwaps++;
	} //if	
} //for

for(j = 0; j < size; j += 2)
{
	if(ptr[j + 1] < ptr[j])
	{
	temp = ptr[j];
	ptr[j] = ptr[j + 1];
	ptr[j + 1] = temp;
	evenSwaps++;
	} //if	
} //for

} //while



int searchIndex;
do
   {
   scanf("%d", &searchIndex);
   if (searchIndex >= 0)
      printf("%d\n\n", ptr[searchIndex]);
   } while (searchIndex >= 0);


return 0;
}


