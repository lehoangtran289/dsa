package leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class E_2273_FindResultantArrayAfterRemovingAnagrams {

    /**
     * Simulation
     * ----------------
     * TC: O(n * k) where n is the number of words and k is the maximum length of a word
     * SC: O(n)
     */
    public List<String> removeAnagrams(String[] words) {
        List<String> res = new ArrayList<>();
        res.add(words[0]);

        for (int i = 1; i < words.length; ++i) {
            if (!isAnagram(words[i], words[i - 1])) {
                res.add(words[i]);
            }
        }

        return res;
    }

    private boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;

        int[] freq = new int[26];

        for (int i = 0; i < s1.length(); ++i) {
            freq[s1.charAt(i) - 'a']++;
            freq[s2.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; ++i) {
            if (freq[i] != 0)
                return false;
        }
        return true;
    }
}
