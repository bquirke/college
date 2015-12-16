static void selectionSort(int a[], int size)
{
        int i, j, minIndex, tmp;
        int iterationsA = 0;
        int iterationsB = 0;
        int iterationsOne = 0;

        for( i = 0; i < size - 1; i++ )
        {
            minIndex = i;

            for (j = i + 1; j < size; j++)
            {
                if (a[j] < a[minIndex])
                {
                    minIndex = j;
                }
                iterationsA++;
            }

            if (minIndex != i)
            {
                tmp = a[i];
                a[i] = arr[minIndex];
                a[minIndex] = tmp;
            }
            iterationsB++;
        }

    iterationsOne = ( iterationsA + iterationsB );
    
    printf( "%s\n", iterationsOne );
}

/* insertion sort algorithm: place unsorted array
   elements into the correct location in an initially
   empty sorted part.*/

void insertionSort(int a[], int size)
{
    int i, j ,tmp;
    int iterationsTwo = 0;

    for (i = 1; i < size; i++)
    {
        j = i;

        while (j > 0 && a[j - 1] > a[j])
        {
            tmp = a[j];
            a[j] = a[j - 1];
            a[j - 1] = tmp;
            j--;
        }

    //print_array( a,5 );
        iterationsTwo++;
    }
    printf( "%s\n", iterationsTwo );
}

/* bubble sort algorithm: repeatedly compare and swap
   adjacent array elements. */

void bubbleSort(int a[], int size)
{
    bool swapped = true;
    int j = 0;
    int tmp;
    int iterationsThree = 0;

    while (swapped)
    {
        swapped = false;
        j++;

        for (int i = 0; i < size - j; i++)
        {
            if (a[i] > a[i + 1])
            {
                tmp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = tmp;
                swapped = true;
            }
            iterationsThree++;
        }
    }
    printf( "%s\n", iterationsThree );
}