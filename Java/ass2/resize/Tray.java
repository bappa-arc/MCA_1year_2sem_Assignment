package resize;

public class Tray implements ResizableTray {

    int width;
    int length;
    int plates;

    public Tray(int plates) {
        this.plates = plates;
    }

    public void W(int width) {
        this.width = width;
    }

    public void L(int length) {
        this.length = length;
    }

    public void display() {

        System.out.println("Tray Details");
        System.out.println("Width: " + width);
        System.out.println("Length: " + length);
        System.out.println("Number of Plates: " + plates);
    }
}