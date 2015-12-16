#include <stdio.h>
#include <stdlib.h>
#include "setsfile.h"

int MIN_SIZE = 16;


struct intSet * newIntSet()
{
	struct intSet *newSet = malloc(sizeof(struct intSet));
	(*newSet).array = malloc(sizeof(int) *MIN_SIZE);
	(*newSet).maxElements = MIN_SIZE;
	(*newSet).currentElements = 0;
	return newSet;
};

int intSetLookup( struct intSet *currentArray, int item )
{
	for( int i=0; i<(*currentArray).currentElements; i++ )
	{
		if( (*currentArray).array[i] == item )
		{
			return 1;
		}
	}
	return 0;
}

int intSetAdd( struct intSet *addItem, int item )
{
	//printf("Enter the integer you wish to add: ");
	//scanf("%d", item);
	if( intSetLookup( addItem, item ) != 1 )
	{
		(*addItem).array[(*addItem).currentElements] = item;
		(*addItem).currentElements++;
		return 1;
	}
	return 0;
}

int intSetRemove( struct intSet *removeItem, int item )
{
	if( intSetLookup( removeItem, item ) == 1 )
	{
		int i = 0;
		while( (*removeItem).array[i] != item && i < (*removeItem).currentElements )
		{
			i++;
		}
		int temp = (*removeItem).array[(*removeItem).currentElements - 1 ];
		(*removeItem).array[i] = temp;
		(*removeItem).currentElements--;
		return 1;
	}
	return 0;
}

//ADD EVERY ELEMENT OF THE SET TO A NEW SET, WITHOUT DUPLICATING ELEMENTS
/*void intSetUnion( struct intSet *dest, struct intSet *src1, struct intSet src2 )
{
	for( int i=0; i<(*src1).currentElements; i++ )
	{
		for( int j=0; j<(*src2).currentElements; j++ )
		{
			if( intSetLookup( src1, (*src2).currentElements[j] ) != 1 )
			{
				(*dest).currentElements = (*src1).currentElements[i];
				(*dest).currentElements = (*src2).currentElements[j];
			}
			else
			{
				(*dest).currentElements = (*src1).currentElements[i];
			}
		}
	}
}*/



//MOVE THE COMMON ELEMENTS OF SET 1 AND SET 2 INTO A DESTINATION SET
int intSetIntersect( struct intSet *dest, struct intSet *src1, struct intSet *src2 )
{
	int count = 0;
	for( int i=0; i<(*src1).currentElements; i++ )
	{
		for( int j=0; j<(*src2).currentElements; j++ )
		{
			if( (*src1).array[i] == (*src2).array[j] )
			{
				(*dest).array[count] = (*src1).array[i];
				//(*dest).array[count] = (*src1).currentElements;
				count++;
				return 1;
			}
		}
	}
	return 0;
}

/*
void createNewArray( struct intSet *destArray, struct intSet *fullArray )
{
	if( (*fullArray).maxElements >= MIN_SIZE )
	{
		MIN_SIZE = ( (*fullArray).maxElements*2 );

		for( int i=0; i<(*fullArray).currentElements; i++ )
		{
			(*destArray).currentElements[i] = (*fullArray).currentElements[i];
		}
	}
	free(fullArray);
}*/

int main()
{
	struct intSet *newSet = newIntSet();
	struct intSet *setTwo = newIntSet();
	struct intSet *intersectionSet = newIntSet();

	int testItem = 3;
	intSetAdd( newSet, testItem );

	intSetAdd( newSet, 4 );
	intSetAdd( newSet, 5 );
	//printf( "newSet.currentElements: %d\n", (*newSet).currentElements );
	printf( "Contains 3: %d\n", intSetLookup( newSet, 3 ) );
	printf( "Contains 4: %d\n", intSetLookup( newSet, 4 ) );
	printf( "Contains 5: %d\n", intSetLookup( newSet, 5 ) );
	printf( "Contains 6: %d\n", intSetLookup( newSet, 6 ) );
	intSetRemove( newSet, 4 );
	intSetRemove( newSet, 5 ); 
	printf( "Contains 4 now? %d\n", intSetLookup( newSet, 4 ) );
	printf( "Contains 5 now? %d\n", intSetLookup( newSet, 5 ) );
	intSetAdd( newSet, 5 );
	printf( "Contains 5 now? %d\n", intSetLookup( newSet, 5 ) );
	intSetAdd( setTwo, 3 );
	intSetAdd( setTwo, 4 );
	intSetAdd( setTwo, 5 );
	printf( "Contains 4 now? %d\n", intSetLookup( setTwo, 4 ) );
	printf( "Contains 5 now? %d\n", intSetLookup( setTwo, 5 ) );
	intSetIntersect( intersectionSet, newSet, setTwo );
	printf( "Contains 3 now? %d\n", intSetLookup( intersectionSet, 3 ) );
	printf( "Contains 4 now? %d\n", intSetLookup( intersectionSet, 4 ) );
	printf( "Contains 5 now? %d\n", intSetLookup( intersectionSet, 5 ) );

	//newSet -> array = malloc( 
	//			sizeof( int ) * ( ( (*AaddItem).current_elements + 1 ) * 2 ) );


	//struct intSet *intersectionSet = newIntSet();
	//struct intSet *unionSet = newIntSet();


	//intSetUnion( unionSet, newSet, *setTwo );
	//intSetIntersect( intersectionSet, newSet, *setTwo );
	//createNewArray( setTwo, newSet );

	return 0;
}