package leetcode.array.binarysearch;

public class M_2333_MinimumSumOfSquaredDifference {
    static void main() {
        System.out.println(minSumSquareDiff(new int[]{1, 4, 10, 12}, new int[]{5, 8, 6, 9}, 1, 1)); // 43
    }

    /**
     * Idea: Greedily reduce max diff value (since that contributes more to the sum of square).
     * -> Use binary search to find max diff value that k1 + k2 can afford.
     * ---
     * TC: O(N log(N))
     * SC: O(N)
     */
    public static long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;

        // build max diff array
        long[] diffs = new long[n];
        long totalDiffs = 0;
        for (int i = 0; i < n; ++i) {
            diffs[i] = Math.abs(nums1[i] - nums2[i]);
            totalDiffs += diffs[i];
        }
        if (totalDiffs <= k1 + k2) return 0;

        // find max diff value that k1 + k2 operations can afford
        long l = 0, r = 1 << 30;
        long maxDiff = 0;
        long totalOps = 0;

        while (l <= r) {
            long mid = (l + r) >>> 1;
            long ops = totalOpsNeeded(diffs, mid);

            if (ops <= k1 + k2) {
                maxDiff = mid;
                totalOps = ops;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        //  for each leftover ops, reduce each maxDiff by 1
        long res = 0;
        long leftOverOps = k1 + k2 - totalOps;

        for (int i = 0; i < n; ++i) {
            if (diffs[i] >= maxDiff) {
                if (leftOverOps > 0) {
                    diffs[i] = maxDiff - 1;
                    leftOverOps--;
                } else {
                    diffs[i] = maxDiff;
                }
            }
            res += diffs[i] * diffs[i];
        }
        return res;
    }

    private static long totalOpsNeeded(long[] diffs, long target) {
        long res = 0;
        for (long num : diffs) {
            if (num > target) res += num - target;
        }
        return res;
    }
}
