package leetcode.array.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E_163P_MissingRanges {

    public static void main(String[] args) {
        System.out.println(findMissingRanges(new int[]{0, 1, 3, 50, 75}, 0, 99)); // [[2, 2], [4, 49], [51, 74], [76, 99]]
    }

    /**
     * Merge intervals and find missing ranges in one pass.
     * ----
     * TC: O(n)
     * SC: O(n) for output
     */
    public static List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        List<List<Integer>> res = new ArrayList<>();
        int first = lower;

        for (int num : nums) {
            if (num < first) continue;
            if (num > first) res.add(Arrays.asList(first, num - 1));
            first = num + 1;
        }

        if (upper >= first) {
            res.add(Arrays.asList(first, upper));
        }

        return res;
    }
}
