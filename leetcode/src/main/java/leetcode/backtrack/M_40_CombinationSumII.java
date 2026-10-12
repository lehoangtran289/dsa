package leetcode.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M_40_CombinationSumII {

    private List<List<Integer>> res;
    private int[] candidates;
    private int target;

    /**
     * Idea: backtracking with ordering.
     * Sort the candidates array to easily skip duplicates.
     * Skip a duplicate when it's being considered as a sibling choice at the same for-loop level
     * ---
     * TC: O(2^(N)) - worst case = explore all combinations
     * SC: O(N)
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        this.res = new ArrayList<>();
        this.candidates = candidates;
        this.target = target;
        Arrays.sort(candidates);

        backtrack(new ArrayList<>(), 0, 0);

        return res;
    }

    private void backtrack(List<Integer> numList, int sum, int start) {
        // base cases
        if (sum == target) {
            res.add(new ArrayList<>(numList));
            return;
        }

        if (sum > target) return;

        // backtracking
        for (int i = start; i < candidates.length; ++i) {
            // skip if sibling is duplicate
            if (i > start && candidates[i] == candidates[i - 1]) continue;

            numList.add(candidates[i]);
            backtrack(numList, sum + candidates[i], i + 1);
            numList.removeLast();
        }
    }
}
