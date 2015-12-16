#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include "structdef.h"
#include <stdbool.h>
#include <math.h>
#include <string.h>

struct hashtable * newTable( int size )
{
  struct hashtable * theTable = malloc( sizeof( struct hashtable ) );
  (*theTable).table = malloc( sizeof( struct symbol ) * size );
  (*theTable).size = 0;

  int i = 0;
  for ( i; i < theTable -> size; i++ )
  {
    theTable -> table[i] = 0;
  }
  return theTable;
}

void freeTable( struct hashtable * this )
{
  free( (*this).table );
  free( this );
}

unsigned int hash( char c )
{
  unsigned int hash_index = c;
  return hash_index;
}

int symbolQuery( struct hashtable * this, struct symbol * element )
{
  unsigned int index = hash( (element -> u).c );
  if ( (*this).table[ index ] != 0 )
  {
    return 1;
  }
  return 0;
}

void put( struct hashtable * this, struct symbol * element )
{
  unsigned int index = hash( (element -> u).c );
  unsigned int min = 32;
  unsigned int max = 128;

  if ( (index >= min) && (index < max) )
  {
    if ( symbolQuery( this, element ) == 0 )
    {
      (*this).table[index] = element; 
      (*this).size = ( this -> size ) + 1;
    }
    else
    {
      (*this).table[index] -> freq = ( (*this).table[index] -> freq ) + 1;
    }
  }
}

void printTable( struct hashtable * this )
{
  int i = 32;
  for ( i; i < (*this).size+32; i++ )
  {
    struct symbol * temp = (*this).table[i];
  }
}

struct theQueue * createQueue( int size )
{
  struct theQueue * mainQueue = malloc( sizeof( struct theQueue ) );
  (*mainQueue).queue = malloc( sizeof( struct symbol ) * size );
  (*mainQueue).end_index = 1; // end_index, inclusive
  (*mainQueue).start_index = 1; // start_index, inclusive
  (*mainQueue).size = 0;
  return mainQueue;
}

void freeQueue( struct theQueue * this )
{
  free( (*this).queue );
  free( this );
}

void exchange( int i, int j, struct theQueue * this )
{
  struct symbol * swap = this -> queue[i];
  (*this).queue[i] = (*this).queue[j];
  (*this).queue[j] = swap;
} 

int greater( int a, int b, struct theQueue * this )
{
  return ( ((*this).queue[a] -> freq) > ((*this).queue[b] -> freq) );
}


void sink( int k, struct theQueue * this )
{
  while ( 2*k <= ((*this).size ) )
  {
    int j = 2 * k;
    if ( (j < (*this).size) && ( greater( j, j+1, this ) > 0 ) )
    {
      j++;
    }
    if ( greater( k, j, this ) <= 0 )
    {
      break;
    }
    exchange( k, j, this );
    k = j;
  }
}

void swim( int k, struct theQueue * this )
{
  while ( k > 1 && ( greater( k/2, k, this ) > 0 ) )
  {
    exchange( k, k/2, this );
    k = k/2;
  }
}

void enqueue( struct theQueue * this, struct symbol * element )
{

  (*this).queue[this->end_index] = element;
  (*this).size++;
  swim( this -> size, this );
  (*this).end_index++;
}

struct symbol * dequeue( struct theQueue * this )
{
  exchange( 1, (*this).size, this );
  struct symbol * min = this -> queue[ this -> size ];

  (*this).size--;
  (*this).end_index--;
  sink( 1, this );
  return min;
}

struct symbol * min( struct theQueue * this )
{
  return (*this).queue[1];
}

struct symbol * cycleTreeElements( struct symbol * node, char * string_rep )
{
  if( node -> is_leaf == 1 )
  {
    printf( "character: %c\n", node -> u.c );
    printf( "encoded representation: %s\n", string_rep );
    string_rep[ ( ( int ) strlen( string_rep ) ) - 1 ] = '\0';
    return node;
  }
  (string_rep[strlen(string_rep)] = '0');
  cycleTreeElements( node -> u.children.right, string_rep );
  (string_rep[strlen(string_rep)] = '1' );
  cycleTreeElements( node -> u.children.left, string_rep );
  string_rep[ ( ( int ) strlen( string_rep ) ) - 1 ] = '\0';
}

int main(int argc, char ** argv)
{
  struct hashtable * testTable = newTable( 128 );

  unsigned int a = 32;
  for ( a; a < 128; a++ )
  {
    struct symbol * temp = malloc( sizeof( struct symbol ) );
    unsigned char c = a;
    (*temp).u.c = c;
    (*temp).freq = 1;
    put( testTable, temp );
  }

  unsigned char c;
  FILE * file;

  printf( "length of argc: %d\n", argc );
  if ( argc == 3 )
  {
    printf( "file name: %s", argv[2] );
  }
  if ( argc != 3 ) {
    fprintf(stderr, "Useage: huffman <filename>\n");
    exit(1);      // exit with error code
  }
  

  file = fopen(argv[2], "r");
  assert( file != NULL );
  c = fgetc(file);  // attempt to read a byte
  while( !feof(file) )
  {
    c = fgetc(file);
    struct symbol * temp = malloc( sizeof( struct symbol ) );
    (*temp).u.c = c;
    put( testTable, temp );
  }
  fclose(file);

  printTable( testTable );
  
  struct theQueue * mainQueue = createQueue( 128 );
  int b = 32;
  for ( b; b < testTable -> size+32; b++ )
  {
    struct symbol * temp = malloc( sizeof( struct symbol ) );
    (*temp).u.c = testTable -> table[b] -> u.c;
    (*temp).freq = testTable -> table[b] -> freq;
    (*temp).is_leaf = 1;
    enqueue( mainQueue, temp );
  }

  struct symbol * prev;
  struct symbol * next;
  int d = 1;
  while ( mainQueue -> size > 1 )
  {
    prev = dequeue( mainQueue );
    next = dequeue( mainQueue );
    struct symbol * new_symbol = malloc( sizeof( struct symbol ) );
    new_symbol -> freq = ( next -> freq + prev -> freq );
    new_symbol -> is_leaf = 0;
    new_symbol -> u.children.left = prev;
    new_symbol -> u.children.right = next;
    enqueue( mainQueue, new_symbol );
    d++;
  }
  char * temp = malloc( sizeof( char ) * 400 );
  cycleTreeElements( mainQueue -> queue[1], temp );
  
  return 0;  // exit without error code
}