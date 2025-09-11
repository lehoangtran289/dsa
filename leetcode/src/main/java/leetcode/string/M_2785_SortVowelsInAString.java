package leetcode.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class M_2785_SortVowelsInAString {

    public static void main(String[] args) {
        System.out.println(sortVowels("lEetcOde")); // lEOtcede
        System.out.println(sortVowels("lEetcOdeA")); // lAEtcOdee
    }

    /**
     * Counting sort with array
     * ------------
     * Time: O(n)
     * Space: O(1)
     */
    public static String sortVowels(String s) {
        // init vowel count array
        int[] count = new int[100];

        for (char c : s.toCharArray()) {
            if (isVowel(c)) {
                count[c - 'A']++;
            }
        }

        // build result string by iterating s
        StringBuilder res = new StringBuilder();
        int vowelIndex = 0;

        for (char c : s.toCharArray()) {
            if (!isVowel(c)) {
                res.append(c);
            } else {
                while (count[vowelIndex] == 0) {
                    vowelIndex++;
                }
                res.append((char) (vowelIndex + 'A'));
                count[vowelIndex]--;
            }
        }

        return res.toString();
    }

    /**
     * Sorting using list
     * ------------
     * Time: O(nlogn)
     * Space: O(n)
     */
    public static String sortVowels2(String s) {
        // init vowel list then sort
        List<Character> vowels = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (isVowel(c)) {
                vowels.add(c);
            }
        }
        Collections.sort(vowels);

        // build result string by iterating s
        StringBuilder res = new StringBuilder();
        int vowelIndex = 0;

        for (char c : s.toCharArray()) {
            if (!isVowel(c)) {
                res.append(c);
            } else {
                res.append(vowels.get(vowelIndex++));
            }
        }

        return res.toString();
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
               c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}
