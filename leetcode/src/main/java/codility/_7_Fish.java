package codility;

import java.util.Stack;

public class _7_Fish {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{4, 3, 2, 1, 5}, new int[]{0, 1, 0, 0, 0})); // 2
        System.out.println(solution(new int[]{4, 3, 2, 1, 5}, new int[]{1, 0, 1, 0, 1})); // 3
        System.out.println(solution(new int[]{4, 3, 2, 1, 5}, new int[]{1, 1, 1, 0, 0})); // 1
        System.out.println(solution(new int[]{4, 3, 2, 1, 5}, new int[]{0, 0, 0, 1, 1})); // 5
    }

    /**
     * Idea: Use a stack to keep track of the downstream fishes.
     * Upstream fishes is alive if there is no downstream fish before it
     * -------------------
     * TC: O(N)
     * SC: O(N)
     */
    public static int solution(int[] A, int[] B) {
        // Implement your solution here
        int res = 0;
        int n = A.length;
        Stack<Integer> stack1 = new Stack<>();

        for (int i = 0; i < n; ++i) {
            if (B[i] == 0) {
                // upstream fish -> check if there is any downstream fishes
                while (!stack1.isEmpty() && stack1.peek() < A[i]) {
                    stack1.pop();
                }

                if (stack1.isEmpty()) {
                    res++;
                }
            } else if (B[i] == 1) {
                // downstream fish -> not affect previous upstream fishes -> reset stack0
                stack1.add(A[i]);
            }
        }

        return res + stack1.size();
    }
}
