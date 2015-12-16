// let the hash function be the ASCII value of the character

struct hashtable
{
	int size;
	struct symbol ** table;
} hastable;

struct hashtable * newTable( int size );
int symbolQuery( struct hashtable * this, struct symbol * element );
void put( struct hashtable * this, struct symbol * element );
//struct symbol * get_symbol( struct hashtable * this, char keyId );
unsigned int hash( char c );
void freeTable( struct hashtable * this );

struct node
{
	struct symbol * data;
	struct node * next;
	struct node * prev;
} node;

struct symbol 
{
	union u
	{
		struct children 
		{
			struct symbol * left;
			struct symbol * right;
		} children;
		char c;
	} u;
	int freq;
	int is_leaf;
} symbol;

struct theQueue
{
	struct symbol ** queue;
	int end_index;
	int start_index;
	int size;
} theQueue;

struct theQueue * createQueue( int size );
void freeQueue( struct theQueue * this );
void enqueue( struct theQueue * this, struct symbol * element );
struct symbol * dequeue( struct theQueue * this );
struct symbol * min( struct theQueue * this );
void checkChildNodes( struct theQueue * this );
