package leetcode.dp;

/**
 * Idea: Start with backtrack -> DP Top Down -> DP Bottom up 2d -> DP Bottom up 1d
 */
public class M_678_ValidParenthesisString {

    void main() {
        System.out.println(checkValidString2("()(*)")); // true
    }

    /**
     * Idea: dp[i][j] = it is possible to make s[i:] valid if we currently have j unmatched '('
     * ---
     * TC: O(n^2)
     * SC: O(n^2)
     */
    boolean checkValidString2(String s) {
        int n = s.length();

        // dp[i][j] = can we make substring from index 'i' valid with 'j' unmatched '('
        boolean[][] dp = new boolean[n + 1][n + 1];
        dp[n][0] = true; // empty string with 0 unmatched '(' is valid

        for (int i = n - 1; i >= 0; --i) {
            char c = s.charAt(i);

            for (int j = 0; j <= i; ++j) {
                if (c == '(') {
                    dp[i][j] = dp[i + 1][j + 1];
                } else if (c == ')') {
                    if (j > 0) dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = dp[i + 1][j + 1]                // case (
                               || dp[i + 1][j]                 // case empty
                               || (j > 0 && dp[i + 1][j - 1]); // case )
                }
            }
        }

        // check whether string s, with currently 0 unmatched '(' is valid
        return dp[0][0];
    }

    /**
     * Idea: In DP 2d, states only depend on next row
     * => dp[i] = whether we can make s[i:] valid
     */
    public boolean checkValidString3(String s) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];

        // base case: empty string is valid
        dp[0] = true;

        for (int i = n - 1; i >= 0; --i) {
            char c = s.charAt(i);
            boolean[] newDp = new boolean[n + 1];

            for (int j = 0; j <= i; ++j) {
                if (c == '(') {
                    newDp[j] = dp[j + 1];
                } else if (c == ')') {
                    if (j >= 1) newDp[j] = dp[j - 1];
                } else {
                    newDp[j] = dp[j + 1]                    // * = (
                               || (j >= 1 && dp[j - 1])     // * = )
                               || dp[j];                    // * = empty
                }
            }
            dp = newDp;
        }

        return dp[0];
    }

// -----------------------------------------------------------------------------

    Boolean[][] memo;

    /**
     * Idea: memo[i][j] = is substring from index 'i' with 'j' unmatched '(' valid
     * ---
     * TC: O(n^2)
     * SC: O(n^2)
     */
    boolean checkValidString1(String s) {
        memo = new Boolean[s.length()][s.length()];
        return backtrack(s, 0, 0);
    }

    boolean backtrack(String s, int start, int unmatch) {
        if (unmatch < 0) return false;
        if (start >= s.length()) return unmatch == 0;
        if (memo[start][unmatch] != null) return memo[start][unmatch];

        char c = s.charAt(start);

        // not * case
        if (c == '(') return memo[start][unmatch] = backtrack(s, start + 1, unmatch + 1);

        if (c == ')') return memo[start][unmatch] = backtrack(s, start + 1, unmatch - 1);

        // option 1: * = empty
        if (backtrack(s, start + 1, unmatch)) return memo[start][unmatch] = true;

        // option 2: * = (
        if (backtrack(s, start + 1, unmatch + 1)) return memo[start][unmatch] = true;

        // option 3: * = )
        return memo[start][unmatch] = backtrack(s, start + 1, unmatch - 1);
    }

}
