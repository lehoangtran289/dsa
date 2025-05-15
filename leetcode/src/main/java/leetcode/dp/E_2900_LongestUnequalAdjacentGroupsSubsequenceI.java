package leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E_2900_LongestUnequalAdjacentGroupsSubsequenceI {

    public static void main(String[] args) {
        String[] words = {"a", "b", "c", "d", "e"};
        int[] groups = {1, 2, 1, 2, 1};

        System.out.println(getLongestSubsequence(words, groups)); // Output: [e, d, b]
    }

    /**
     * 11 000 1 0 111 00 -> Greedily choose 1 from each group
      */
    public static List<String> getLongestSubsequence(String[] words, int[] groups) {
        // get LAS indexes
        List<Integer> resultIndexes = getLongestSubseqIndexes(groups);

        // build result
        List<String> res = new ArrayList<>();
        for (int i = resultIndexes.size() - 1; i >= 0; --i) {
            res.add(words[resultIndexes.get(i)]);
        }
        return res;
    }

    /**
     * DP
     * state: dp[i] = LAS length at i-th
     * dp[i] = max(dp[j] + 1) where j < i and arr[j] != arr[i]
     */
    private static List<Integer> getLongestSubseqIndexes(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int maxLength = 1, endIndex = 0;

        Arrays.fill(dp, 1);

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (arr[i] != arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);

                    if (dp[i] > maxLength) {
                        maxLength = dp[i];
                        endIndex = i;
                    }
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        res.add(endIndex);

        for (int i = endIndex - 1; i >= 0; --i) {
            int prev = arr[res.get(res.size() - 1)];

            if (prev != arr[i]) {
                res.add(i);
            }
        }

        return res;
    }
}
