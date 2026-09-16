package leetcode.dp;

import java.util.ArrayList;
import java.util.List;

public class M_131_PalindromePartitioning {
    private String s;
    private int n;
    private boolean[][] isPalindrome;
    private List<List<String>> result;

    static void main() {
        System.out.println(new M_131_PalindromePartitioning().partition("aab")); // [[a, a, b], [aa, b]]
    }

    /**
     * Idea: Build isPalindrome checker, where isPalindrome[i][j] = is s[i...j] a palindrome
     * Then backtrack for all possible substrings
     * ---
     * TC: O(n^2 + 2^n * n), isPalindrome check + backtrack all substrings (O(2^n)) * substring (O(n))
     * SC: O(n^2)
     */
    public List<List<String>> partition(String s) {
        this.s = s;
        this.n = s.length();
        this.isPalindrome = new boolean[n][n];
        this.result = new ArrayList<>();

        for (int len = 1; len <= n; ++len) {
            for (int i = 0; i + len - 1 < n; ++i) {
                int j = i + len - 1;

                isPalindrome[i][j] = s.charAt(i) == s.charAt(j)
                                     && (len <= 2 || isPalindrome[i + 1][j - 1]);
            }
        }

        backtrack(0, new ArrayList<>());
        return result;
    }

    private void backtrack(int start, List<String> partition) {
        if (start >= n) {
            result.add(new ArrayList<>(partition));
            return;
        }

        for (int i = start; i < n; ++i) {
            if (isPalindrome[start][i]) {
                partition.add(s.substring(start, i + 1));
                backtrack(i + 1, partition);
                partition.removeLast();
            }
        }
    }
}
