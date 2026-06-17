package resize;

public class NewBox implements ResizableBox {

    int width;
    int length;
    int depth;
    int ballWidth;
    int ballLength;
    int ballDepth;

    public NewBox(int width, int length, int depth) {
        this.width = width;
        this.length = length;
        this.depth = depth;
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

    public void setBallSize(int ballWidth, int ballLength, int ballDepth) {
        this.ballWidth = ballWidth;
        this.ballLength = ballLength;
        this.ballDepth = ballDepth;
    }

    public int calculateBallCount() {
        if (width <= 0 || length <= 0 || depth <= 0 || ballWidth <= 0 || ballLength <= 0 || ballDepth <= 0) {
            return 0;
        }

        int[][] perms = {
            { ballWidth, ballLength, ballDepth },
            { ballWidth, ballDepth, ballLength },
            { ballLength, ballWidth, ballDepth },
            { ballLength, ballDepth, ballWidth },
            { ballDepth, ballWidth, ballLength },
            { ballDepth, ballLength, ballWidth }
        };

        int maxCount = 0;
        for (int[] perm : perms) {
            int fitWidth = width / perm[0];
            int fitLength = length / perm[1];
            int fitDepth = depth / perm[2];
            maxCount = Math.max(maxCount, fitWidth * fitLength * fitDepth);
        }

        return maxCount;
    }

    public void display() {

        System.out.println("Box Details");
        System.out.println("Box Width: " + width);
        System.out.println("Box Length: " + length);
        System.out.println("Box Depth: " + depth);
        System.out.println("Ball Width: " + ballWidth);
        System.out.println("Ball Length: " + ballLength);
        System.out.println("Ball Depth: " + ballDepth);
        System.out.println("Number of Balls that can fit: " + calculateBallCount());
    }
}