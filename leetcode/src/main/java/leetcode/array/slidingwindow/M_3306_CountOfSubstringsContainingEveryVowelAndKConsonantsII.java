package leetcode.array.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class M_3306_CountOfSubstringsContainingEveryVowelAndKConsonantsII {
    public static void main(String[] args) {
        M_3306_CountOfSubstringsContainingEveryVowelAndKConsonantsII sol = new M_3306_CountOfSubstringsContainingEveryVowelAndKConsonantsII();

        System.out.println(sol.countOfSubstrings("ieaouqqieaouqq", 1)); // 3
        System.out.println(sol.countOfSubstrings("aeiou", 0)); // 1
    }

    public long countOfSubstrings(String word, int k) {
        int n = word.length();
        long res = 0;
        int l = 0;

        // compute index of next consonant for all indices to avoid TLE
        int[] nextConsonant = new int[n];
        int nextConsonantIndex = n;
        for (int i = n - 1; i >= 0; --i) {
            nextConsonant[i] = nextConsonantIndex;
            if (!isVowel(word.charAt(i))) {
                nextConsonantIndex = i;
            }
        }

        Map<Character, Integer> vowelMap = new HashMap<>();
        int consonantCount = 0;

        for (int r = 0; r < n; ++r) {
            char rChar = word.charAt(r);
            if (isVowel(rChar)) {
                vowelMap.put(rChar, vowelMap.getOrDefault(rChar, 0) + 1);
            } else {
                consonantCount++;
            }

            // shrink left if consonant count exceeds k
            while (consonantCount > k) {
                char lChar = word.charAt(l);
                if (isVowel(lChar)) {
                    vowelMap.put(lChar, vowelMap.get(lChar) - 1);
                    if (vowelMap.get(lChar) == 0) vowelMap.remove(lChar);
                } else {
                    consonantCount--;
                }
                l++;
            }

            while (vowelMap.size() == 5 && consonantCount == k) {
                // window still valid when adding more vowels to the right
                // res += nextConsonant[r] - r; since all substrings starting from r to nextConsonant[r] will be valid
                res += nextConsonant[r] - r;

                // try to shrink left and continue counting
                char lChar = word.charAt(l);
                if (isVowel(lChar)) {
                    vowelMap.put(lChar, vowelMap.get(lChar) - 1);
                    if (vowelMap.get(lChar) == 0) vowelMap.remove(lChar);
                } else {
                    consonantCount--;
                }
                l++;
            }
        }
        return res;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
