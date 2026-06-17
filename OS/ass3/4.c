#include <stdio.h>

int main()
{
    int nb, np, i, j;

    printf("Enter number of memory blocks: ");
    scanf("%d", &nb);

    printf("Enter number of processes: ");
    scanf("%d", &np);

    int block[nb], process[np];
    int allocation[np];
    int used[nb];

    printf("\nEnter sizes of memory blocks:\n");
    for(i = 0; i < nb; i++){
        scanf("%d", &block[i]);
        used[i] = 0;
    }

    printf("\nEnter sizes of processes:\n");
    for(i = 0; i < np; i++){
        scanf("%d", &process[i]);
        allocation[i] = -1;
    }

    for(i = 0; i < np; i++){
        for(j = 0; j < nb; j++){
            if(!used[j] && block[j] >= process[i]){
                allocation[i] = j;
                used[j] = 1; 
                break;
            }
        }
    }

    printf("\nProcess No.\tProcess Size\tBlock No.\n");

    for(i = 0; i < np; i++){
        printf("P%d\t\t%d\t\t", i + 1, process[i]);

        if(allocation[i] != -1)
            printf("%d\n", allocation[i] + 1);
        else
            printf("Not Allocated\n");
    }

    return 0;
}