package leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class M_78_SubSets {
    static void main() {
        System.out.println(subsets(new int[]{1, 2, 3}));
        // Output: [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
    }

    /**
     * backtracking
     * ---
     * TC: O(2^n), where n is the length of nums
     * SC: O(n)
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, res, 0, new ArrayList<>());
        return res;
    }

    private static void backtrack(
            int[] nums,
            List<List<Integer>> res,
            int start,
            List<Integer> curList
    ) {
        if (start >= nums.length) {
            res.add(new ArrayList<>(curList));
            return;
        }

        // case 1: take this num
        curList.add(nums[start]);
        backtrack(nums, res, start + 1, curList);
        curList.removeLast();

        // case 2: skip this num
        backtrack(nums, res, start + 1, curList);
    }
}
