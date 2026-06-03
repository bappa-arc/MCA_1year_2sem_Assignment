#include <stdio.h>
#include <stdlib.h>

struct Node {
    int data;
    struct Node* next;
};
typedef struct Node Node;

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
void sortList(Node **head);
void searchElement(Node *head, int value);

Node* mergeSorted(Node *head1, Node *head2);
Node* concatenate(Node *head1, Node *head2);
int areEqual(Node *head1, Node *head2);

int main(){
    Node *head1 = NULL;
    Node *head2 = NULL;

    int choice, value, k;

    while(1){
        printf("\n=+=+=+=+=+=+=MENU=+=+=+=+=+=+=\n");
        printf("1. Create List 1\n");
        printf("2. Display List 1\n");
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
        printf("16. Sort List\n");
        printf("17. Search Element\n");
        printf("18. Merge Two Sorted Lists\n");
        printf("19. Concatenate Two Lists\n");
        printf("20. Check Lists Equality\n");
        printf("21. Exit\n");
        printf("\n=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice)
        {
            case 1:
                createList(&head1);
                break;

            case 2:
                display(head1);
                break;

            case 3:
                printf("Enter value: ");
                scanf("%d", &value);
                insertFront(&head1, value);
                break;

            case 4:
                printf("Enter value: ");
                scanf("%d", &value);
                insertEnd(&head1, value);
                break;

            case 5:
                printf("Enter k and value: ");
                scanf("%d%d", &k, &value);
                insertAfterK(&head1, k, value);
                break;
            case 6:
                printf("Enter key and value: ");
                scanf("%d%d", &value, &k);
                insertAfterValue(&head1, value, k);
                break;

            case 7:
                printf("Enter k and value: ");
                scanf("%d%d", &k, &value);
                insertBeforeK(&head1, k, value);
                break;

            case 8:
                printf("Enter key and value: ");
                scanf("%d%d", &value, &k);
                insertBeforeValue(&head1, value, k);
                break;

            case 9:
                deleteFront(&head1);
                break;

            case 10:
                deleteEnd(&head1);
                break;
            case 11:
                printf("Enter k: ");
                scanf("%d", &k);
                deleteAfterK(&head1, k);
                break;

            case 12:
                printf("Enter k: ");
                scanf("%d", &k);
                deleteBeforeK(&head1, k);
                break;

            case 13:
                printf("Enter k: ");
                scanf("%d", &k);
                deleteK(&head1, k);
                break;

            case 14:
                printf("Enter value: ");
                scanf("%d", &value);
                deleteValue(&head1, value);
                break;

            case 15:
                reverseList(&head1);
                break;
            case 16:
                sortList(&head1);
                break;

            case 17:
                printf("Enter value to search: ");
                scanf("%d", &value);
                searchElement(head1, value);
                break;

            case 18:
                printf("Create list2:\n");
                createList(&head2);
                sortList(&head1);
                sortList(&head2);
                head1 = mergeSorted(head1, head2);
                printf("Merged List:\n");
                display(head1);
                head2 = NULL;
                break;
            case 19:
                createList(&head2);
                head1 = concatenate(head1, head2);
                printf("Concatenated List:\n");
                display(head1);
                head2 = NULL;
                break;

            case 20:
                createList(&head2);
                if (areEqual(head1, head2))
                    printf("Lists are Equal\n");
                else
                    printf("Lists are NOT Equal\n");
                head2 = NULL;
                break;

            case 21:
                exit(0);
            default:
                printf("Invalid choice!!");
                break;
        }

    }

    return 0;
}

void createList(Node **head){
    int n, i, value;
    Node *temp, *newNode;

    printf("Enter the number of nodes: ");
    scanf("%d", &n);

    *head=NULL;
    for(i=0;i<n;i++){
        printf("Enter data: ");
        scanf("%d", &value);
        newNode = (Node*)malloc(sizeof(Node));
        newNode->data=value;
        newNode->next=NULL;
        if(*head == NULL){
            *head = newNode;
        } else {
            temp = *head;
            while (temp->next != NULL){
                temp = temp->next;
            } 
            temp->next=newNode;
        }
    }
}

void display(Node *head){ 
    if (head == NULL){
        printf("List is empty\n");
        return;
    }
    while (head != NULL)   
    {
        printf("%d -> ",head->data);
        head = head->next;
    }
    printf("NULL\n");
}

void insertFront(Node **head, int value){
    Node *newNode = (Node*)malloc(sizeof(Node));
    newNode->data=value;
    newNode->next=*head;
    *head=newNode;
}

void insertEnd(Node **head, int value){
    Node *newNode, *temp;
    newNode = (Node*)malloc(sizeof(Node));
    newNode->data=value;
    newNode->next=NULL;

    if(*head ==NULL){
        *head = newNode;
        return;
    }

    temp = *head;
    while(temp->next != NULL){
        temp = temp->next;
    }
    temp->next = newNode;
}

void insertAfterK(Node **head, int k, int value){
    Node *temp = *head;
    int i;
    
    for(i=1; i<k && temp != NULL; i++){
        temp=temp->next;
    }
    if(temp==NULL){
        printf("Invalid k\n");
        return;
    }

    Node *newNode = (Node*)malloc(sizeof(Node));
    newNode->data=value;
    newNode->next=temp->next;
    temp->next=newNode;
}

void insertAfterValue(Node **head, int key, int value){
    Node *temp = *head;
    while(temp != NULL && temp->data != key){
        temp = temp->next;
    }
    if (temp == NULL){
        printf("Value not found!!\n");
        return;
    }

    Node *newNode = (Node*)malloc(sizeof(Node));
    newNode->data = value;
    newNode->next = temp->next;
    temp->next = newNode;
}

void insertBeforeK(Node **head, int k, int value){
    if (k == 1){
        insertFront(head, value);
        return;
    }

    Node *temp = *head;
    for (int i = 1; i < k - 1 && temp != NULL; i++)
        temp = temp->next;
    if (temp == NULL){
        printf("Invalid k\n");
        return;
    }

    Node *newNode = (Node*)malloc(sizeof(Node));
    newNode->data = value;
    newNode->next = temp->next;
    temp->next = newNode;
}

void insertBeforeValue(Node **head, int key, int value){
    if (*head == NULL)
        return;

    if ((*head)->data == key){
        insertFront(head, value);
        return;
    }

    Node *temp = *head;
    while (temp->next != NULL && temp->next->data != key)
        temp = temp->next;
    if (temp->next == NULL){
        printf("Value not found\n");
        return;
    }

    Node *newNode = (Node*)malloc(sizeof(Node));
    newNode->data = value;
    newNode->next = temp->next;
    temp->next = newNode;
}


void deleteFront(Node **head){
    if (*head == NULL)
        return;

    Node *temp = *head;
    *head = (*head)->next;
    free(temp);
}

void deleteEnd(Node **head){
    if (*head == NULL)
        return;

    if ((*head)->next == NULL){
        free(*head);
        *head = NULL;
        return;
    }

    Node *temp = *head;

    while (temp->next->next != NULL)
        temp = temp->next;

    free(temp->next);
    temp->next = NULL;
}

void deleteAfterK(Node **head, int k){
    Node *temp = *head;
    for (int i = 1; i < k && temp != NULL; i++)
        temp = temp->next;

    if (temp == NULL || temp->next == NULL){
        printf("Deletion not possible\n");
        return;
    }

    Node *del = temp->next;
    temp->next = del->next;
    free(del);
}

void deleteBeforeK(Node **head, int k){
    if (k <= 2 || *head == NULL){
        printf("Deletion not possible\n");
        return;
    }

    Node *temp = *head;
    for (int i = 1; i < k - 2 && temp != NULL; i++)
        temp = temp->next;

    if (temp == NULL || temp->next == NULL){
        printf("Deletion not possible\n");
        return;
    }

    Node *del = temp->next;
    temp->next = del->next;
    free(del);
}

void deleteK(Node **head, int k){
    if (*head == NULL)
        return;

    if (k == 1){
        deleteFront(head);
        return;
    }

    Node *temp = *head;
    for (int i = 1; i < k - 1 && temp != NULL; i++)
        temp = temp->next;

    if (temp == NULL || temp->next == NULL){
        printf("Invalid k\n");
        return;
    }

    Node *del = temp->next;
    temp->next = del->next;
    free(del);
}

void deleteValue(Node **head, int value){
    if (*head == NULL)
        return;

    if ((*head)->data == value){
        deleteFront(head);
        return;
    }

    Node *temp = *head;
    while (temp->next != NULL && temp->next->data != value)
        temp = temp->next;

    if (temp->next == NULL){
        printf("Value not found\n");
        return;
    }

    Node *del = temp->next;
    temp->next = del->next;
    free(del);
}

void reverseList(Node **head){
    Node *prev = NULL;
    Node *curr = *head;
    Node *next = NULL;

    while (curr != NULL){
        next = curr->next;
        curr->next = prev;
        prev = curr;
        curr = next;
    }

    *head = prev;
}

void sortList(Node **head){
    Node *i, *j;
    int temp;
    for (i = *head; i != NULL; i = i->next){
        for (j = i->next; j != NULL; j = j->next){
            if (i->data > j->data){
                temp = i->data;
                i->data = j->data;
                j->data = temp;
            }
        }
    }
}

void searchElement(Node *head, int value){
    int pos = 1;
    while (head != NULL){
        if (head->data == value){
            printf("Found at position %d\n", pos);
            return;
        }

        head = head->next;
        pos++;
    }

    printf("Element not found\n");
}

Node* mergeSorted(Node *head1, Node *head2){
    Node dummy;
    Node *tail = &dummy;
    dummy.next = NULL;

    while (head1 && head2){
        if (head1->data < head2->data){
            tail->next = head1;
            head1 = head1->next;
        }else{
            tail->next = head2;
            head2 = head2->next;
        }

        tail = tail->next;
    }

    if (head1)
        tail->next = head1;
    else
        tail->next = head2;

    return dummy.next;
}

Node* concatenate(Node *head1, Node *head2){
    if (head1 == NULL)
        return head2;

    Node *temp = head1;
    while (temp->next != NULL)
        temp = temp->next;

    temp->next = head2;

    return head1;
}

int areEqual(Node *head1, Node *head2){
    while (head1 != NULL && head2 != NULL){
        if (head1->data != head2->data)
            return 0;

        head1 = head1->next;
        head2 = head2->next;
    }

    return (head1 == NULL && head2 == NULL);
}