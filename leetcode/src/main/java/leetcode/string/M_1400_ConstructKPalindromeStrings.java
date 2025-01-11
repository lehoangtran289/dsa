package leetcode.string;

import java.util.Arrays;

public class M_1400_ConstructKPalindromeStrings {
    public static void main(String[] args) {
        System.out.println(canConstruct("ibzkwaxxaggkiwjbeysz", 15));
        System.out.println(canConstruct("cr", 7));
        System.out.println(canConstruct("annabelle", 2));
        System.out.println(canConstruct("leetcode", 3));
    }

    public static boolean canConstruct(String s, int k) {
        if (s.length() < k) return false;
        if (s.length() == k) return true;

        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        System.out.println(Arrays.toString(freq));

        int countOdd = 0;
        for (int f : freq) {
            if (f % 2 == 1) countOdd++;
        }
        return countOdd <= k;
    }
}
