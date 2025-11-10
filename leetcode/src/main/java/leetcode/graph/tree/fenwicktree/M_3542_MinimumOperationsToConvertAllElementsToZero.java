package leetcode.graph.tree.fenwicktree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M_3542_MinimumOperationsToConvertAllElementsToZero {
    public static void main(String[] args) {
        M_3542_MinimumOperationsToConvertAllElementsToZero solution = new M_3542_MinimumOperationsToConvertAllElementsToZero();

        System.out.println(solution.minOperations(new int[]{1, 5, 0, 3, 5})); // 4
        System.out.println(solution.minOperations(new int[]{1, 2, 1, 2, 1, 2})); // 4
    }

    /**
     * Idea: Simulation
     * - Group elements with value 1s together, 2s together, etc.
     * - Process smallest to largest from 1, then 2, then 3...
     * - Count segments of each element group (split by zeros)
     * - The number of Operations is exactly same as the no. of Segments
     * ---------------------------
     * TC: O(n log n)
     * SC: O(n)
     */
    public int minOperations(int[] nums) {
        int n = nums.length;
        int maxVal = Arrays.stream(nums).max().getAsInt();

        // groups all position where a value v occurs
        List<List<Integer>> groups = new ArrayList<>();
        for (int i = 0; i <= maxVal; ++i) {
            groups.add(new ArrayList<>());
        }
        groupIndices(nums, groups);

        // track all zeros value in nums
        FenwickTree zeroTracker = new FenwickTree(n);
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) zeroTracker.update(i, 1);
        }

        int res = 0;
        for (int val = 1; val <= maxVal; val++) {
            if (groups.get(val).isEmpty()) continue;

            res += countOperations(groups.get(val), zeroTracker);
        }

        return res;
    }

    /**
     * groups[v] represents all positions where value v appears
     */
    private void groupIndices(int[] nums, List<List<Integer>> groups) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                groups.get(nums[i]).add(i);
            }
        }
    }

    /**
     * Count how many disjoint operations needed for a particular value
     */
    private int countOperations(List<Integer> positions, FenwickTree zeroTracker) {
        int operations = 0;
        int lastPos = -1;

        // Start new operation if:
        // 1) first occurrence, or
        // 2) there's a zero between last and current position
        for (int curr : positions) {
            if (lastPos == -1 || zeroTracker.rangeSum(lastPos, curr) > 0) {
                operations++;
            }
            lastPos = curr;
        }

        // Mark all positions as used
        for (int pos : positions) {
            zeroTracker.update(pos, 1);
        }

        return operations;
    }
}
