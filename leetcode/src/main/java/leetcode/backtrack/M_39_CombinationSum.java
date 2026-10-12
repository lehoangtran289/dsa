package leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class M_39_CombinationSum {

    private List<List<Integer>> res;
    private int[] candidates;
    private int target;

    /**
     * Idea: backtracking with ordering.
     * Maintain a start index to avoid duplicates and allow repeated use of the same candidate.
     * ---
     * TC: O(N^(T/M)) - N = number of candidates, T = target, M = minimum value in candidates
     *          T/M = maximum depth of recursion (number of elements in a combination)
     *          N = number of choices at each level of recursion
     * SC: O(T/M) - recursion stack
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.res = new ArrayList<>();
        this.candidates = candidates;
        this.target = target;

        backtrack(new ArrayList<>(), 0, 0);

        return res;
    }

    private void backtrack(List<Integer> numList, int sum, int start) {
        if (sum == target) {
            res.add(new ArrayList<>(numList));
            return;
        }

        if (sum > target) return;

        for (int i = start; i < candidates.length; ++i) {
            numList.add(candidates[i]);
            backtrack(numList, sum + candidates[i], i);
            numList.removeLast();
        }
    }
}
