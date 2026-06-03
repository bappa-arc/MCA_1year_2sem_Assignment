#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>

int isPalindrome(char str[])
{
    int i, len;

    len = strlen(str);

    for (i = 0; i < len / 2; i++)
    {
        if (str[i] != str[len - i - 1])
        {
            return 0;
        }
    }

    return 1;
}

int main()
{
    int pipe1[2], pipe2[2];
    pid_t pid;

    char str[100];
    char result[10];

    // Create pipes
    pipe(pipe1);
    pipe(pipe2);

    pid = fork();

    // Child Process
    if (pid == 0)
    {
        while (1)
        {
            // Read string from parent
            read(pipe1[0], str, sizeof(str));
            // Check quit condition
            if (strcmp(str, "quit") == 0)
            {
                break;
            }
            // Check palindrome
            if (isPalindrome(str))
            {
                strcpy(result, "YES");
            }
            else
            {
                strcpy(result, "NO");
            }
            // Send result to parent
            write(pipe2[1], result, sizeof(result));
        }
    }
    // Parent Process
    else
    {
        while (1)
        {
            printf("Enter string: ");
            scanf("%s", str);
            // Send string to child
            write(pipe1[1], str, sizeof(str));
            // Quit condition
            if (strcmp(str, "quit") == 0)
            {
                break;
            }
            // Read result from child
            read(pipe2[0], result, sizeof(result));
            printf("Palindrome? %s\n", result);
        }
        wait(NULL);
    }
    return 0;
}