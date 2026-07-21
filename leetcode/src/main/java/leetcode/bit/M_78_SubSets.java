package leetcode.bit;

import java.util.ArrayList;
import java.util.List;

public class M_78_SubSets {

    static void main() {
        System.out.println(subsets(new int[]{1, 2, 3}));
        // Output: [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
    }

    /**
     * bitmask, generate all possible bitmask for n bits, where n is the length of nums
     * ---
     * TC: O(2^n), where n is the length of nums
     * SC: O(n)
     */
    public static List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        int mask = (1 << n) - 1;

        for (int i = 0; i <= mask; ++i) { // O(2^n)
            List<Integer> curList = new ArrayList<>();

            for (int j = 0; j < n; ++j) { // O(n)
                if ((i & (1 << j)) > 0) {
                    curList.add(nums[j]);
                }
            }
            res.add(curList);
        }

        return res;
    }
}

