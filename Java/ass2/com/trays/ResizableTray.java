package com.trays;

public interface ResizableTray {
    void W(int width);
    void L(int length);
}

class Tray implements ResizableTray {
    private int width, length;

    @Override
    public void W(int width) { this.width = width; }

    @Override
    public void L(int length) { this.length = length; }

    public void carryPlates(int numPlates) {
        System.out.println("Tray sized " + width + "x" + length + " carrying " + numPlates + " plates.");
    }
}
