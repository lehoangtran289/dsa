package leetcode.dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class M_873_LengthOfLongestFibonacciSubsequence {
    public static void main(String[] args) {
        System.out.println(lenLongestFibSubseq(new int[]{1, 3, 5})); // 0
        System.out.println(lenLongestFibSubseq(new int[]{1, 2, 3, 4, 5, 6, 7, 8})); // 5
        System.out.println(lenLongestFibSubseq(new int[]{1, 3, 7, 11, 12, 14, 18})); // 3
    }

    /**
     * DP Bottom up approach
     * -------------------------
     * state: dp[i][j] means longest fib sequences with 2 last elements i-th and j-th
     * dp[i][j] = dp[j][k] + 1 if arr[i] - arr[j] = arr[k] && arr[k] < arr[j]
     *          = 2
     * -------------------------
     * TC: O(n^2)
     * SC: O(n^2)
     */
    public static int lenLongestFibSubseq2(int[] arr) {
        int res = 0;
        int n = arr.length;
        Map<Integer, Integer> indexMap = new HashMap<>();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; ++i) {
            indexMap.put(arr[i], i);
        }

        // process dp, check every pair of i-th and j-th
        for (int i = 0; i < n; ++i) {
            for (int j = i - 1; j >= 0; --j) {
                int diff = arr[i] - arr[j];

                if (indexMap.containsKey(diff)) {
                    int k = indexMap.get(diff);

                    // check if k-th is before j-th -> valid fib sequence
                    if (diff < arr[j]) {
                        dp[i][j] = dp[j][k] + 1;
                    } else {
                        dp[i][j] = 2;
                    }
                } else {
                    dp[i][j] = 2;
                }

                res = Math.max(res, dp[i][j]);
            }
        }

        return res > 2 ? res : 0;
    }

    /**
     * Bruteforce approach. Try every start pair in the array
     * TC: O(n^2 * log(max(arr)))
     */
    public static int lenLongestFibSubseq(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) set.add(num);

        int maxLen = 0;
        for (int i = 0; i < arr.length - 1; ++i) {
            for (int j = i + 1; j < arr.length; ++j) {
                int curLen = 2;
                int num1 = arr[i];
                int num2 = arr[j];

                while (set.contains(num1 + num2)) {
                    curLen++;
                    int temp = num1;
                    num1 = num2;
                    num2 = temp + num2;
                }

                // fibonacci subarray start with 3 elements
                if (curLen > 2) {
                    maxLen = Math.max(maxLen, curLen);
                }
            }
        }

        return maxLen;
    }
}
