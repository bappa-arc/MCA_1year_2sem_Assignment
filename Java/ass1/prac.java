import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int max=heights[0];
        int count = 0;
        int val = 0;
        for (int h : heights){

            if (h>max){
                max=h;
            }

            if (!stack.isEmpty() && stack.peek() == h){
                count++;
                val = h;
            }

            stack.push(h);
        }

        if (max == val){
            return val*count;
        }else if (count > 0){
            return val*count;
        }else {
            return max;
        }
    }
}


public class prac {
    public static void main(String[] args) {

        Solution obj = new Solution();
        int[] heights = new int[]{7,1,7,2,2,4};
        System.out.println(obj.largestRectangleArea(heights));
    }
}


