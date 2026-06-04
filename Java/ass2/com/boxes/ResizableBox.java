package com.boxes;

import com.trays.ResizableTray;

interface ResizableBox extends ResizableTray {
    void D(int depth);
}

class NewBox implements ResizableBox {
    private int width, length, depth;

    @Override
    public void W(int width) { this.width = width; }

    @Override
    public void L(int length) { this.length = length; }

    @Override
    public void D(int depth) { this.depth = depth; }

    public void houseBalls(int numBalls) {
        System.out.println("Box sized " + width + "x" + length + "x" + depth + " housing " + numBalls + " balls.");
    }
}
