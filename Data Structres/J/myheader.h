#ifndef H_MYHEADER_INCLUDED // include guard
#define H_MYHEADER_INCLUDED

#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>


struct EDGETAG;

typedef struct
{
	char c;
	bool isVisited;
	struct EDGETAG* p;
}  VERTEX;

typedef struct EDGETAG
{
	VERTEX* v;
	struct EDGETAG* q;
}  EDGE;

int findIndex (VERTEX* vertexArray[], char);
inline int isspace(int);

#endif // H_MYHEADER_INCLUDED