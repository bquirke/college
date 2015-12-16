
struct intSet
{
	int *array;
	int maxElements;
	int currentElements;
};

struct intSet *newIntSet();
int intSetLookup( struct intSet *currentArray, int item );
int intSetAdd( struct intSet *addItem, int item );
int intSetRemove( struct intSet *removeItem, int item );
//void intSetUnion( struct intSet *dest, struct intSet *src1, struct intSet src2 );
int intSetIntersect( struct intSet *dest, struct intSet *src1, struct intSet *src2 );
//void createNewArray( struct intSet *destArray, struct intSet *fullArray );
