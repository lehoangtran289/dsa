package leetcode.array.prefixSum;

import java.util.Arrays;

public class M_1477_FindTwoNonOverlappingSubarraysEachWithTargetSum {
    static void main() {
        System.out.println(minSumOfLengths2(new int[]{3, 2, 2, 4, 3}, 3)); // 2
    }

    private static final int INF = 1 << 30;

    /**
     * Idea: Prefix DP. dp[i] = min length of subarray with sum = target in arr[0..i-1]
     * ---
     * TC: O(N)
     * SC: O(N)
     */
    public static int minSumOfLengths2(int[] arr, int target) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, INF);

        int res = INF;
        int curSum = 0;
        int minLength = INF;
        int l = 0;

        for (int r = 0; r < n; ++r) {
            curSum += arr[r];
            while (curSum > target) curSum -= arr[l++];

            if (curSum == target) {
                int curLength = r - l + 1;
                if (dp[l] != INF) res = Math.min(res, curLength + dp[l]);

                minLength = Math.min(minLength, curLength);
            }
            dp[r + 1] = minLength;
        }

        return res == 1 << 30 ? -1 : res;
    }


    /**
     * Intuition approach
     * ---
     * Idea: Build prefix and postfix min length arrays, then res = min(prefix[i] + postfix[i + 1])
     * Use sliding window to find subarray with sum = target
     * ---
     * TC: O(3N)
     * SC: O(2N)
     */
    public static int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;

        int[] prefixMinLen = new int[n];
        int[] postfixMinLen = new int[n];

        // forward pass to build prefixMinLen
        int minLength = INF;
        int sum = 0;
        int l = 0;

        for (int r = 0; r < n; ++r) {
            sum += arr[r];
            while (sum > target) sum -= arr[l++];

            if (sum == target) minLength = Math.min(minLength, r - l + 1);
            prefixMinLen[r] = minLength;
        }

        // backward pass to build postfixMinLen
        minLength = INF;
        sum = 0;
        l = n - 1;

        for (int r = n - 1; r >= 0; --r) {
            sum += arr[r];
            while (sum > target) sum -= arr[l--];

            if (sum == target) minLength = Math.min(minLength, l - r + 1);
            postfixMinLen[r] = minLength;
        }

        // build result
        int res = INF;
        for (int i = 0; i < n - 1; i++) {
            if (prefixMinLen[i] != INF && postfixMinLen[i + 1] != INF) {
                res = Math.min(res, prefixMinLen[i] + postfixMinLen[i + 1]);
            }
        }

        return res == INF ? -1 : res;
    }
}
