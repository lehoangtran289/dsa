package leetcode.array.twopointers;

import java.util.Arrays;

public class M_2592_MaximizeGreatnessOfAnArray {
    public static void main(String[] args) {
        System.out.println(
                maximizeGreatness(new int[]{1, 3, 5, 2, 1, 3, 1}) // 4
        );
    }

    /**
     * Sorting + 2 pointers.
     * l = cur index ; r = next bigger num
     * -------------------
     * TC: O(N)
     * SC: O(1)
     */
    public static int maximizeGreatness(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        int res = 0;
        int l = 0, r = 0;

        while (r < n) {
            if (nums[r] > nums[l]) { // found a next bigger number
                res++;
                l++;
            }
            r++;
        }

        return res;
    }
}
