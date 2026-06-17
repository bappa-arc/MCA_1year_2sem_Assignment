#include <stdio.h>
#include <stdlib.h>

int main()
{
    int n, i;
    int head, totalSeek = 0;

    printf("Enter number of disk requests: ");
    scanf("%d", &n);

    int request[n];

    printf("Enter disk request queue:\n");
    for(i = 0; i < n; i++)
    {
        scanf("%d", &request[i]);
    }

    printf("Enter initial head position: ");
    scanf("%d", &head);

    printf("\nSeek Sequence: %d", head);

    for(i = 0; i < n; i++)
    {
        totalSeek += abs(request[i] - head);
        head = request[i];

        printf(" -> %d", head);
    }

    printf("\n\nTotal Seek Time = %d", totalSeek);

    printf("\nAverage Seek Time = %.2f\n",
           (float)totalSeek / n);

    return 0;
}