package mystack;

public class SpecialStack extends Stack {

    public SpecialStack(int size) {
        super(size);
    }

    @Override
    public boolean push(int value) {
        if (isFull()) {
            System.out.println("Stack Overflow!");
            return false;
        }
        if (isEmpty() || value <= peek()) {
            arr[++top] = value;
            return true;
        } else {
            return false;
        }
    }
}
