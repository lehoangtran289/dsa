package leetcode.array.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class _M_1673_FindCompetitiveSubsequence {
    public static void main(String[] args) {
        int[] nums = new int[]{71, 18, 52, 29, 55, 73, 24, 42, 66, 8, 80, 2};
        int k = 3;
        System.out.println(Arrays.toString(new _M_1673_FindCompetitiveSubsequence().mostCompetitive(nums, k))); // [8,80,2]
    }

    public int[] mostCompetitive(int[] nums, int k) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums.length; ++i) {
            int cur = nums[i];
            while (!stack.isEmpty() && cur < stack.peekLast() && (k - stack.size()) <= (nums.length - i - 1)) {
                stack.pollLast();
            }

            if (stack.size() < k)
                stack.offerLast(cur);
        }

        int[] res = new int[k];
        int idx = k - 1;
        while (!stack.isEmpty()) {
            res[idx--] = stack.pollLast();
        }
        return res;
    }
}
