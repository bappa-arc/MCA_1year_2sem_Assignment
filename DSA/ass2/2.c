#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int coeff;
    int exp;
    struct Node* next;
}Node;

void createPolynomial(Node **poly);
void displayPolynomial(Node *poly);
void insertTerm(Node **poly, int coeff, int exp);

Node* addPolynomial(Node *p1, Node *p2);
Node* subtractPolynomial(Node *p1, Node *p2);
Node* multiplyPolynomial(Node *p1, Node *p2);

int main(){
    Node *poly1 = NULL;
    Node *poly2 = NULL;
    Node *result = NULL;

    int choice;

    while (1){
        printf("\n========== MENU ==========\n");
        printf("1. Create Polynomial 1\n");
        printf("2. Create Polynomial 2\n");
        printf("3. Display Polynomial 1\n");
        printf("4. Display Polynomial 2\n");
        printf("5. Add Polynomials\n");
        printf("6. Subtract Polynomials\n");
        printf("7. Multiply Polynomials\n");
        printf("8. Exit\n");
        printf("==========================\n");

        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice){
            case 1:
                createPolynomial(&poly1);
                break;

            case 2:
                createPolynomial(&poly2);
                break;

            case 3:
                printf("Polynomial 1: ");
                displayPolynomial(poly1);
                break;

            case 4:
                printf("Polynomial 2: ");
                displayPolynomial(poly2);
                break;

            case 5:
                result = addPolynomial(poly1, poly2);

                printf("Result of Addition:\n");
                displayPolynomial(result);
                break;

            case 6:
                result = subtractPolynomial(poly1, poly2);

                printf("Result of Subtraction:\n");
                displayPolynomial(result);
                break;

            case 7:
                result = multiplyPolynomial(poly1, poly2);

                printf("Result of Multiplication:\n");
                displayPolynomial(result);
                break;

            case 8:
                printf("Program Exited Successfully.\n");
                exit(0);

            default:
                printf("Invalid Choice!\n");
        }
    }

    return 0;
}

void insertTerm(Node **poly, int coeff, int exp){
    Node *newNode, *temp, *prev;

    if (coeff == 0)
        return;

    newNode = (Node*)malloc(sizeof(Node));

    newNode->coeff = coeff;
    newNode->exp = exp;
    newNode->next = NULL;

    if (*poly == NULL){
        *poly = newNode;
        return;
    }

    temp = *poly;
    prev = NULL;

    while (temp != NULL && temp->exp > exp){
        prev = temp;
        temp = temp->next;
    }

    if (temp != NULL && temp->exp == exp){
        temp->coeff += coeff;

        free(newNode);

        return;
    }

    if (prev == NULL){
        newNode->next = *poly;
        *poly = newNode;
    }
    else{
        newNode->next = temp;
        prev->next = newNode;
    }
}

void createPolynomial(Node **poly){
    int n, i;
    int coeff, exp;

    printf("Enter number of terms: ");
    scanf("%d", &n);

    *poly = NULL;

    for (i = 0; i < n; i++){
        printf("Enter coefficient and exponent: ");
        scanf("%d%d", &coeff, &exp);

        insertTerm(poly, coeff, exp);
    }

    printf("Polynomial Created Successfully.\n");
}

void displayPolynomial(Node *poly){
    if (poly == NULL){
        printf("0\n");
        return;
    }

    while (poly != NULL){
        printf("%dx^%d", poly->coeff, poly->exp);

        if (poly->next != NULL)
            printf(" + ");

        poly = poly->next;
    }

    printf("\n");
}

Node* addPolynomial(Node *p1, Node *p2){
    Node *result = NULL;

    while (p1 != NULL && p2 != NULL){
        if (p1->exp > p2->exp){
            insertTerm(&result, p1->coeff, p1->exp);
            p1 = p1->next;
        }
        else if (p1->exp < p2->exp){
            insertTerm(&result, p2->coeff, p2->exp);
            p2 = p2->next;
        }
        else{
            insertTerm(&result, p1->coeff + p2->coeff, p1->exp);

            p1 = p1->next;
            p2 = p2->next;
        }
    }

    while (p1 != NULL){
        insertTerm(&result, p1->coeff, p1->exp);
        p1 = p1->next;
    }

    while (p2 != NULL){
        insertTerm(&result, p2->coeff, p2->exp);
        p2 = p2->next;
    }

    return result;
}

Node* subtractPolynomial(Node *p1, Node *p2){
    Node *result = NULL;

    while (p1 != NULL && p2 != NULL){
        if (p1->exp > p2->exp){
            insertTerm(&result, p1->coeff, p1->exp);
            p1 = p1->next;
        }
        else if (p1->exp < p2->exp){
            insertTerm(&result, -p2->coeff, p2->exp);
            p2 = p2->next;
        }
        else{
            insertTerm(&result, p1->coeff - p2->coeff, p1->exp);

            p1 = p1->next;
            p2 = p2->next;
        }
    }

    while (p1 != NULL){
        insertTerm(&result, p1->coeff, p1->exp);
        p1 = p1->next;
    }

    while (p2 != NULL){
        insertTerm(&result, -p2->coeff, p2->exp);
        p2 = p2->next;
    }

    return result;
}

Node* multiplyPolynomial(Node *p1, Node *p2){
    Node *result = NULL;

    Node *temp1 = p1;
    Node *temp2;

    while (temp1 != NULL){
        temp2 = p2;

        while (temp2 != NULL){
            int coeff = temp1->coeff * temp2->coeff;
            int exp = temp1->exp + temp2->exp;

            insertTerm(&result, coeff, exp);

            temp2 = temp2->next;
        }

        temp1 = temp1->next;
    }

    return result;
}