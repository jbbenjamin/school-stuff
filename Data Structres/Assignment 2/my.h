#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

void sortArray(int ptr[], int size);
//boolean checkExistence(VERTEX listOfVerticies[], char currentLetter);
//void makeVertex (VERTEX listOfVerticies[], int size, char currentLetter);
//void makeEdge (VERTEX listOfVerticies[], int runningCount);

int main(int argc, char* argv[]);

/* Forward declaration */
struct EDGETAG;


typedef struct
{
    char c;
    bool isVisited;
    struct EDGETAG* p;
} VERTEX;


typedef struct EDGETAG
{
    VERTEX* v;
    struct EDGETAG* q;
} EDGE;


typedef struct
   {
   int            count;
   struct node*   top;
   } STACK;
typedef struct node
   {
   int            data;
   struct node*   link;
   } STACK_NODE;