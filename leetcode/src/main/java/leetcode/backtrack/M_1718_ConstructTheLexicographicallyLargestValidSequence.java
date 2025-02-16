package leetcode.backtrack;

import java.util.Arrays;

public class M_1718_ConstructTheLexicographicallyLargestValidSequence {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(constructDistancedSequence(3)));
    }

    public static int[] constructDistancedSequence(int n) {
        int[] res = new int[2 * n - 1];
        boolean[] visited = new boolean[n + 1];

        backtrack(0, res, visited, n);

        return res;
    }

    private static boolean backtrack(int curIdx, int[] res, boolean[] visited, int n) {
        // If we have filled all positions, return true indicating success
        if (curIdx == res.length) return true;

        // If the current position is already filled, move to the next index
        if (res[curIdx] != 0) {
            return backtrack(curIdx + 1, res, visited, n);
        }

        // attempt to place numbers from n to 1
        for (int i = n; i >= 1; --i) {
            if (visited[i]) continue;

            // set
            visited[i] = true;
            res[curIdx] = i;

            if (i == 1) {
                if (backtrack(curIdx + 1, res, visited, n)) return true;
            } else if (
                    curIdx + i < res.length &&
                    res[curIdx + i] == 0
            ) {
                // set
                res[curIdx + i] = i;

                if (backtrack(curIdx + 1, res, visited, n)) return true;

                // undo
                res[curIdx + i] = 0;
            }

            // undo
            visited[i] = false;
            res[curIdx] = 0;
        }

        return false;
    }
}
