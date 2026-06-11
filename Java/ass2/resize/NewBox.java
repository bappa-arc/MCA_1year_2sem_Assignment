package resize;

public class NewBox implements ResizableBox {

    int width;
    int length;
    int depth;
    int balls;

    public NewBox(int balls) {
        this.balls = balls;
    }

    public void W(int width) {
        this.width = width;
    }

    public void L(int length) {
        this.length = length;
    }

    public void D(int depth) {
        this.depth = depth;
    }

    public void display() {

        System.out.println("Box Details");
        System.out.println("Width: " + width);
        System.out.println("Length: " + length);
        System.out.println("Depth: " + depth);
        System.out.println("Number of Balls: " + balls);
    }
}