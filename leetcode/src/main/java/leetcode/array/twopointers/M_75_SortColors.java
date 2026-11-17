package leetcode.array.twopointers;

import java.util.Arrays;

public class M_75_SortColors {
    static void main() {
        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums)); // 0, 0, 1, 1, 2, 2
    }

    /**
     * Idea: 1 pass only. Maintain 2 pointers at left and right ends, corresponding for 0 and 2
     * swap 0 to left end, swap 2 to right end
     * ---
     * TC: O(N)
     * SC: O(1)
     */
    public static void sortColors(int[] nums) {
        int n = nums.length;
        int p0 = 0, p2 = n - 1;
        int ptr = 0;

        while (ptr <= p2) {
            if (nums[ptr] == 0) {
                swap(nums, ptr++, p0++); // swap to left end, forward ptr
            } else if (nums[ptr] == 2) {
                swap(nums, ptr, p2--); // swap unseen num => need to process that num, do not forward ptr
            } else {
                ptr++; // skip if 1
            }
        }
    }

    private static void swap(int[] nums, int p1, int p2) {
        int temp = nums[p1];
        nums[p1] = nums[p2];
        nums[p2] = temp;
    }
}
