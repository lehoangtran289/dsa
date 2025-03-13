package leetcode.array.binarysearch;

public class M_3356_ZeroArrayTransformationII {

    /**
     * N = size array, M = size queries
     * TC: O((logN * (N + M))
     * SC: O(N)
     */
    public int minZeroArray(int[] nums, int[][] queries) {
        int l = 0, r = queries.length;
        int res = -1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (isValid(nums, queries, mid)) {
                res = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return res;
    }

    /**
     * Difference array technique: update range query in O(1)
     * For each query (l, r, val), we will decrease nums[l] by val and increase nums[r + 1] by val
     * Then use prefix sum to check get final state of nums[i]
     */
    private boolean isValid(int[] nums, int[][] queries, int k) {
        int[] diff = new int[nums.length + 1];
        for (int i = 0; i < k; ++i) {
            int l = queries[i][0];
            int r = queries[i][1];
            int val = queries[i][2];

            diff[l] -= val;
            diff[r + 1] += val;
        }

        int curSum = 0;
        for (int i = 0; i < nums.length; ++i) {
            curSum += diff[i];
            if (nums[i] + curSum > 0) return false;
        }

        return true;
    }
}
