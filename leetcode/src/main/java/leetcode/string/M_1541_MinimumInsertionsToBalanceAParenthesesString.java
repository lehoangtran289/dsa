package leetcode.string;

public class M_1541_MinimumInsertionsToBalanceAParenthesesString {

    /**
     * Idea: Intuition stack style
     * ---
     * TC: O(N)
     * SC: O(1)
     */
    public int minInsertions(String s) {
        int res = 0;
        int n = s.length();
        int open = 0;

        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);

            if (c == '(') {
                open++;
                continue;
            }

            // last char is ) -> add 1 missing )
            if (i == n - 1) res++;

            // if next char is ) -> forward i
            // else add 1 missing )
            if (i < n - 1) {
                if (s.charAt(i + 1) == ')') i++;
                else res++;
            }

            // stack empty = missing ( -> add 1
            if (open == 0) res++;
            else open--;
        }

        return res + open * 2;
    }
}
