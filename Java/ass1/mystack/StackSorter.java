package mystack;

public class StackSorter {
    public static SpecialStack sortStack(Stack A) {
        // Stack A = new Stack();
        Stack B = new Stack(A.size());
        SpecialStack C = new SpecialStack(A.size());

        System.out.println("Initial Stack A: ");
        A.display();
        System.out.println("----------------------------------------");

        while (!A.isEmpty()) {
            int x = A.peek();
            int y = C.peek();

            if (x <= y) {
                x = A.pop();
                C.push(x);

                while (!B.isEmpty()) {
                    int z = B.pop();
                    C.push(z);
                }
            } else {
                y = C.pop();
                B.push(y);
            }
        }

        return C;
    }
}