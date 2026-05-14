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
/*void insertAfterValue(Node **head, int key, int value);
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
int areEqual(Node *head1, Node *head2);*/

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
        printf("18. Create List 2\n");
        printf("19. Merge Two Sorted Lists\n");
        printf("20. Concatenate Two Lists\n");
        printf("21. Check Lists Equality\n");
        printf("22. Exit\n");
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
            /*case 6:
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
                createList(&head2);
                break;

            case 19:
                head1 = mergeSorted(head1, head2);
                printf("Merged List:\n");
                display(head1);
                break;
            case 20:
                head1 = concatenate(head1, head2);
                printf("Concatenated List:\n");
                display(head1);
                break;

            case 21:
                if (areEqual(head1, head2))
                    printf("Lists are Equal\n");
                else
                    printf("Lists are NOT Equal\n");
                break;*/

            case 22:
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