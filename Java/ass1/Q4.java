import java.util.Scanner;
import mystack.*;

public class Q4 {

    static void sortToSpecialStack(int n, Stack source, SpecialStack destination) {
        Stack buffer = new Stack(n);

        while (!source.isEmpty()) {
            int current = source.pop();

            while (!destination.isEmpty() && destination.peek() < current) {
                buffer.push(destination.pop());
            }
            destination.push(current);
            while (!buffer.isEmpty()) {
                destination.push(buffer.pop());
            }
        }
    }

    static void towerOfHanoi(int n, Stack source, Stack auxilary, Stack destination){
        if(n==1){
            int temp = source.pop();
            if(temp != -1){
                destination.push(temp);
            }
            return;
        }
        towerOfHanoi(n-1, source, destination, auxilary);
        int temp = source.pop();
        if (temp != -1){
            destination.push(temp);
        }
        towerOfHanoi(n-1, auxilary, source, destination);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of elements: ");
        int n = sc.nextInt();
        if (n <= 0){
            System.out.println("Invalid Stack Size!");
            return;
        }

        Stack temp = new Stack(n);
        SpecialStack original = new SpecialStack(n);
        Stack helper = new Stack(n);
        SpecialStack finalStack = new SpecialStack(n);

        System.out.println("Enter elements randomly: ");
        for (int i=0; i<n; i++){
            int value = sc.nextInt();
            temp.push(value);
        }

        sortToSpecialStack(n, temp, original);
        System.out.println("\nOriginal Stack: ");
        original.display();

        towerOfHanoi(n, original, helper, finalStack);

        System.out.println("\nFinal Sorted Stack: ");
        finalStack.display();
        
        sc.close();
    }
}
