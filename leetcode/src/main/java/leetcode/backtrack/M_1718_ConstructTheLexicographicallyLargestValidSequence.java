package leetcode.backtrack;

import java.util.Arrays;

public class M_1718_ConstructTheLexicographicallyLargestValidSequence {

    static void main() {
        M_1718_ConstructTheLexicographicallyLargestValidSequence sol = new M_1718_ConstructTheLexicographicallyLargestValidSequence();
        System.out.println(Arrays.toString(sol.constructDistancedSequence(3))); // [3, 1, 2, 3, 2]
    }

    private int n;

    /**
     * TC: O(n!)
     * SC: O(n)
     */
    public int[] constructDistancedSequence(int n) {
        this.n = n;
        int[] res = new int[2 * n - 1];
        backtrack(0, res, new boolean[n + 1]);
        return res;
    }

    private boolean backtrack(int index, int[] seq, boolean[] seen) {
        if (index == seq.length) return true; // all filled
        if (seq[index] != 0) return backtrack(index + 1, seq, seen); // already filled

        for (int num = n; num >= 1; --num) {
            if (seen[num]) continue;

            seq[index] = num;
            seen[num] = true;

            if (num == 1 && backtrack(index + 1, seq, seen)) {
                return true;
            }

            if (index + num < seq.length && seq[index + num] == 0) {
                seq[index + num] = num;
                if (backtrack(index + 1, seq, seen)) return true;
                seq[index + num] = 0;
            }

            seq[index] = 0;
            seen[num] = false;
        }
        return false;
    }
}
