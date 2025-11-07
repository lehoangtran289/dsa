package leetcode.array.prefixSum;

public class H_2528_MaximizeTheMinimumPoweredCity {
    public static void main(String[] args) {
        System.out.println(maxPower(new int[]{1, 2, 4, 5, 0}, 1, 2)); // 5
    }

    /**
     * Line sweeping (Difference array) + Binary Search
     * ---------------------------
     * Idea:
     * - Range sum -> Difference Array
     * - Maximize the minimum -> binary search
     * ---------------------------
     * TC: O(n log(maxPower))
     * SC: O(n)
     */
    public static long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        long[] diff = new long[n];

        for (int i = 0; i < n; ++i) {
            int left = Math.max(0, i - r);
            int right = i + r + 1;

            diff[left] += stations[i];
            if (right < n) diff[right] -= stations[i];
        }

        // Binary Search for the maximum minimum power
        long lo = 0, hi = 1L << 40;
        long res = 0;

        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;

            if (isValid(diff, r, k, mid)) {
                res = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return res;
    }

    private static boolean isValid(long[] originDiff, int r, int k, long target) {
        int n = originDiff.length;
        long[] diff = originDiff.clone();
        long curSum = 0;

        for (int i = 0; i < n; ++i) {
            curSum += diff[i];
            if (curSum >= target) continue;

            long remain = target - curSum;
            if (remain > k) return false;

            curSum += remain;
            k -= remain;
            if (i + 2 * r + 1 < n) {
                diff[i + 2 * r + 1] -= remain;
            }
        }

        return true;
    }
}
