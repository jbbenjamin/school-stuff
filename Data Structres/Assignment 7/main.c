#include "myheader.h"

//void sortArray(int ptr[], int size);

int main (int argc, char* argv[])
{

FILE* spFile;
char letter;
int listSize = 0;
int count = 0;
int j;

VERTEX* tempVertex = (VERTEX*)malloc(sizeof(VERTEX));
VERTEX* vertexList[26];


for(j = 0; j < 26; j++)
	{
		vertexList[j] = tempVertex;
	}	

spFile = fopen(argv[1], "r");

fscanf(spFile, "%c", &letter);	                           //read first letter
count++;

makeVertex(vertexList, listSize, letter);                //make a vertex
listSize++;


fscanf(spFile, "%c", &letter);                              //read next letter


while(fscanf(spFile, "%c", &letter) != EOF){                                       // while there's still more data to be read, read data, and...
   if(checkExistence(vertexList, letter, listSize) == false)
   {                                                        //if letter isn't already a vertex
      makeVertex(vertexList, listSize, letter);             //make a vertex
      count++;
      listSize++;
   }
   else{
   count++;
   }
   
   if((count % 2) == 0){                                     // if two letters have been read...
      makeEdge(vertexList, count);                           // make an edge
   }
   
   fscanf(spFile, "%c", &letter);                            //read next letter
} // while

displayAdjacencyList(vertexList, listSize);

depthSearch(vertexList, listSize);

breadthSearch(vertexList, listSize);

return 0;
}	//end main
