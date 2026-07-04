#include <stdio.h>
#include <stdlib.h>

#define MAX 100
#define DISK_SIZE 199

void sort(int arr[], int n)
{
    int i, j, temp;
    for(i = 0; i < n - 1; i++) {
        for(j = 0; j < n - i - 1; j++) {
            if(arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}

void scan(int req[], int n, int head)
{
    int i, pos = n;
    int seek = 0;
    sort(req, n);
    for(i = 0; i < n; i++) {
        if(req[i] >= head) {
            pos = i;
            break;
        }
    }
    printf("\nSCAN Order:\n");
    printf("%d ", head);
    for(i = pos; i < n; i++) {
        printf("-> %d ", req[i]);
        seek += abs(head - req[i]);
        head = req[i];
    }
    if(head != DISK_SIZE) {
        seek += abs(head - DISK_SIZE);
        head = DISK_SIZE;
    }
    for(i = pos - 1; i >= 0; i--) {
        printf("-> %d ", req[i]);
        seek += abs(head - req[i]);
        head = req[i];
    }
    printf("\nTotal Seek Time = %d\n", seek);
}

void cscan(int req[], int n, int head)
{
    int i, pos = n;
    int seek = 0;
    sort(req, n);
    for(i = 0; i < n; i++) {
        if(req[i] >= head) {
            pos = i;
            break;
        }
    }
    printf("\nCSCAN Order:\n");
    printf("%d ", head);
    for(i = pos; i < n; i++) {
        printf("-> %d ", req[i]);
        seek += abs(head - req[i]);
        head = req[i];
    }
    if(head != DISK_SIZE) {
        seek += abs(head - DISK_SIZE);
    }
    seek += DISK_SIZE;
    head = 0;
    for(i = 0; i < pos; i++) {
        printf("-> %d ", req[i]);
        seek += abs(head - req[i]);
        head = req[i];
    }
    printf("\nTotal Seek Time = %d\n", seek);
}

int main()
{
    int req[MAX];
    int n, head, choice;
    printf("Enter number of requests: ");
    scanf("%d", &n);
    printf("Enter disk requests:\n");
    for(int i = 0; i < n; i++)
        scanf("%d", &req[i]);
    printf("Enter initial head position: ");
    scanf("%d", &head);
    do
    {
        printf("\n===== Disk Scheduling =====\n");
        printf("1. SCAN\n");
        printf("2. CSCAN\n");
        printf("3. Exit\n");
        printf("Enter choice: ");
        scanf("%d", &choice);
        switch(choice)
        {
            case 1:
                scan(req, n, head);
                break;

            case 2:
                cscan(req, n, head);
                break;

            case 3:
                printf("Exiting...\n");
                break;

            default:
                printf("Invalid choice!\n");
        }

    } while(choice != 3);
    return 0;
}