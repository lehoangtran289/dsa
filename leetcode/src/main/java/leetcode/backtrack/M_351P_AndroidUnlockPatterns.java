package leetcode.backtrack;

public class M_351P_AndroidUnlockPatterns {

    private static int[][] memo;
    private static int[][] crossPoints;

    /**
     * Idea: backtrack with visited array -> backtrack with dp bitmask
     * ---
     * TC: O(1), since explore at most 10 * 1025 (dp size)
     */
    public int numberOfPatterns(int m, int n) {
        memo = new int[10][1025];

        // init cross visited array
        crossPoints = new int[10][10];
        crossPoints[1][3] = crossPoints[3][1] = 2; // horizontal
        crossPoints[4][6] = crossPoints[6][4] = 5;
        crossPoints[7][9] = crossPoints[9][7] = 8;
        crossPoints[1][7] = crossPoints[7][1] = 4; // vertical
        crossPoints[2][8] = crossPoints[8][2] = 5;
        crossPoints[3][9] = crossPoints[9][3] = 6;
        crossPoints[1][9] = crossPoints[9][1] = 5; // diagonal
        crossPoints[3][7] = crossPoints[7][3] = 5;

        // backtrack
        int res = 0;
        for (int i = 1; i <= 9; ++i) {
            int mask = set(0, i);
            res += backtrack(m, n, mask, i, 1);
        }
        return res;
    }

    private int backtrack(
            int m,
            int n,
            int mask,
            int pos,
            int curLength
    ) {
        if (memo[pos][mask] != 0) return memo[pos][mask];
        if (curLength > n) return 0;

        int res = 0;
        if (curLength >= m) res++;

        for (int i = 1; i <= 9; ++i) {
            if (isSet(mask, i)) continue;

            // 2 cases:
            // (1) there is no crossing point
            // (2) there is crossing point, and it is already visited
            if (crossPoints[pos][i] == 0 || isSet(mask, crossPoints[pos][i])) {
                mask = set(mask, i);
                res += backtrack(m, n, mask, i, curLength + 1);
                mask = unset(mask, i);
            }
        }

        return memo[pos][mask] = res;
    }

    // helper functions

    private boolean isSet(int mask, int pos) {
        return ((mask >> pos) & 1) == 1;
    }

    private int set(int mask, int pos) {
        return mask | (1 << pos);
    }

    private int unset(int mask, int pos) {
        return mask & ~(1 << pos);
    }
}
