package leetcode.array.twopointers;

public class E_680_ValidPalindromeII {

    /**
     * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
     * Idea: 2 pointers approach.
     * ----------
     * TC: O(n)
     * SC: O(1)
     */
    public boolean validPalindrome(String s) {
        int n = s.length();
        int p1 = 0, p2 = n - 1;

        while (p1 <= p2) {
            if (s.charAt(p1) != s.charAt(p2)) {
                return isPalindrome(s, p1 + 1, p2) || isPalindrome(s, p1, p2 - 1);
            }

            p1++;
            p2--;
        }

        return true;
    }

    private boolean isPalindrome(String s, int l, int r) {
        while (l <= r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }
}
