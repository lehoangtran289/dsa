package leetcode.dp;

import java.util.Arrays;

public class M_91_DecodeWays {
    private String s;
    private int[] memo;

    /**
     * Top down DP
     * Idea: 2 option: 1 digit OR 2 digits
     * State: current index of string
     * Relation: dp(i) = dp(i + 1) || dp(i) = dp(i + 1) + dp(i + 2)
     */
    public int numDecodings(String s) {
        this.s = s;
        this.memo = new int[s.length()];
        Arrays.fill(memo, -1);

        return dp(0);
    }

    private int dp(int index) {
        // base cases
        if (index < s.length()) {
            if (memo[index] != -1) return memo[index]; // check memo
            if (s.charAt(index) == '0') return 0; // start with 0 -> cannot decode -> 0
        }
        if (index == s.length() || index == s.length() - 1) return 1; // if reach end of s -> can decode -> 1

        // process
        if (Integer.parseInt(s.substring(index, index + 2)) <= 26) {
            return memo[index] = dp(index + 1) + dp(index + 2);
        } else {
            return memo[index] = dp(index + 1);
        }
    }
}
