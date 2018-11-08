#include "myheader.h"

//int findIndex(VERTEX* vertexArray[], char key);
int main(int argc, const char *argv[])
{
	FILE *file;
	file = fopen(argv[1], "r");
	VERTEX* tempVertex = (VERTEX*)malloc(sizeof(VERTEX));
	
	int j, z, c, i, numOfChars, numOfEdges, numOfVertices;
	bool inIt;
 	VERTEX* vertexArray[26];
 	
	for(j = 0; j < 26; j++)
	{
		vertexArray[j] = tempVertex;
	}	
	numOfVertices = 0;
	while ((c = fgetc(file)) !=  EOF)
	{ // get the next character from the file
        if (!isspace(c))
		{ // if c is not a space, increment the character counter, and set inIt boolean to false
			numOfChars++;
			inIt = false;
			for(i = 0; i < 26; i++) 
			{ // for 26 times, do
			   if(c == vertexArray[i]->c)
			   { // check the vertex array, for each character 'c', to see if the character is already stored as a vertex
			 	inIt = true;	
			 	}
			}
			if(!inIt)
			{ /* if the character is not in the vertex array, create a new vertex and assign it the value of 'c', store the vertex in the next index of the vertex array, then increment the counter for vertices*/
			   VERTEX* newVertex = (VERTEX*)malloc(sizeof(VERTEX));
			   newVertex->c = c;
		  	   vertexArray[numOfVertices] = newVertex;
			   numOfVertices++;
			}
			if(numOfChars % 2 == 0)
			{ /* if there have been exactly two characters read, create a new edge, have the previous vertex point to the new edge, then have the new edge's v field point to the current vertex to create an edge between them */
				EDGE* newEdge = (EDGE*)malloc(sizeof(EDGE));
				vertexArray[findIndex(vertexArray, c) - 1]->p = newEdge;
				newEdge->v = vertexArray[findIndex(vertexArray, c)];
				numOfEdges++;
			}
			printf ("character read from file is: %c\n", c);
		}
	}

    for(i = 0; i < numOfVertices; i++)
    {
    printf("character of the vertex at vertexArray[%d] = %c\n", i, vertexArray[i]->c );
    }
    
    for(z = 0; z < numOfEdges; z++)	//print edges
    {
    printf("there is an edge from %c -> %c\n", vertexArray[z]->c, vertexArray[z]->p->v->c);
    }
    return 0;

}


