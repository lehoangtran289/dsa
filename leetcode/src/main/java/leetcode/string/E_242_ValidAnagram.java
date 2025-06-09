package leetcode.string;

public class E_242_ValidAnagram {

    /**
     * Given two strings s and t, return true if t is an anagram of s and false otherwise.
     * Idea: Counting
     * --------------------
     * TC: O(n)
     * SC: O(1)
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] countS = new int[26];
        int[] countT = new int[26];

        for (int c : s.toCharArray()) {
            countS[c - 'a']++;
        }

        for (int c : t.toCharArray()) {
            countT[c - 'a']++;
        }

        for (int i = 0; i < 26; ++i) {
            if (countS[i] != countT[i]) return false;
        }

        return true;
    }
}
