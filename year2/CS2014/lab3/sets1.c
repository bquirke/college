#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>

struct set
{
	int *arrayPointer;
	int maxElements;
	int currentElements;
};

struct intSet *newIntSet()
{
	//CREATE SET
	struct intSet *newSet = malloc(sizeof(struct intSet));
	//POPULATE FIELDS
	(*newSet).array = malloc(sizeof(int) * MIN_SIZE);
	(*newSet).maxElements = MIN_SIZE;
	(*newSet).currentElements = 0;
	return newSet;

	/*struct set theSet;
	theSet.maxElements = (theSet.currentElements*2);
	int newSetArray[theSet.maxElements];
	p = theSet.arrayPointer;
	//p = &newSetArray[0];
	return theSet;*/
}

int intSetLookup( struct set *this, int item )
{
	new();
	struct set theSet;
	for( int i=0; i<theSet.maxElements; i++ )
	{
		if( theSet.maxElements[i] == item )
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	int result = intSetLookup( theSet, item );
	printf("%d", result);
}

int intSetAdd( struct set *this, int item )
{
	//new();
	struct set theSet;
	printf("Enter a number to add to the set: ");
	scanf("%d", item);
	if( intSetLookup( theSet, item ) != 1 )
	{
		for( int i=0; i<theSet.maxElements; i++ )
		{
			if( theSet.maxElements[i] != NULL )
			{
				theSet.currentElements += 1;
				theSet.maxElements[i] = item;
			}
			else
			{
				return 0;
			}
		}
	}
}

int intSetRemove( struct set *this, int item )
{
	//new();
	struct set theSet;
	printf("Enter the number you wish to remove from the set: ");
	scanf("%d", item);
	if( intSetLookup( theSet, item ) == 1 )
	{
		for( int i=0; i<theSet.maxElements[i]; i++ )
		{
			if( theSet.maxElements[i] == item )
			{
				theSet.currentElements -= 1;
				theSet.maxElements[i] = NULL;
			}
			else
			{
				return 0;
			}
		}
	}
}

// MORE METHODS TO FOLLOW HERE ...

int checkIfSetFull( struct set *this )
{
	int count = 0;
	struct set theSet;
	for( int i=0; i<theSet.maxElements; i++ )
	{
		if( theSet.maxElements[i] != NULL )
		{
			count++;
		}
	}
	if( count >= theSet.currentElements )
	{
		new();
	}
}