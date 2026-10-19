package leetcode.array.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class M_503_NextGreaterElementII {
    static void main() {
        System.out.println(Arrays.toString(nextGreaterElements(new int[]{1, 2, 1}))); // [2,-1,2]
    }

    /**
     * Idea: since nums is circular -> traverse [0, n * 2], id = i % n
     * ---
     * TC: O(N)
     * SC: O(N)
     */
    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);

        for (int i = 0; i < nums.length * 2; ++i) {
            int id = i % n;

            while (!stack.isEmpty() && nums[stack.peek()] < nums[id]) {
                int prevId = stack.pop();
                res[prevId] = nums[id];
            }
            stack.push(id);
        }

        return res;
    }
}
