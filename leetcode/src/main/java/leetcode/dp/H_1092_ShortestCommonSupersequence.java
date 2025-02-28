package leetcode.dp;

/**
 * Idea: Find LCS and add remaining
 */
public class H_1092_ShortestCommonSupersequence {
    public static void main(String[] args) {
        System.out.println(new H_1092_ShortestCommonSupersequence().shortestCommonSupersequence3("abac", "cab"));
    }

    /**
     * LCS with Memo approach. </br>
     * ===> Memory Limit Exceed
     */
    public String shortestCommonSupersequence1(String str1, String str2) {
        // caching purpose
        String[][] dp = new String[str1.length() + 1][str2.length() + 1];

        return backtrack(str1, str2, dp, 0, 0);
    }

    private String backtrack(
            String str1,
            String str2,
            String[][] dp,
            int p1,
            int p2
    ) {
        // base cases
        if (dp[p1][p2] != null) {
            return dp[p1][p2];
        }
        if (p1 == str1.length()) {
            return str2.substring(p2);
        }
        if (p2 == str2.length()) {
            return str1.substring(p1);
        }

        if (str1.charAt(p1) == str2.charAt(p2)) {
            return str1.charAt(p1) + backtrack(str1, str2, dp, p1 + 1, p2 + 1);
        }

        String res1 = str1.charAt(p1) + backtrack(str1, str2, dp, p1 + 1, p2);
        String res2 = str2.charAt(p2) + backtrack(str1, str2, dp, p1, p2 + 1);

        if (res1.length() < res2.length()) {
            dp[p1][p2] = res1;
            return res1;
        }

        dp[p1][p2] = res2;
        return res2;
    }

    // ----------------------------------------------------------------------------------------------

    /**
     * Bottom up DP approach </br>
     * ===> Memory limit exceed
     */
    public String shortestCommonSupersequence2(String str1, String str2) {
        // init table
        String[][] dp = new String[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i < dp.length; ++i) {
            for (int j = 0; j < dp[0].length; ++j) {
                dp[i][j] = "";
            }
        }

        // process last row
        for (int col = dp[0].length - 2; col >= 0; --col) {
            dp[dp.length - 1][col] = str2.charAt(col) + dp[dp.length - 1][col + 1];
        }

        // process last col
        for (int row = dp.length - 2; row >= 0; --row) {
            dp[row][dp[0].length - 1] = str1.charAt(row) + dp[row + 1][dp[0].length - 1];
        }

        for (int i = dp.length - 2; i >= 0; --i) {
            for (int j = dp[0].length - 2; j >= 0; --j) {
                // process 2 cases
                if (i < str1.length() && j < str2.length()) {
                    if (str1.charAt(i) == str2.charAt(j)) {
                        dp[i][j] = str1.charAt(i) + dp[i + 1][j + 1];
                    } else {
                        String res1 = str1.charAt(i) + dp[i + 1][j];
                        String res2 = str2.charAt(j) + dp[i][j + 1];

                        if (res1.length() < res2.length()) {
                            dp[i][j] = res1;
                        } else {
                            dp[i][j] = res2;
                        }
                    }
                }
            }
        }

        return dp[0][0];
    }

    // ----------------------------------------------------------------------------------------------

    /**
     * Bottom up DP approach with memory optimization </br>
     * However, rather than maintaining an entire 2D table, we can optimize space usage by
     * keeping only two rows at a time: *prevRow*, which represents the previous row in the table, and *curRow*
     */
    public String shortestCommonSupersequence3(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();

        String[] prevRow = new String[len2 + 1];

        // init last row
        for (int col = len2; col >= 0; --col) {
            prevRow[col] = str2.substring(col);
        }

        for (int row = len1 - 1; row >= 0; --row) {
            // init last col
            String[] curRow = new String[len2 + 1];
            curRow[len2] = str1.substring(row);

            // build col of curRow, process 2 cases
            for (int col = len2 - 1; col >= 0; --col) {
                if (str1.charAt(row) == str2.charAt(col)) {
                    curRow[col] = str2.charAt(col) + prevRow[col + 1];
                } else {
                    String res1 = prevRow[col];
                    String res2 = curRow[col + 1];

                    curRow[col] = res1.length() < res2.length() ?
                            str1.charAt(row) + res1 :
                            str2.charAt(col) + res2;
                }
            }
            prevRow = curRow;
        }

        return prevRow[0];
    }
}
