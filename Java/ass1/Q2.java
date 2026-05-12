import java.util.Scanner;

class Stack{
    int [] arr;
    int top;
    int size;

    Stack(int size){
        this.size = size;
        arr = new int[size];
        top = -1;
    }

    void push(int value){
        if(top==size-1){
            System.out.println("Stack overflow!");
        }else{
            top++;
            arr[top] = value;
            System.out.println(value+" pushed in the stack.");
        }
    }

    void pop(){
        if(top==-1){
            System.out.println("Stack underflow!");
        }else{
            System.out.println(arr[top]+" popped from the stack.");
            top--;
        }
    }

    void peek(){
        if(top==-1){
            System.out.println("Stack is empty!");
        }else{
            System.out.println("Top element: "+arr[top]);
        }
    }

    void display(){
        if(top==-1){
            System.out.println("Stack is empty!");
        }else{
            System.out.println("Stack elements: ");
            for (int i=0; i<arr.length;i++){
                System.out.println(arr[i]);
            }
        }
    }

    void display(int depth){
        if(top==-1){
            System.out.println("Stack is empty!");
        }else if (depth < 0 || depth > top) {
            System.out.println("Invalid Depth!");
        } 
        else {
            System.out.println("Element at depth " + depth + " is: "+ arr[top - depth]);
        }
    }

}


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
                    s.push(value);
                    break;
                case 2:
                    s.pop();
                    break;

                case 3:
                    s.peek();
                    break;

                case 4:
                    s.display();
                    break;

                case 5:
                    System.out.print("Enter Depth: ");
                    int depth = sc.nextInt();
                    s.display(depth);
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
