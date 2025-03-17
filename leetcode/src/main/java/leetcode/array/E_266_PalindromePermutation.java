package leetcode.array;

import java.util.HashSet;
import java.util.Set;

public class E_266_PalindromePermutation {
    public boolean canPermutePalindrome2(String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if (!set.contains(curChar)) {
                set.add(curChar);
            } else {
                set.remove(curChar);
            }
        }
        return set.size() <= 1;
    }

    public boolean canPermutePalindrome(String s) {
        boolean[] checkPair = new boolean[26];

        for (int i = 0; i < s.length(); ++i) {
            char curChar = s.charAt(i);
            checkPair[curChar - 'a'] = !checkPair[curChar - 'a'];
        }

        int count = 0;
        for (boolean b : checkPair) {
            if (b) count++;
            if (count > 1) return false;
        }

        return true;
    }
}
