#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

// Function to check prime number
int isPrime(int n)
{
    if (n < 2)
        return 0;

    for (int i = 2; i * i <= n; i++)
    {
        if (n % i == 0)
            return 0;
    }

    return 1;
}

int main()
{
    int start, end;

    // Create 10 child processes
    for (int i = 0; i < 10; i++)
    {
        pid_t pid = fork();

        // Child process
        if (pid == 0)
        {
            start = i * 1000 + 1;
            end = (i + 1) * 1000;

            printf("\nChild %d printing primes from %d to %d\n", i + 1, start, end);

            for (int num = start; num <= end; num++)
            {
                if (isPrime(num))
                {
                    printf("%d ", num);
                }
            }

            printf("\nChild %d finished\n", i + 1);

            exit(0);
        }
    }

    // Parent waits for all children
    for (int i = 0; i < 10; i++)
    {
        wait(NULL);
    }

    printf("\nAll child processes finished.\n");

    return 0;
}