import java.util.Scanner;
import mystack.Stack;
import mystack.SpecialStack;

public class Q3 {

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

    static Stack mergeStacks(SpecialStack s1, SpecialStack s2, int totalSize) {

        Stack temp = new Stack(totalSize);

        while (!s1.isEmpty() && !s2.isEmpty()) {

            if (s1.peek() > s2.peek()) {
                temp.push(s1.pop());
            } else {
                temp.push(s2.pop());
            }
        }

        while (!s1.isEmpty()) {
            temp.push(s1.pop());
        }

        while (!s2.isEmpty()) {
            temp.push(s2.pop());
        }

        SpecialStack merged = new SpecialStack(totalSize);
        sortToSpecialStack(totalSize, temp, merged);
        return merged;
        //return temp;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of Stack 1: ");
        int n1 = sc.nextInt();

        if (n1 <= 0) {
            System.out.println("Invalid Stack Size!");
            return;
        }

        Stack temp1 = new Stack(n1);
        SpecialStack s1 = new SpecialStack(n1);

        System.out.println("Enter elements randomly for Stack 1:");
        for (int i = 0; i < n1; i++) {
            int value = sc.nextInt();
            temp1.push(value);
        }

        System.out.print("Enter size of Stack 2: ");
        int n2 = sc.nextInt();

        if (n2 <= 0) {
            System.out.println("Invalid Stack Size!");
            return;
        }

        Stack temp2 = new Stack(n2);
        SpecialStack s2 = new SpecialStack(n2);

        System.out.println("Enter elements randomly for Stack 2:");

        for (int i = 0; i < n2; i++) {
            int value = sc.nextInt();
            temp2.push(value);
        }

        System.out.println("\nSorting Stack 1...");
        sortToSpecialStack(n1, temp1, s1);
        System.out.println("Stack 1 (Sorted):");
        s1.display();

        System.out.println("\nSorting Stack 2...");
        sortToSpecialStack(n2, temp2, s2);
        System.out.println("Stack 2 (Sorted):");
        s2.display();

        Stack merged = mergeStacks(s1, s2, n1 + n2);

        System.out.println("\nMerged Stack:");
        merged.display();

        sc.close();
    }
}