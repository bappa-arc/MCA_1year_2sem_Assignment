#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int data;
    struct Node *prev;
    struct Node *next;
}Node;


void createList(Node **head);
void display(Node *head);

void insertFront(Node **head, int value);
void insertEnd(Node **head, int value);
void insertAfterK(Node **head, int k, int value);
void insertAfterValue(Node **head, int key, int value);
void insertBeforeK(Node **head, int k, int value);
void insertBeforeValue(Node **head, int key, int value);

void deleteFront(Node **head);
void deleteEnd(Node **head);
void deleteAfterK(Node **head, int k);
void deleteBeforeK(Node **head, int k);
void deleteK(Node **head, int k);
void deleteValue(Node **head, int value);

void reverseList(Node **head);

int main(){
    Node *head = NULL;
    int choice, value, k;

    while (1){
        printf("\n========== MENU ==========\n");
        printf("1. Create List\n");
        printf("2. Display List\n");
        printf("3. Insert at Front\n");
        printf("4. Insert at End\n");
        printf("5. Insert after kth node\n");
        printf("6. Insert after given value\n");
        printf("7. Insert before kth node\n");
        printf("8. Insert before given value\n");
        printf("9. Delete first node\n");
        printf("10. Delete last node\n");
        printf("11. Delete after kth node\n");
        printf("12. Delete before kth node\n");
        printf("13. Delete kth node\n");
        printf("14. Delete node containing value\n");
        printf("15. Reverse List\n");
        printf("16. Exit\n");
        printf("==========================\n");

        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice){
            case 1:
                createList(&head);
                break;

            case 2:
                display(head);
                break;

            case 3:
                printf("Enter value: ");
                scanf("%d", &value);
                insertFront(&head, value);
                break;

            case 4:
                printf("Enter value: ");
                scanf("%d", &value);
                insertEnd(&head, value);
                break;

            case 5:
                printf("Enter k and value: ");
                scanf("%d%d", &k, &value);
                insertAfterK(&head, k, value);
                break;

            case 6:
                printf("Enter key and value: ");
                scanf("%d%d", &k, &value);
                insertAfterValue(&head, k, value);
                break;

            case 7:
                printf("Enter k and value: ");
                scanf("%d%d", &k, &value);
                insertBeforeK(&head, k, value);
                break;

            case 8:
                printf("Enter key and value: ");
                scanf("%d%d", &k, &value);
                insertBeforeValue(&head, k, value);
                break;

            case 9:
                deleteFront(&head);
                break;

            case 10:
                deleteEnd(&head);
                break;

            case 11:
                printf("Enter k: ");
                scanf("%d", &k);
                deleteAfterK(&head, k);
                break;

            case 12:
                printf("Enter k: ");
                scanf("%d", &k);
                deleteBeforeK(&head, k);
                break;

            case 13:
                printf("Enter k: ");
                scanf("%d", &k);
                deleteK(&head, k);
                break;

            case 14:
                printf("Enter value: ");
                scanf("%d", &value);
                deleteValue(&head, value);
                break;

            case 15:
                reverseList(&head);
                printf("List Reversed Successfully.\n");
                break;

            case 16:
                printf("Program Exited Successfully.\n");
                exit(0);

            default:
                printf("Invalid Choice!\n");
        }
    }

    return 0;
}

void createList(Node **head){
    int n, i, value;
    Node *newNode, *temp;

    printf("Enter number of nodes: ");
    scanf("%d", &n);

    *head = NULL;

    for (i = 0; i < n; i++){
        printf("Enter data: ");
        scanf("%d", &value);

        newNode = (Node*)malloc(sizeof(Node));

        newNode->data = value;
        newNode->prev = NULL;
        newNode->next = NULL;

        if (*head == NULL){
            *head = newNode;
        }
        else{
            temp = *head;

            while (temp->next != NULL)
                temp = temp->next;

            temp->next = newNode;
            newNode->prev = temp;
        }
    }
}

void display(Node *head){
    if (head == NULL){
        printf("List is Empty\n");
        return;
    }

    printf("List:\n");

    while (head != NULL){
        printf("%d <-> ", head->data);
        head = head->next;
    }

    printf("NULL\n");
}

void insertFront(Node **head, int value){
    Node *newNode;

    newNode = (Node*)malloc(sizeof(Node));

    newNode->data = value;
    newNode->prev = NULL;
    newNode->next = *head;

    if (*head != NULL)
        (*head)->prev = newNode;

    *head = newNode;
}

void insertEnd(Node **head, int value){
    Node *newNode, *temp;

    newNode = (Node*)malloc(sizeof(Node));

    newNode->data = value;
    newNode->next = NULL;

    if (*head == NULL){
        newNode->prev = NULL;
        *head = newNode;
        return;
    }

    temp = *head;

    while (temp->next != NULL)
        temp = temp->next;

    temp->next = newNode;
    newNode->prev = temp;
}

void insertAfterK(Node **head, int k, int value){
    Node *temp = *head;
    int i;

    for (i = 1; i < k && temp != NULL; i++)
        temp = temp->next;

    if (temp == NULL){
        printf("Invalid k\n");
        return;
    }

    Node *newNode = (Node*)malloc(sizeof(Node));

    newNode->data = value;
    newNode->next = temp->next;
    newNode->prev = temp;

    if (temp->next != NULL)
        temp->next->prev = newNode;

    temp->next = newNode;
}

void insertAfterValue(Node **head, int key, int value){
    Node *temp = *head;

    while (temp != NULL && temp->data != key)
        temp = temp->next;

    if (temp == NULL){
        printf("Value not found\n");
        return;
    }

    Node *newNode = (Node*)malloc(sizeof(Node));

    newNode->data = value;
    newNode->next = temp->next;
    newNode->prev = temp;

    if (temp->next != NULL)
        temp->next->prev = newNode;

    temp->next = newNode;
}

void insertBeforeK(Node **head, int k, int value){
    Node *temp = *head;
    int i;

    if (k == 1){
        insertFront(head, value);
        return;
    }

    for (i = 1; i < k && temp != NULL; i++)
        temp = temp->next;

    if (temp == NULL){
        printf("Invalid k\n");
        return;
    }

    Node *newNode = (Node*)malloc(sizeof(Node));

    newNode->data = value;
    newNode->prev = temp->prev;
    newNode->next = temp;

    temp->prev->next = newNode;
    temp->prev = newNode;
}

void insertBeforeValue(Node **head, int key, int value){
    Node *temp = *head;

    while (temp != NULL && temp->data != key)
        temp = temp->next;

    if (temp == NULL){
        printf("Value not found\n");
        return;
    }

    if (temp == *head){
        insertFront(head, value);
        return;
    }

    Node *newNode = (Node*)malloc(sizeof(Node));

    newNode->data = value;
    newNode->prev = temp->prev;
    newNode->next = temp;

    temp->prev->next = newNode;
    temp->prev = newNode;
}

void deleteFront(Node **head){
    if (*head == NULL){
        printf("List Empty\n");
        return;
    }

    Node *temp = *head;

    *head = (*head)->next;

    if (*head != NULL)
        (*head)->prev = NULL;

    free(temp);
}

void deleteEnd(Node **head){
    if (*head == NULL){
        printf("List Empty\n");
        return;
    }

    Node *temp = *head;

    if (temp->next == NULL){
        free(temp);
        *head = NULL;
        return;
    }

    while (temp->next != NULL)
        temp = temp->next;

    temp->prev->next = NULL;

    free(temp);
}

void deleteAfterK(Node **head, int k){
    Node *temp = *head;
    int i;

    for (i = 1; i < k && temp != NULL; i++)
        temp = temp->next;

    if (temp == NULL || temp->next == NULL){
        printf("Deletion not possible\n");
        return;
    }

    Node *del = temp->next;

    temp->next = del->next;

    if (del->next != NULL)
        del->next->prev = temp;

    free(del);
}

void deleteBeforeK(Node **head, int k){
    Node *temp = *head;
    int i;

    if (k <= 1 || *head == NULL){
        printf("Deletion not possible\n");
        return;
    }

    for (i = 1; i < k && temp != NULL; i++)
        temp = temp->next;

    if (temp == NULL || temp->prev == NULL){
        printf("Deletion not possible\n");
        return;
    }

    Node *del = temp->prev;

    if (del->prev != NULL)
        del->prev->next = temp;
    else
        *head = temp;

    temp->prev = del->prev;

    free(del);
}

void deleteK(Node **head, int k){
    Node *temp = *head;
    int i;

    if (*head == NULL)
        return;

    for (i = 1; i < k && temp != NULL; i++)
        temp = temp->next;

    if (temp == NULL){
        printf("Invalid k\n");
        return;
    }

    if (temp->prev != NULL)
        temp->prev->next = temp->next;
    else
        *head = temp->next;

    if (temp->next != NULL)
        temp->next->prev = temp->prev;

    free(temp);
}

void deleteValue(Node **head, int value){
    Node *temp = *head;

    while (temp != NULL && temp->data != value)
        temp = temp->next;

    if (temp == NULL){
        printf("Value not found\n");
        return;
    }

    if (temp->prev != NULL)
        temp->prev->next = temp->next;
    else
        *head = temp->next;

    if (temp->next != NULL)
        temp->next->prev = temp->prev;

    free(temp);
}

void reverseList(Node **head)
{
    Node *temp = NULL;
    Node *current = *head;

    while (current != NULL){
        temp = current->prev;
        current->prev = current->next;
        current->next = temp;

        current = current->prev;
    }

    if (temp != NULL)
        *head = temp->prev;
}