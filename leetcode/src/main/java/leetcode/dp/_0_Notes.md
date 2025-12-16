- For problems about finding subsequences, a standard trick is to restrict to a
  prefix or suffix of the input (as in the LIS example above, or in HW6.1). Such a prefix or
  suffix can be specified by one index parameter. Some prefer prefixes (which requires working
  “backward”), and others prefer suffixes (which requires working “forward”)—whichever way
  suits your thinking better.

- If the problem has additional parameters and extra constraints involving those parameters,
  these parameters may need to be incorporated into the subproblem definition. E.g., HW6.2
  has an additional size restriction (the parameter k), and so we end up with a two-parameter
  class of subproblems there.

- If the problem involves two (or more) sequences like LCS, we may need two (or
  more) index parameters for different prefixes (or suffixes) of those sequences.
    - dp[i][j] = solution for prefixes A[1..i],B[1..j]

- For problems like subset sum or knapsack, we may need a parameter for the target
  integer (leading to so-called “pseudo-polynomial” algorithms only).
    - dp[i][t] = whether the first i items can achieve total t, or max value achievable with
      first i items and capacity t

- For problems where the input is a tree, a standard trick is to use a tree node v as
  a parameter and restrict the input to the subtree rooted at v.
    - dp[v] = combine results from children of v

- For problems of type (G), we often need to restrict to a contiguous block of the input rather
  than a prefix or suffix, and so use 2 parameters for the start and end indices.
    - dp[i][j] = answer for subarray (or substring) A[i..j]
    - dp[i][j] = min_(k=i -> j−1) (dp[i][k] + dp[k+1][j] + cost)

- Sometimes we may need a flag as an extra parameter. E.g., in HW6.1, we have a parameter
  with a constant number of possibilities {//, \\, /\, \/}. In the first solution to Problem
  Old.7.2, we have a parameter with a Yes/No flag (or equivalently two functions, one for Yes
  and one for No

-------------------------------------

```java
/**
 * Bottom-up DP approach
 * ------------------------------
 * Recursion relation:
 * dp[i][i] = 0 for all i
 * dp[i][j] = min(dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j]) for all i <= k < j
 * ------------------------------
 * TC: O(n^3)
 * SC: O(n^2)
 */
public int matrixChainOrder(int[] p) {
    int n = p.length - 1;
    int[][] dp = new int[n + 1][n + 1]; // dp[i][j] = min cost for multiplying Ai...Aj
    int[][] split = new int[n + 1][n + 1]; // split[i][j] = index k at which to split

    // dp is traversed by length of the chain to reuse previously computed results
    for (int len = 2; len <= n; ++len) {
        for (int i = 1; i <= n - len + 1; ++i) {
            int j = i + len - 1;

            dp[i][j] = 1 << 30;

            for (int k = i; k < j; ++k) {
                int cost = dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j];

                if (cost < dp[i][j]) {
                    split[i][j] = k;
                    dp[i][j] = cost;
                }
            }
        }
    }
    System.out.println(printOptimalParens(split, 1, n));

    return dp[1][n];
}

public int cutRod2(int[] price, int n) {
    int[] dp = new int[n + 1];

    for (int i = 1; i <= n; ++i) {
        for (int j = 1; j <= i; ++j) {
            dp[i] = Math.max(dp[i], dp[i - j] + price[j]);
        }
    }
    return dp[n];
}


```

- Knapsack

```java
/**
 * 0/1 Knapsack Problem
 * -----------------------------------------------
 * <a href="https://leetcode.com/discuss/post/1200320/thief-with-a-knapsack-a-series-of-crimes-lcdd/">ref</a>.</a>
 * <a href="https://leetcode.com/discuss/post/1152328/01-knapsack-problem-and-dynamic-programm-4had/">ref2</a>
 * -----------------------------------------------
 * Given n items with weights and values, put these into a knapsack of capacity W to get the maximum total value
 * dp[i][w] = max profit using first i items with current capacity w
 * dp[i][w] = max(
 *      dp[i - 1][w], // not take
 *      dp[i - 1][w - weights[i]] + profits[i] // take
 * )
 * Result: dp[n][W] ~ max profit using first n items with full capacity W
 * -----------------------------------------------
 * |                  | 0 | 1 | 2            | 3             | 4             | 5             |
 * | Weights | Values |
 * |---------|--------|---|---|--------------|---------------|---------------|---------------|
 * | 0       | 0      | 0 | 0 | 0            | 0             | 0             | 0             |
 * | 1       | 6      | 0 | 6 | 6            | 6             | 6             | 6             |
 * | 2       | 10     | 0 | 6 | 6, 10+0 = 10 | 6, 10+6 = 16  | 6, 10+6 = 16  | 6, 10+6 = 16  |
 * | 3       | 12     | 0 | 6 | 10           | 16, 12+0 = 16 | 16, 12+6 = 18 | 16, 12+10 = 22 |
 * -------------------------------
 * Ref: M_416_PartitionEqualSubsetSum:
 * Bottom up DP 2D
 *      dp[i][sum] = can we achieve subset <sum> using first <i> elements
 *      dp[i][sum] = dp[i - 1][sum] || dp[i - 1][sum - nums[i]] // not take || take
 * --------------------------------
 * TC: O(n*sum)
 * SC: O(n*sum)
 * ---
 * Space optimization : Bottom up DP 1D
 * --------------------------------
 * dp[sum] = can we achieve <sum> using cur element
 * e.g:
 *      num = 2 -> traverse (from end) from sum -> 2, if we can achieve <sum>
 *      if we can, that means dp[cur_sum] = true or dp[cur_sum - 2] = true
 *      => dp[cur_sum] = dp[cur_sum] || dp[cur_sum - 2]
 *
 *      initially, dp[0] = true
 *      if we start with num = 2 -> only dp[2] is true
 *      -> So we can reach sum = 2 using num = 2
 *
 *      then num = 3 -> traverse from sum -> 3
 *      dp[5] = dp[2] -> dp[5] = true
 *      dp[3] = dp[0] = true
 *      dp[4] = dp[1] = false
 *      -> So we can reach sum = 2, 3, 5 using num = 2, 3
 * --------------------------------
 * TC: O(n*sum)
 * SC: O(sum)
 *
 */
public class _0_Knapsack01 {
    /**
     * ----------------------------------------------
     * Top-down Knapsack DP
     * ----------------------------------------------
     */
    private int[] weights;
    private int[] profits;
    private int[][] memo;

    /**
     * ----------------------------------------------
     * Bottom-up 1D DP
     * Idea:
     * dp[w] = max profit with current capacity w
     * dp[w] = max(
     *      dp[w], // not take
     *      dp[w - weights[i]] + profits[i] // take
     * )
     * Result: dp[W] ~ max profit with full capacity W
     * ----------------------------------------------
     */
    public int knapsack3(int[] weights, int[] profits, int capacity) {
        int n = weights.length;
        int[] dp = new int[capacity + 1];

        for (int i = 0; i < n; ++i) {
            for (int w = capacity; w >= weights[i]; --w) { // current capacity
                dp[w] = Math.max(
                        dp[w], // not take
                        dp[w - weights[i]] + profits[i] // take
                );
            }
        }

        return dp[capacity];
    }

    /**
     * ----------------------------------------------
     * Bottom-up 2D DP
     * Idea:
     * dp[i][w] = max profit using first i items with current capacity w
     * dp[i][w] = max(
     *      dp[i - 1][w], // not take
     *      dp[i - 1][w - weights[i]] + profits[i] // take
     * )
     * Result: dp[n][W] ~ max profit using first n items with full capacity W
     * ----------------------------------------------
     */
    public int knapsack2(int[] weights, int[] profits, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; ++i) {
            for (int w = 1; w <= capacity; ++w) { // current capacity
                int index = i - 1; // 0-based index for weights and profits

                if (weights[index] > w) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    dp[i][w] = Math.max(
                            dp[i - 1][w], // not take
                            dp[i - 1][w - weights[index]] + profits[index] // take
                    );
                }
            }
        }
        for (int[] row : dp) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        return dp[n][capacity];
    }

    public int knapsack(int[] weights, int[] profits, int capacity) {
        int n = weights.length;
        this.weights = weights;
        this.profits = profits;
        this.memo = new int[n][capacity + 1]; // int[i][w] = max profit using first i items with capacity w

        return dp(n - 1, capacity);
    }

    private int dp(int i, int w) {
        if (i == 0 || w <= 0) return 0;
        if (memo[i][w] != 0) return memo[i][w];

        if (weights[i] > w) {
            return dp(i - 1, w);
        } else {
            memo[i][w] = Math.max(
                    dp(i - 1, w), // not take
                    dp(i - 1, w - weights[i]) + profits[i] // take
            );
        }

        return memo[i][w];
    }

}

```

- LIS

```java
    /**
 * Bottom up DP approach.
 * ----------------------------------
 * Let's say we know dp[0], dp[1], and dp[2].
 * How can we find dp[3] given this information?
 * Well, since dp[2] represents the length of the longest increasing subsequence that ends with nums[2],
 * if nums[3] > nums[2], then we can simply take the subsequence ending at i = 2 and append nums[3] to it, increasing the length by 1
 * ----------------------------------
 * Time complexity: O(n^2)
 * Space complexity: O(n)
 * dp[i] = 1 OR dp[j] + 1 for j < i and nums[j] < nums[i], where dp[j] is the length of the longest increasing subsequence ending at index j.
 */
public int lengthOfLIS(int[] nums) {
    int n = nums.length;
    int res = 1;
    int[] dp = new int[n];
    Arrays.fill(dp, 1);

    for (int i = n - 2; i >= 0; --i) {
        for (int j = i + 1; j < n; ++j) {
            if (nums[j] > nums[i]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        res = Math.max(res, dp[i]);
    }

    return res;
} 
```

- LCS

```java
/**
 * DP bottom up
 * ----------------------------------
 * dp[i][j] = LCS for first i chars of s1 and first j chars of s2
 * Recurrence relation:
 * dp[i][j] = dp[i - 1][j - 1] + 1                 if s1[i] == s2[j]
 * dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])      if s1[i] != s2[j]
 * ----------------------------------
 * TC: O(mn) - s1.length() * s2.length()
 * SC: O(mn)
 */
public int longestCommonSubsequence(String s1, String s2) {
    int m = s1.length(), n = s2.length();
    int[][] dp = new int[m + 1][n + 1]; // dp[i][j] = LCS for first i chars of s1 and first j chars of s2

    for (int i = 1; i <= m; ++i) {
        for (int j = 1; j <= n; ++j) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }
    return dp[m][n];
}

public int longestCommonSubsequence_withPath(String s1, String s2) {
    int m = s1.length(), n = s2.length();
    int[][] dp = new int[m + 1][n + 1]; // dp[i][j] = LCS for first i chars of s1 and first j chars of s2

    String[][] dir = new String[m + 1][n + 1]; // to reconstruct the optimal path
    dir[0][0] = "START";

    for (int i = 1; i <= m; ++i) {
        for (int j = 1; j <= n; ++j) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
                dir[i][j] = "DIAG";
            } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                dp[i][j] = dp[i - 1][j];
                dir[i][j] = "UP";
            } else {
                dp[i][j] = dp[i][j - 1];
                dir[i][j] = "LEFT";
            }
        }
    }
    // Print the LCS path
    for (String[] row : dir) {
        System.out.println(Arrays.toString(row));
    }

    return dp[m][n];
} 
```