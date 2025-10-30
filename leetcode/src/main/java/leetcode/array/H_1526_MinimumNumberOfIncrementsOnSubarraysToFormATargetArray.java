package leetcode.array;

/**
 * Problem:
 * In one operation you can choose any subarray from initial (zeros) and increment each value by 1.
 * Return the minimum number of operations to form a target array from initial.
 * ----
 * Example:
 * Input: target = [3,1,5,4,2]
 * Output: 7
 */
public class H_1526_MinimumNumberOfIncrementsOnSubarraysToFormATargetArray {

    /**
     * Greedy
     * ---------------------------
     * Idea:
     * If target[i] > target[i - 1], we need to do (target[i] - target[i - 1]) operations to increase
     * If target[i] <= target[i - 1], no extra operations are needed since previous operations already cover it.
     * ---------------------------
     * TC: O(n)
     * SC: O(1)
     */
    public int minNumberOperations(int[] target) {
        int n = target.length;
        int res = target[0];

        for (int i = 1; i < n; ++i) {
            if (target[i] > target[i - 1]) {
                res += target[i] - target[i - 1];
            }
        }

        return res;
    }
}
