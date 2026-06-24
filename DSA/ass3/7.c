#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <math.h>

#define MAX 100

int stack[MAX];
int top = -1;

/* Stack Operations */
void push(int x)
{
    stack[++top] = x;
}

int pop()
{
    if(top == -1)
    {
        printf("Stack Underflow\n");
        return -1;
    }

    return stack[top--];
}

int main()
{
    char postfix[MAX];
    int i;
    int op1, op2, result;

    printf("Enter Postfix Expression: ");
    scanf("%s", postfix);

    for(i = 0; postfix[i] != '\0'; i++)
    {
        char ch = postfix[i];

        /* Operand */
        if(isdigit(ch))
        {
            push(ch - '0');
        }

        /* Operator */
        else
        {
            op2 = pop();
            op1 = pop();

            switch(ch)
            {
                case '+':
                    result = op1 + op2;
                    break;

                case '-':
                    result = op1 - op2;
                    break;

                case '*':
                    result = op1 * op2;
                    break;

                case '/':
                    result = op1 / op2;
                    break;

                case '^':
                    result = pow(op1, op2);
                    break;

                default:
                    printf("Invalid Operator\n");
                    return 1;
            }

            push(result);
        }
    }

    printf("Result = %d\n", pop());

    return 0;
}