package leetcode.divideconquer;

import java.util.HashSet;
import java.util.Set;

public class E_1763_LongestNiceSubstring {
    public static void main(String[] args) {
        System.out.println(longestNiceSubstring("YazaAay"));
    }

    public static String longestNiceSubstring(String s) {
        if (s.length() < 2) return "";
        char[] arr = s.toCharArray();

        Set<Character> set = new HashSet<>();
        for (char c : arr) set.add(c);

        for (int i = 0; i < arr.length; ++i) {
            if (isValid(set, arr[i])) continue;
            String sub1 = longestNiceSubstring(s.substring(0, i));
            String sub2 = longestNiceSubstring(s.substring(i + 1));
            return sub1.length() >= sub2.length() ? sub1 : sub2;
        }
        return s;
    }

    public static boolean isValid(Set<Character> s, char c) {
        return s.contains(Character.toLowerCase(c)) && s.contains(Character.toUpperCase(c));
    }
}
