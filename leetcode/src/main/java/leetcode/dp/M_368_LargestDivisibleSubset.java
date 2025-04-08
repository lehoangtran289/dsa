package leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M_368_LargestDivisibleSubset {
    private int[] nums;
    private Map<Integer, List<Integer>> memo;

    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        this.nums = nums;
        this.memo = new HashMap<>();

        List<Integer> maxSubset = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            List<Integer> subset = dp(i);
            if (subset.size() > maxSubset.size()) maxSubset = subset;
        }

        return maxSubset;
    }

    private List<Integer> dp(int i) {
        // base cases
        if (memo.containsKey(i)) return memo.get(i);

        List<Integer> maxSubset = new ArrayList<>();

        for (int j = 0; j < i; ++j) {
            if (nums[i] % nums[j] == 0) {
                List<Integer> subset = dp(j);
                if (subset.size() > maxSubset.size()) maxSubset = subset;
            }
        }

        List<Integer> res = new ArrayList<>(maxSubset);
        res.add(nums[i]);
        memo.put(i, res);
        return res;
    }
}
