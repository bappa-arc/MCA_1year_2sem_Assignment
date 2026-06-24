#include <stdio.h>
#include <stdlib.h>

#define MAX 100

/* Display Array */
void display(int arr[], int n)
{
    int i;

    printf("Array Elements:\n");

    for(i = 0; i < n; i++)
        printf("%d ", arr[i]);

    printf("\n");
}

/* Bubble Sort */
void bubbleSort(int arr[], int n)
{
    int i, j, temp;

    for(i = 0; i < n - 1; i++)
    {
        for(j = 0; j < n - i - 1; j++)
        {
            if(arr[j] > arr[j + 1])
            {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }

    printf("Array Sorted Using Bubble Sort\n");
}

/* Insertion Sort */
void insertionSort(int arr[], int n)
{
    int i, key, j;

    for(i = 1; i < n; i++)
    {
        key = arr[i];
        j = i - 1;

        while(j >= 0 && arr[j] > key)
        {
            arr[j + 1] = arr[j];
            j--;
        }

        arr[j + 1] = key;
    }

    printf("Array Sorted Using Insertion Sort\n");
}

/* Selection Sort */
void selectionSort(int arr[], int n)
{
    int i, j, min, temp;

    for(i = 0; i < n - 1; i++)
    {
        min = i;

        for(j = i + 1; j < n; j++)
        {
            if(arr[j] < arr[min])
                min = j;
        }

        temp = arr[i];
        arr[i] = arr[min];
        arr[min] = temp;
    }

    printf("Array Sorted Using Selection Sort\n");
}

int main()
{
    int arr[MAX];
    int temp[MAX];
    int n;
    int i;
    int choice;

    printf("Enter number of elements: ");
    scanf("%d", &n);

    printf("Enter array elements:\n");

    for(i = 0; i < n; i++)
        scanf("%d", &arr[i]);

    while(1)
    {
        printf("\n===== SORTING MENU =====\n");
        printf("1. Display Array\n");
        printf("2. Bubble Sort\n");
        printf("3. Insertion Sort\n");
        printf("4. Selection Sort\n");
        printf("5. Exit\n");

        printf("Enter Choice: ");
        scanf("%d", &choice);

        /* Copy original array each time */
        for(i = 0; i < n; i++)
            temp[i] = arr[i];

        switch(choice)
        {
            case 1:
                display(arr, n);
                break;

            case 2:
                bubbleSort(temp, n);
                display(temp, n);
                break;

            case 3:
                insertionSort(temp, n);
                display(temp, n);
                break;

            case 4:
                selectionSort(temp, n);
                display(temp, n);
                break;

            case 5:
                exit(0);

            default:
                printf("Invalid Choice\n");
        }
    }

    return 0;
}