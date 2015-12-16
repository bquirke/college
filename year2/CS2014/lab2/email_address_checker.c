#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>


bool checkForAlphaNum( char* substring )
{
	for ( int i = 0; i < strlen( substring ); i++ )
	{
		if ( !(isalpha( substring[i] ) || isdigit( substring[i] ) ) )
		{
			return false;
		}
	}
	return true;
}
bool checkForRemainder( char * substring )
{
	if ( checkForAlphaNum( substring ) == true )
	{
		return true;
	}
	return false;
}
bool checkForIdentifier( char * substring )
{
	if ( checkForAlphaNum( substring ) == true || checkForRemainder( substring ) == true )
	{
		return true;
	}
	return false;
}
bool checkForTerminator( char * substring, char ** terminators, int length )
{
	for ( int i = 0; i < length; i++ )
	{
		if ( strcmp( terminators[i], substring ) == 0 )
		{
			return true;
		}
	}	
	return false;
}
bool checkForIdentifierList( char* substring )
{
	int i = 0;
	while( substring[i] != '.' && substring[i] != '\0' )
	{
		i += 1;
	}
	if ( substring[i] == '\0' )
	{
		return checkForIdentifier( substring );
	}
	else
	{
		char prefix[i];
		strncpy( prefix, substring, i );
		prefix[i] = '\0';
		i += 1;
		
		if ( checkForIdentifier( prefix ) == true )
		{
			return checkForIdentifierList( &substring[i] );
		}
		
	}
	return false;
}

bool isValidEmailAddress( char * address, char ** terminators, int length )
{
	int i = 0;
	// CHECK BEFORE @ SYMBOL
	while( address[i] != '@' && address[i] != '\0' )
	{
		i += 1;
	}
	char prefix[i];
	strncpy( prefix, address, i);
	prefix[i] = '\0';

	if ( strcmp( &prefix[0], " " ) == 0 || strcmp( &prefix[0], "" ) == 0 )
	{
		return false;
	}

	if ( checkForIdentifierList( prefix ) == false )
	{
		return false;
	}
	i += 1;
	// CHECK AFTER @ SYMBOL
	int before = i;
	while ( address[i] != '.' && address[i] != '\0' )
	{
		i += 1;
	}
	if ( address[i] == '\0' )
	{
		return false;
	}
	char suffix[ i - before ];
	strncpy( suffix, &address[before], i - before );
	suffix[ i - before ] = '\0';
	if ( checkForIdentifierList( suffix ) == false )
	{
		return false;
	}
	i += 1;

	// CHECK FOR VALID TERMINATOR
	before = i;
	while ( address[i] != '\0' )
	{
		i += 1;
	}

	char terminator[i - before];
	strncpy( terminator, &address[before], i - before );
	terminator[i - before] = '\0';

	if ( checkForTerminator( terminator, terminators, length ) == false )
	{
		return false;
	}
	return true;
}

void print_validity(char * address, char** terminators, int length )
{
  printf("The string %s is ", address);
  if (!isValidEmailAddress(address, terminators, length) ) {
    printf("not "); }
  printf("a valid email address\n");
}


int main()
{
	const int length = 5;
	char * address1;
	char * address2;
	char * address3;
	char * address4;
	char * address5;
	char * address6;
	char * address7;


	char * terminators[length];
	terminators[0] = "com";
 	terminators[1] = "net";
  	terminators[2] = "edu";
  	terminators[3] = "ie";
  	terminators[4] = "tv";

  	address1 = "santa.clause@northpole.com";
  	address2 = "santaclause@northpole.com";
  	address3 = "santa@np.com";
	address4 = "nbmre@gmail.com";
	address5 = "nothing@nothing";
	address6 = "name@org.ie";
	address7 = "7numberbefore@invalid.ie";

	print_validity( address6, terminators, length );
	return 0;
}