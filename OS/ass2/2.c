#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main()
{
    pid_t pid;
    int status;

    // Create child process
    pid = fork();

    // Error in fork
    if (pid < 0)
    {
        printf("Fork failed\n");
        exit(1);
    }
    // Child process
    else if (pid == 0)
    {
        printf("Child Process\n");
        printf("Child PID: %d\n", getpid());
        printf("Parent PID: %d\n", getppid());

        // Child exits with status 5
        exit(5);
    }
    // Parent process
    else
    {
        // Wait for child to finish
        wait(&status);

        printf("\nParent Process\n");
        printf("Parent PID: %d\n", getpid());
        printf("Parent's Parent PID: %d\n", getppid());

        // Print child exit status
        printf("Child Exit Status: %d\n", WEXITSTATUS(status));
    }

    return 0;
}