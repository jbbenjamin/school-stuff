#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

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
   struct sNode*   top;
   } STACK;
   
typedef struct sNode
   {
   char            data;
   struct sNode*   link;
   } STACK_NODE;
   
typedef struct qNode
   {
   char            data;
   struct qNode*   next;
   } QUEUE_NODE;
   
typedef struct
   {
   QUEUE_NODE*   front;
   int           count;
   QUEUE_NODE*   rear;
   } QUEUE;
   
   
   
int main(int argc, char* argv[]);
//void sortArray(int ptr[], int size);
bool checkExistence(VERTEX* listOfVerticies[], char currentLetter, int size);
void makeVertex(VERTEX* listOfVerticies[], int size, char currentLetter);
void makeEdge(VERTEX* listOfVerticies[], int runningCount);
void displayAdjacencyList(VERTEX* listOfVerticies[], int size);
void depthSearch(VERTEX* listOfVerticies[], int size);
bool pushVertex(STACK* vStack, char dataIn);
bool popVertex(STACK* vStack, char* dataOut);
void breadthSearch(VERTEX* listOfVerticies[], int size);
bool enqueue (QUEUE* queue, char dataIn);
bool dequeue (QUEUE* queue, char* dataOut);