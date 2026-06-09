#include <stdio.h>

int main()
{
    int nb, np, i, j;

    // nb = number of memory blocks
    // np = number of processes

    printf("Enter number of memory blocks: ");
    scanf("%d", &nb);

    printf("Enter number of processes: ");
    scanf("%d", &np);

    int block[nb], process[np];
    int allocation[np];

    printf("\nEnter sizes of memory blocks:\n");
    for(i = 0; i < nb; i++){
        scanf("%d", &block[i]);
    }

    printf("\nEnter sizes of processes:\n");
    for(i = 0; i < np; i++){
        scanf("%d", &process[i]);
        allocation[i] = -1;
    }

    for(i = 0; i < np; i++){
        int worstIndex = -1;

        for(j = 0; j < nb; j++){
            if(block[j] >= process[i]){
                if(worstIndex == -1 || block[j] > block[worstIndex]){
                    worstIndex = j;
                }
            }
        }

        if(worstIndex != -1){
            allocation[i] = worstIndex;

            block[worstIndex] -= process[i];
        }
    }

    printf("\nProcess No.\tProcess Size\tBlock No.\n");

    for(i = 0; i < np; i++)
    {
        printf("P%d\t\t%d\t\t", i + 1, process[i]);

        if(allocation[i] != -1)
        {
            printf("%d\n", allocation[i] + 1);
        }
        else
        {
            printf("Not Allocated\n");
        }
    }

    return 0;
}