#include <stdio.h>
#include <stdlib.h>

void create(int **arr, int *size);
void display(int arr[], int size);
void countElements(int size);
void reverseList(int arr[], int size);
void searchElements(int arr[], int size);
void indexedElement(int arr[], int size);
void insertElement(int **arr, int *size);
void deleteElement(int **arr, int *size);
void mergeLists(int **arr, int *size);
void splitList(int arr[], int size);
void sortList(int arr[], int size);


int main(){
    int *arr=NULL;
    int size=0;
    int choice;

    while(1){
        printf("\n----------MENU----------\n");
        printf("1. Create\n");
        printf("2. Display List\n");
        printf("3. Count Elements\n");
        printf("4. Reverse List\n");
        printf("5. Search Element Index\n");
        printf("6. Indexed Element\n");
        printf("7. Insert Element\n");
        printf("8. Delete Element\n");
        printf("9. Merge Lists\n");
        printf("10. Split List\n");
        printf("11. Sort List\n");
        printf("12. Exit\n");
        printf("----------------------------\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice)
        {
        case 1:
            create(&arr, &size);
            break;
        case 2:
            display(arr, size);
            break;
        case 3:
            countElements(size);
            break;
        case 4:
            reverseList(arr, size);
            break;
        case 5:
            searchElements(arr, size);
            break;
        case 6:
            indexedElement(arr, size);
            break;
        case 7:
            insertElement(&arr, &size);
            break;
        case 8:
            deleteElement(&arr, &size);
            break;
        case 9:
                mergeLists(&arr, &size);
                break;
        case 10:
            splitList(arr, size);
            break;
        case 11:
            sortList(arr, size);
            break;
        case 12:
            free(arr);
            printf("Program Exited Successfully.\n");
            exit(0);
        default:
            printf("Invalid choice!");
        }
    }
    return 0;
}

void create(int **arr, int *size){
    int i;

    printf("Enter the size of the array: ");
    scanf("%d", size);

    *arr=(int*)malloc((*size)*sizeof(int));

    if(*arr==NULL){
        printf("Memory Allocation Failed!\n");
        return;
    }

    printf("Enter Elements:\n");
    for (i = 0; i < *size; i++){
        scanf("%d", &(*arr)[i]);
    }
    printf("List Created Successfully.\n");
}

void display(int arr[], int size){
    int i;
    if(arr==NULL || size==0){
        printf("List is empty!\n");
        return;
    }
    printf("List elements:\n");
    for(i = 0 ; i < size ; i++){
        printf("%d\t", arr[i]);
    }
    printf("\n");
}

void countElements(int size){
    printf("Total Elements = %d\n", size);
}


void reverseList(int arr[], int size){
    int i, temp;
    if(arr==NULL || size==0){
        printf("List is empty!\n");
        return;
    }
    for(i = 0; i < size/2; i++){
        temp = arr[i];
        arr[i] = arr[size-i-1];
        arr[size-i-1] = temp;
    }
    printf("List Reversed Successfully.\n");
}

void searchElements(int arr[], int size){
    int element, i, found=0;
    if(arr==NULL || size==0){
        printf("List is empty!\n");
        return;
    }
    printf("Enter element to search: ");
    scanf("%d", &element);

    printf("Element found at index: ");
    for(i = 0; i < size; i++){
        if(arr[i] == element){
            printf("%d\t", i);
            found=1;
        }
    }
    if(!found){
        printf("Not Found!");
    }
    printf("\n");
}

void indexedElement(int arr[], int size){
    int index;

    if (arr == NULL || size == 0){
        printf("List is Empty!\n");
        return;
    }

    printf("Enter index: ");
    scanf("%d", &index);

    if (index < 0 || index >= size){
        printf("Invalid Index!\n");
        return;
    }

    printf("Element at index %d = %d\n", index, arr[index]);
}

void insertElement(int **arr, int *size){
    int index, element, i;

    printf("Enter index to insert: ");
    scanf("%d", &index);

    if (index < 0 || index > *size){
        printf("Invalid Index!\n");
        return;
    }

    printf("Enter element: ");
    scanf("%d", &element);

    *arr = (int *)realloc(*arr, (*size + 1) * sizeof(int));

    if (*arr == NULL){
        printf("Memory Reallocation Failed!\n");
        return;
    }

    for (i = *size; i > index; i--){
        (*arr)[i] = (*arr)[i - 1];
    }

    (*arr)[index] = element;

    (*size)++;

    printf("Element Inserted Successfully.\n");
}

void deleteElement(int **arr, int *size){
    int index, i;

    if (*size == 0){
        printf("List is Empty!\n");
        return;
    }

    printf("Enter index to delete: ");
    scanf("%d", &index);

    if (index < 0 || index >= *size){
        printf("Invalid Index!\n");
        return;
    }

    for (i = index; i < *size - 1; i++){
        (*arr)[i] = (*arr)[i + 1];
    }

    (*size)--;

    *arr = (int *)realloc(*arr, (*size) * sizeof(int));

    printf("Element Deleted Successfully.\n");
}

void mergeLists(int **arr, int *size){
    int n2, i;
    int *arr2;

    printf("Enter size of second list: ");
    scanf("%d", &n2);
    arr2 = (int *)malloc(n2 * sizeof(int));
    if (arr2 == NULL){
        printf("Memory Allocation Failed!\n");
        return;
    }

    printf("Enter elements of second list:\n");
    for (i = 0; i < n2; i++){
        scanf("%d", &arr2[i]);
    }

    *arr = (int *)realloc(*arr, (*size + n2) * sizeof(int));
    if (*arr == NULL){
        printf("Memory Reallocation Failed!\n");
        free(arr2);
        return;
    }

    for (i = 0; i < n2; i++){
        (*arr)[*size + i] = arr2[i];
    }
    *size = *size + n2;
    free(arr2);
    printf("Lists Merged Successfully.\n");
}


void splitList(int arr[], int size){
    int pos, i;

    if (arr == NULL || size == 0){
        printf("List is Empty!\n");
        return;
    }

    printf("Enter the position to split (0 to %d): ", size);
    scanf("%d", &pos);
    if (pos < 0 || pos > size){
        printf("Invalid position!\n");
        return;
    }

    printf("First Half:\n");
    for (i = 0; i < pos; i++){
        printf("%d ", arr[i]);
    }

    printf("\nSecond Half:\n");
    for (i = pos; i < size; i++){
        printf("%d ", arr[i]);
    }
    printf("\n");
}

void sortList(int arr[], int size){
    int i, j, temp;

    if (arr == NULL || size == 0){
        printf("List is Empty!\n");
        return;
    }

    for (i = 0; i < size - 1; i++){
        for (j = 0; j < size - i - 1; j++){
            if (arr[j] > arr[j + 1]){
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }

    printf("List Sorted Successfully.\n");
}