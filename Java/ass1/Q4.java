import java.util.Scanner;

import mystack.SpecialStack;
import mystack.Stack;
import mystack.StackSorter;

public class Q4 {
    public static void main(String[] args) {
        int n = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of elements:");
        n = sc.nextInt();
        Stack A = new Stack(n);
        System.out.print("Enter elements in Stack: ");
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            A.push(num);
        }

        SpecialStack res = StackSorter.sortStack(A);

        System.out.println("----------------------------------------");
        System.out.println("Final Sorted SpecialStack C: ");
        res.display();
        sc.close();
    }
}