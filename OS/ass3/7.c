#include <stdio.h>

int main()
{
    int pages[] = {9,8,11,7,12,8,7,6,8,12,5,4,12,3,10,11,12,4,9,4};
    int n = 20;
    int frames;

    printf("Enter number of frames: ");
    scanf("%d",&frames);

    int frame[frames];
    int recent[frames];

    int pageFaults = 0, hits = 0;

    for(int i=0;i<frames;i++)
    {
        frame[i] = -1;
        recent[i] = -1;
    }

    printf("\nPage\tFrames\t\t\tStatus\n");
    printf("------------------------------------------\n");

    for(int i=0;i<n;i++)
    {
        int found = 0;

        for(int j=0;j<frames;j++)
        {
            if(frame[j] == pages[i])
            {
                found = 1;
                recent[j] = i;
                hits++;
                break;
            }
        }

        if(!found)
        {
            pageFaults++;

            int pos = 0;

            for(int j=0;j<frames;j++)
            {
                if(frame[j] == -1)
                {
                    pos = j;
                    break;
                }

                if(recent[j] < recent[pos])
                    pos = j;
            }

            frame[pos] = pages[i];
            recent[pos] = i;
        }

        printf("%d\t", pages[i]);

        for(int j=0;j<frames;j++)
        {
            if(frame[j] == -1)
                printf("- ");
            else
                printf("%d ", frame[j]);
        }

        if(found)
            printf("\tHit\n");
        else
            printf("\tFault\n");
    }

    printf("\nTotal References = %d", n);
    printf("\nPage Faults = %d", pageFaults);
    printf("\nHits = %d", hits);

    printf("\nFault Ratio = %.2f",(float)pageFaults/n);

    printf("\nHit Ratio = %.2f\n",(float)hits/n);

    return 0;
}