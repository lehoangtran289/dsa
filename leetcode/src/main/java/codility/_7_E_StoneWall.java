package codility;

import java.util.Stack;

public class _7_E_StoneWall {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{8, 8, 5, 7, 9, 8, 7, 4, 8})); // 7
    }

    public static int solution(int[] H) {
        // Implement your solution here
        int n = H.length;
        Stack<Integer> stack = new Stack<>();
        int res = 0;

        for (int height : H) {
            while (!stack.isEmpty() && stack.peek() > height) {
                stack.pop();
            }

            if (stack.isEmpty() || stack.peek() < height) {
                stack.add(height);
                res++;
            }
        }

        return res;
    }
}
