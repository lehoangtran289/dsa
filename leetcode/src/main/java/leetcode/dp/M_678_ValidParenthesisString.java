void main() {
    System.out.println(checkValidString2("()(*)")); // true
}

/**
 * Idea: dp[i][j] = is substring from index 'i' with 'j' unmatch '(' valid
 * ---
 * TC: O(n^2)
 * SC: O(n^2)
 */
boolean checkValidString1(String s) {
    int n = s.length();

    // dp[i][j] = substring from index 'i' with 'j' unmatch '(' is valid
    boolean[][] dp = new boolean[n + 1][n + 1];
    dp[n][0] = true;

    for (int i = n - 1; i >= 0; --i) {
        char c = s.charAt(i);

        for (int j = 0; j <= i; ++j) {
            if (c == '(') {
                dp[i][j] = dp[i + 1][j + 1];
            } else if (c == ')') {
                if (j > 0) dp[i][j] = dp[i + 1][j - 1];
            } else {
                dp[i][j] = dp[i + 1][j + 1]             // case (
                           || dp[i + 1][j]                 // case empty
                           || (j > 0 && dp[i + 1][j - 1]); // case )
            }
        }
    }

    return dp[0][0];
}

// -----------------------------------------------------------------------------

Boolean[][] memo;

/**
 * Idea: memo[i][j] = is substring from index 'i' with 'j' unmatched '(' valid
 * ---
 * TC: O(n^2)
 * SC: O(n^2)
 */
boolean checkValidString2(String s) {
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
