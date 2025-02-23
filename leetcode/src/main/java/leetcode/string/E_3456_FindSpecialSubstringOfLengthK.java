package leetcode.string;

import java.util.HashMap;
import java.util.Map;

public class E_3456_FindSpecialSubstringOfLengthK {
    public static void main(String[] args) {
        System.out.println(hasSpecialSubstring1("aaabaaa", 3)); // true
        System.out.println(hasSpecialSubstring1("h", 1)); // true
        System.out.println(hasSpecialSubstring1("hh", 2)); // true
        System.out.println(hasSpecialSubstring1("baaabb", 3)); // true
        System.out.println(hasSpecialSubstring1("abcd", 3)); // false
        System.out.println(hasSpecialSubstring1("ccc", 2)); // false
        System.out.println(hasSpecialSubstring1("daa", 1)); // true
    }

    /**
     * Sliding window approach
     */
    public static boolean hasSpecialSubstring0(String s, int k) {
        int l = 0;
        Map<Character, Integer> freqMap = new HashMap<>();
        for (int r = 0; r < s.length(); ++r) {
            char curChar = s.charAt(r);
            freqMap.put(curChar, freqMap.getOrDefault(curChar, 0) + 1);

            if (freqMap.size() > 1) {
                while (l <= r && freqMap.size() > 1) {
                    char leftChar = s.charAt(l);
                    freqMap.put(leftChar, freqMap.get(leftChar) - 1);
                    if (freqMap.get(leftChar) == 0) freqMap.remove(leftChar);
                    l++;
                }
            }

            if (freqMap.get(curChar) == k) {
                if (r == s.length() - 1 || s.charAt(r + 1) != curChar) return true;
            }
        }

        return false;
    }

    /**
     * Sliding window counting
     */
    public static boolean hasSpecialSubstring1(String s, int k) {
        int l = 0, r = 0;

        while (r < s.length()) {
            while (r < s.length() && s.charAt(r) == s.charAt(l)) {
                r++;
            }
            if (r - l == k) return true;
            l = r;
        }

        return false;
    }
}
