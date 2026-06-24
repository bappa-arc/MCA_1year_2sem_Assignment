#include <stdio.h>
#include <stdlib.h>

#define MAX 100
#define PRIORITY_LEVELS 5

/* ============================
   ARRAY PRIORITY QUEUE
   ============================ */
struct Item
{
    int data;
    int priority;
};

struct Item arrPQ[MAX];
int arrSize = 0;

void arrayInsert(int data, int priority)
{
    if(arrSize == MAX)
    {
        printf("Overflow\n");
        return;
    }

    arrPQ[arrSize].data = data;
    arrPQ[arrSize].priority = priority;
    arrSize++;
}

void arrayDelete()
{
    if(arrSize == 0)
    {
        printf("Underflow\n");
        return;
    }

    int i, pos = 0;

    for(i = 1; i < arrSize; i++)
    {
        if(arrPQ[i].priority < arrPQ[pos].priority)
            pos = i;
    }

    printf("Deleted = %d\n", arrPQ[pos].data);

    for(i = pos; i < arrSize - 1; i++)
        arrPQ[i] = arrPQ[i + 1];

    arrSize--;
}

void arrayDisplay()
{
    int i;

    printf("\nData\tPriority\n");

    for(i = 0; i < arrSize; i++)
        printf("%d\t%d\n",
               arrPQ[i].data,
               arrPQ[i].priority);
}

/* ============================
   LINKED LIST PRIORITY QUEUE
   ============================ */

struct Node
{
    int data;
    int priority;
    struct Node *next;
};

struct Node *head = NULL;

void listInsert(int data, int priority)
{
    struct Node *newNode =
        (struct Node *)malloc(sizeof(struct Node));

    newNode->data = data;
    newNode->priority = priority;
    newNode->next = NULL;

    if(head == NULL ||
       priority < head->priority)
    {
        newNode->next = head;
        head = newNode;
        return;
    }

    struct Node *temp = head;

    while(temp->next != NULL &&
          temp->next->priority <= priority)
    {
        temp = temp->next;
    }

    newNode->next = temp->next;
    temp->next = newNode;
}

void listDelete()
{
    if(head == NULL)
    {
        printf("Underflow\n");
        return;
    }

    struct Node *temp = head;

    printf("Deleted = %d\n", temp->data);

    head = head->next;

    free(temp);
}

void listDisplay()
{
    struct Node *temp = head;

    printf("\nData\tPriority\n");

    while(temp)
    {
        printf("%d\t%d\n",
               temp->data,
               temp->priority);

        temp = temp->next;
    }
}

/* ============================
   2D ARRAY PRIORITY QUEUE
   ============================ */

int pq2D[MAX][2];
int size2D = 0;

void insert2D(int data, int priority)
{
    pq2D[size2D][0] = data;
    pq2D[size2D][1] = priority;
    size2D++;
}

void delete2D()
{
    if(size2D == 0)
    {
        printf("Underflow\n");
        return;
    }

    int i, pos = 0;

    for(i = 1; i < size2D; i++)
    {
        if(pq2D[i][1] < pq2D[pos][1])
            pos = i;
    }

    printf("Deleted = %d\n",
           pq2D[pos][0]);

    for(i = pos; i < size2D - 1; i++)
    {
        pq2D[i][0] = pq2D[i + 1][0];
        pq2D[i][1] = pq2D[i + 1][1];
    }

    size2D--;
}

void display2D()
{
    int i;

    printf("\nData\tPriority\n");

    for(i = 0; i < size2D; i++)
        printf("%d\t%d\n",
               pq2D[i][0],
               pq2D[i][1]);
}

/* ============================
   MULTIPLE LINKED LISTS
   ============================ */

struct PNode
{
    int data;
    struct PNode *next;
};

struct PNode *priorityList[PRIORITY_LEVELS];

void initPriorityLists()
{
    int i;

    for(i = 0; i < PRIORITY_LEVELS; i++)
        priorityList[i] = NULL;
}

void multiInsert(int data, int priority)
{
    if(priority < 1 ||
       priority > PRIORITY_LEVELS)
    {
        printf("Invalid Priority\n");
        return;
    }

    struct PNode *newNode =
        (struct PNode *)malloc(sizeof(struct PNode));

    newNode->data = data;
    newNode->next = NULL;

    priority--;

    if(priorityList[priority] == NULL)
    {
        priorityList[priority] = newNode;
    }
    else
    {
        struct PNode *temp =
            priorityList[priority];

        while(temp->next)
            temp = temp->next;

        temp->next = newNode;
    }
}

void multiDelete()
{
    int i;

    for(i = 0; i < PRIORITY_LEVELS; i++)
    {
        if(priorityList[i] != NULL)
        {
            struct PNode *temp =
                priorityList[i];

            printf("Deleted = %d\n",
                   temp->data);

            priorityList[i] =
                temp->next;

            free(temp);
            return;
        }
    }

    printf("Underflow\n");
}

void multiDisplay()
{
    int i;

    for(i = 0; i < PRIORITY_LEVELS; i++)
    {
        printf("\nPriority %d : ",
               i + 1);

        struct PNode *temp =
            priorityList[i];

        while(temp)
        {
            printf("%d ",
                   temp->data);

            temp = temp->next;
        }
    }

    printf("\n");
}

/* ============================
   MAIN MENU
   ============================ */

int main()
{
    int choice;
    int data, priority;

    initPriorityLists();

    while(1)
    {
        printf("\n===== PRIORITY QUEUE =====\n");
        printf("1. Single Array\n");
        printf("2. Linked List\n");
        printf("3. 2D Array\n");
        printf("4. Multiple Linked Lists\n");
        printf("5. Exit\n");

        printf("Enter Choice: ");
        scanf("%d", &choice);

        if(choice == 5)
            break;

        printf("Enter Data: ");
        scanf("%d", &data);

        printf("Enter Priority: ");
        scanf("%d", &priority);

        switch(choice)
        {
            case 1:
                arrayInsert(data, priority);
                arrayDisplay();
                break;

            case 2:
                listInsert(data, priority);
                listDisplay();
                break;

            case 3:
                insert2D(data, priority);
                display2D();
                break;

            case 4:
                multiInsert(data, priority);
                multiDisplay();
                break;

            default:
                printf("Invalid Choice\n");
        }
    }

    return 0;
}