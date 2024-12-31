package leetcode.array;

import java.util.Arrays;
import java.util.Stack;

public class M_503_NextGreaterElementII {
    public static void main(String[] args) {
        int[] nums = {100,1,11,1,120,111,123,1,-1,-100};
        System.out.println(Arrays.toString(nextGreaterElements(nums)));
    }

    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < 2 * n - 1; ++i) {
            int idx = i % n;
            while (!stack.empty() && stack.peek() < nums[idx]) {
                stack.pop();
            }
            res[idx] = stack.empty() ? -1 : stack.peek();
            stack.push(idx);
        }
        return res;
    }
}
