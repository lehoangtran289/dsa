package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class E_496_NextGreaterElementI {
    public static void main(String[] args) {
        E_496_NextGreaterElementI solution = new E_496_NextGreaterElementI();
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        System.out.println(Arrays.toString(solution.nextGreaterElement(nums1, nums2)));
    }

    /**
     * Monotonic stack.
     * Preprocess the stack to store the next greater element of each element in nums2.
     */
    public static int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        Map<Integer, Integer> map = new HashMap<>(); // store element and its next greater element
        Stack<Integer> stack = new Stack<>();

        // preprocess stack
        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                map.put(stack.pop(), num);
            }
            stack.add(num);
        }

        for (int i = 0; i < nums1.length; ++i) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }

        return res;
    }

    // Using HashMap to store the index of each element in nums2
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; ++i) map.put(nums2[i], i);

        for (int i = 0; i < res.length; ++i) {
            res[i] = -1;

            int idx = map.get(nums1[i]);
            for (int j = idx + 1; j < nums2.length; ++j) {
                if (nums2[j] > nums1[i]) {
                    res[i] = nums2[j];
                    break;
                }
            }
        }

        return res;
    }
}
