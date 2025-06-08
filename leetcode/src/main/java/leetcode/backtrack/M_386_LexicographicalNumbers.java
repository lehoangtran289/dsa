package leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class M_386_LexicographicalNumbers {
    public static void main(String[] args) {
        System.out.println(new M_386_LexicographicalNumbers().lexicalOrder(13));
        // [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]
    }

    /**
     * Backtracking
     * -----------------------
     * Idea: Generate numbers in lexicographical order by treating them as a tree.
     * We start from 1 to 9 and recursively build numbers by appending digits 0-9.
     * -----------------------
     * TC: O(n)
     * SC: O(1)
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();

        for (int i = 1; i <= 9; ++i) {
            backtrack(i, result, n);
        }

        return result;
    }

    private void backtrack(int cur, List<Integer> result, int target) {
        // base case
        if (cur > target) return;

        result.add(cur);

        for (int i = 0; i <= 9; ++i) {
            cur = cur * 10 + i;
            backtrack(cur, result, target);
            cur /= 10;
        }
    }
}
