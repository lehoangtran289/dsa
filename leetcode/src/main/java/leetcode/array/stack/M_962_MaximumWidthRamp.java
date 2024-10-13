package leetcode.array.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class M_962_MaximumWidthRamp {
    public static void main(String[] args) {
        System.out.println(new M_962_MaximumWidthRamp().maxWidthRamp(new int[]{6, 0, 8, 2, 1, 5}));
        System.out.println(new M_962_MaximumWidthRamp().maxWidthRamp(new int[]{9, 1, 8, 0, 1, 9, 4, 0, 4, 1}));
    }

    public int maxWidthRamp(int[] nums) {
        // create monotonic stack to store potential start_id
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums.length; ++i) {
            if (stack.isEmpty() || nums[i] < nums[stack.peekLast()]) {
                stack.addLast(i);
            }
        }
//        System.out.println(stack); // [0, 1, 3]

        // find max ramp
        int ans = 0;
        for (int i = nums.length - 1; i > 0; --i) {
            while (!stack.isEmpty() && nums[i] >= nums[stack.peekLast()]) {
                ans = Math.max(ans, i - stack.removeLast());
            }
        }

        return ans;
    }
}
