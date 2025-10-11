package leetcode.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class M_740_DeleteAndEarn {

    /**
     * DP Bottom Up
     * -----------------------
     * TC: O(N log N) - N is the length of nums
     * SC: O(N)
     */
    public int deleteAndEarn(int[] nums) {
        // get each num frequency into a Map
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) {
            freq.put(n, freq.getOrDefault(n, 0) + n);
        }

        // build new array from unique num and sort it
        int[] arr = new int[freq.size()];
        int curId = 0;
        for (int n : freq.keySet()) arr[curId++] = n;
        Arrays.sort(arr);

        // init DP
        int[] dp = new int[arr.length];
        if (arr.length == 1) return freq.get(arr[0]);

        // base cases. for i, state = 2 options: take or skip (not take)
        dp[0] = freq.get(arr[0]);
        if (arr[1] - 1 == arr[0]) {
            dp[1] = Math.max(
                    freq.get(arr[1]),
                    dp[0]
            );
        } else {
            dp[1] = dp[0] + freq.get(arr[1]);
        }

        // fill DP
        for (int i = 2; i < arr.length; ++i) {
            if (arr[i] - 1 == arr[i - 1]) {
                dp[i] = Math.max(
                        freq.get(arr[i]) + dp[i - 2],
                        dp[i - 1]
                );
            } else {
                dp[i] = dp[i - 1] + freq.get(arr[i]);
            }
        }

        return dp[arr.length - 1];
    }
}
