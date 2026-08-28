package leetcode.array.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Counts the number of substrings in the given word that contain every vowel (a, e, i, o, u) at least once </br>
 * and exactly k consonants.
 */
public class M_3306_CountOfSubstringsContainingEveryVowelAndKConsonantsII {
    static void main() {
        System.out.println(countOfSubstrings("ieaouqqieaouqq", 1)); // 3
        System.out.println(countOfSubstrings("aeiou", 0)); // 1
    }

    /**
     * Valid substrings = atLeastK(word, k) - atLeastK(word, k + 1) </br>
     * Intuition: once we reach our first valid window, every substring that extends from this point onward is also valid
     * ---
     * TC: O(n) </br>
     * SC: O(n) </br>
     */
    public static long countOfSubstrings(String word, int k) {
        return atLeast(word, k) - atLeast(word, k + 1);
    }

    /**
     * Number of substrings of word that contain every vowel and at least k consonants.
     * ---
     */
    private static long atLeast(String word, int k) {
        long res = 0;
        Map<Character, Integer> vowelCount = new HashMap<>();
        int consonantCount = 0;

        int l = 0;
        for (int r = l; r < word.length(); ++r) {
            char rightChar = word.charAt(r);

            if (isVowel(rightChar)) {
                vowelCount.put(rightChar, vowelCount.getOrDefault(rightChar, 0) + 1);
            } else {
                consonantCount++;
            }

            // find min valid subarray starting at l
            while (vowelCount.size() == 5 && consonantCount >= k) {
                res += word.length() - r;

                char leftChar = word.charAt(l);
                if (isVowel(leftChar)) {
                    vowelCount.put(leftChar, vowelCount.get(leftChar) - 1);
                    if (vowelCount.get(leftChar) == 0) vowelCount.remove(leftChar);
                } else {
                    consonantCount--;
                }
                l++;
            }
        }

        return res;
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
