package codility;

import java.util.Stack;

public class _7_Fish {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{4, 3, 2, 1, 5}, new int[]{0, 1, 0, 0, 0})); // Expected output: 2
    }

    /**
     * TC: O(N)
     * SC: O(N)
     */
    public static int solution(int[] A, int[] B) {
        // Implement your solution here
        int res = 0;
        int n = A.length;
        Stack<Integer> stack0 = new Stack<>();
        Stack<Integer> stack1 = new Stack<>();

        for (int i = 0; i < n; ++i) {
            if (B[i] == 0) {
                // upstream fish -> check if there is any downstream fishes
                while (!stack1.isEmpty() && stack1.peek() < A[i]) {
                    stack1.pop();
                }

                if (stack1.isEmpty()) {
                    stack0.add(A[i]);
                }
            } else if (B[i] == 1) {
                // downstream fish -> not affect previous upstream fishes -> reset stack0
                res += stack0.size();
                stack0 = new Stack<>();

                stack1.add(A[i]);
            }
        }

        return res + stack0.size() + stack1.size();
    }
}
