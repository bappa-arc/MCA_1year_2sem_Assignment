package mystack;
public class StackSorter {
    public static SpecialStack sortStack(Stack A) {
        // Stack A = new Stack();
        Stack B = new Stack(A.size());
        SpecialStack C = new SpecialStack(A.size());

        System.out.println("Initial Stack :");
        A.display();
        System.out.println("-------------------------------------");

        while (!A.isEmpty()){
            int x = A.peek();
            while (!C.isEmpty() && x > C.peek()){
                B.push(C.pop());
            }
            C.push(A.pop());
            while (!B.isEmpty()){
                C.push(B.pop());
            }
        }
        return C;
    }
}