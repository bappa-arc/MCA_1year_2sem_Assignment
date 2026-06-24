#include <stdio.h>
#include <stdlib.h>

#define MAX 100

/* Iterative Linear Search */
int linearSearchIterative(int arr[], int n, int key)
{
    int i;

    for(i = 0; i < n; i++)
    {
        if(arr[i] == key)
            return i;
    }

    return -1;
}

/* Recursive Linear Search */
int linearSearchRecursive(int arr[], int n, int key, int index)
{
    if(index >= n)
        return -1;

    if(arr[index] == key)
        return index;

    return linearSearchRecursive(arr, n, key, index + 1);
}

/* Iterative Binary Search */
int binarySearchIterative(int arr[], int n, int key)
{
    int low = 0;
    int high = n - 1;
    int mid;

    while(low <= high)
    {
        mid = (low + high) / 2;

        if(arr[mid] == key)
            return mid;

        else if(arr[mid] < key)
            low = mid + 1;

        else
            high = mid - 1;
    }

    return -1;
}

/* Recursive Binary Search */
int binarySearchRecursive(int arr[], int low, int high, int key)
{
    int mid;

    if(low > high)
        return -1;

    mid = (low + high) / 2;

    if(arr[mid] == key)
        return mid;

    if(key < arr[mid])
        return binarySearchRecursive(arr, low, mid - 1, key);

    return binarySearchRecursive(arr, mid + 1, high, key);
}

/* Display Array */
void display(int arr[], int n)
{
    int i;

    printf("Array Elements:\n");

    for(i = 0; i < n; i++)
        printf("%d ", arr[i]);

    printf("\n");
}

int main()
{
    int arr[MAX];
    int n;
    int i;
    int choice;
    int key;
    int pos;

    printf("Enter number of elements: ");
    scanf("%d", &n);

    printf("Enter array elements:\n");

    for(i = 0; i < n; i++)
        scanf("%d", &arr[i]);

    while(1)
    {
        printf("\n===== SEARCH MENU =====\n");
        printf("1. Display Array\n");
        printf("2. Iterative Linear Search\n");
        printf("3. Recursive Linear Search\n");
        printf("4. Iterative Binary Search\n");
        printf("5. Recursive Binary Search\n");
        printf("6. Exit\n");

        printf("Enter Choice: ");
        scanf("%d", &choice);

        switch(choice)
        {
            case 1:
                display(arr, n);
                break;

            case 2:
                printf("Enter element to search: ");
                scanf("%d", &key);

                pos = linearSearchIterative(arr, n, key);

                if(pos != -1)
                    printf("Element found at index %d\n", pos);
                else
                    printf("Element not found\n");

                break;

            case 3:
                printf("Enter element to search: ");
                scanf("%d", &key);

                pos = linearSearchRecursive(arr, n, key, 0);

                if(pos != -1)
                    printf("Element found at index %d\n", pos);
                else
                    printf("Element not found\n");

                break;

            case 4:
                printf("Enter element to search: ");
                scanf("%d", &key);

                pos = binarySearchIterative(arr, n, key);

                if(pos != -1)
                    printf("Element found at index %d\n", pos);
                else
                    printf("Element not found\n");

                break;

            case 5:
                printf("Enter element to search: ");
                scanf("%d", &key);

                pos = binarySearchRecursive(arr, 0, n - 1, key);

                if(pos != -1)
                    printf("Element found at index %d\n", pos);
                else
                    printf("Element not found\n");

                break;

            case 6:
                exit(0);

            default:
                printf("Invalid Choice\n");
        }
    }

    return 0;
}