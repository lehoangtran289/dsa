package leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class M_2131_LongestPalindromeByConcatenatingTwoLetterWords {
    public static void main(String[] args) {
        String[] words1 = {"lc", "cl", "cl", "cl", "gg", "gg", "cc", "cc", "cc"};
        System.out.println(longestPalindrome(words1)); // Output: 14

        String[] words2 = {"ab", "ba", "aa", "bb"};
        System.out.println(longestPalindrome2(words2)); // Output: 6
    }

    /**
     * example: lc cl cl cl gg gg cc cc cc
     * -----
     * 2d array freq[26][26] to count the frequency of each two-letter word
     * i != j; lc cl -> 2 * 2 * min(freq[i][j], freq[j][i])
     * i == j; gg gg -> 2 * freq[i][i]
     * i == j and count is odd -> add 2 to the result
     * -----
     * TC: O(n)
     * SC: O(1)
     */
    public static int longestPalindrome(String[] words) {
        int res = 0;
        int isMidAdded = 0;
        int[][] freq = new int[26][26];

        for (String word : words) {
            freq[word.charAt(0) - 'a'][word.charAt(1) - 'a']++;
        }

        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < 26; ++j) {
                int count = freq[i][j];

                if (i == j) {
                    if (count % 2 == 0) {
                        res += count * 2;
                    } else {
                        isMidAdded = 2;
                        res += (count - 1) * 2;
                    }
                } else {
                    res += 2 * Math.min(count, freq[j][i]);
                }
            }
        }

        return res + isMidAdded;
    }

    /**
     * HashMap freq approach
     */
    public static int longestPalindrome2(String[] words) {
        int res = 0;
        int isMidAdded = 0;
        Map<String, Integer> freq = new HashMap<>();

        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            String word = entry.getKey();
            Integer count = entry.getValue();

            if (word.charAt(0) == word.charAt(1)) {
                if (count % 2 == 0) {
                    res += count * 2;
                } else {
                    isMidAdded = 2;
                    res += (count - 1) * 2;
                }
            } else {
                String reverseWord = reverse(word);

                if (freq.containsKey(reverseWord)) {
                    res += 2 * Math.min(count, freq.get(reverseWord));
                }
            }
        }

        return res + isMidAdded;
    }

    private static String reverse (String word) {
        return new StringBuilder(word).reverse().toString();
    }
}
