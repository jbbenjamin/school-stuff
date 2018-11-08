#include "myheader.h"

int findIndex(VERTEX* vertexArray[], char key)
{
    int i;
    for (i = 0; i < 26; i++)
    {
            if(key == vertexArray[i]->c)
            {
                return i;
            }
    }
    return i;
}


