package leetcode.array.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class E_496_NextGreaterElementI {
    static void main() {
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        System.out.println(Arrays.toString(nextGreaterElement(nums1, nums2))); // [-1, 3, -1]
    }

    /**
     * Monotonic stack.
     * Preprocess the stack to store the next greater element of each element in nums2.
     */
    /**
     * Idea: map to store result of monotonic stack
     * ---
     * Dry run: nums1 = [4,1,2], nums2 = [1,3,4,2]
     * i2 = 0 -> stack = [0], map = {}
     * i2 = 1 -> stack = [1], map = {1:3}
     * i2 = 2 -> stack = [2], map = {1:3, 3:4}
     * i2 = 3 -> stack = [2], map = {1:3, 3:4}
     * i1 = 0 -> res = [-1, 0, 0]
     * i1 = 1 -> res = [-1, 3, 0]
     * i1 = 2 -> res = [-1, 3, -1]
     * ---
     * TC: O(nums1.len + nums2.len)
     * SC: O(nums1.len + 2 * nums2.len)
     */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nextGreaterMap = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();

        for (int j : nums2) {
            while (!stack.isEmpty() && stack.peek() < j) {
                nextGreaterMap.put(stack.pop(), j);
            }
            stack.push(j);
        }

        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; ++i) {
            res[i] = nextGreaterMap.getOrDefault(nums1[i], -1);
        }
        return res;
    }
}
