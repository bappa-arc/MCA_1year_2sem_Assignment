import java.util.Scanner;

import mystack.Stack;

public class Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Stack Size: ");
        int n = sc.nextInt();

        Stack s = new Stack(n);

        int choice;

        do {
            System.out.println("\n--- STACK MENU ---");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Display Full Stack");
            System.out.println("5. Display Element by Depth");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Value: ");
                    int value = sc.nextInt();
                    if (s.push(value)) {
                        System.out.println(value + " pushed in the stack.");
                    } else {
                        System.out.println("Stack overflow!");
                    }
                    break;
                case 2:
                    int popped = s.pop();
                    if (popped == -1) {
                        System.out.println("Stack underflow!");
                    } else {
                        System.out.println(popped + " popped from the stack.");
                    }
                    break;

                case 3:
                    int topElement = s.peek();
                    if (topElement == -1) {
                        System.out.println("Stack is empty!");
                    } else {
                        System.out.println("Top element: " + topElement);
                    }
                    break;

                case 4:
                    s.display();
                    break;

                case 5:
                    System.out.print("Enter Depth: ");
                    int depth = sc.nextInt();
                    if (s.isEmpty()) {
                        System.out.println("Stack is empty!");
                    } else {
                        int element = s.display(depth);
                        if (element == -1) {
                            System.out.println("Invalid Depth!");
                        } else {
                            System.out.println("Element at depth " + depth + " is: " + element);
                        }
                    }
                    break;

                case 6:
                    System.out.println("Exiting Program...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}
