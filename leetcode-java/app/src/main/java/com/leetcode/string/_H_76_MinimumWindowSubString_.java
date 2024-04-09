package com.leetcode.string;

import java.util.HashMap;
import java.util.Map;

public class _H_76_MinimumWindowSubString_ {
    public static void main(String[] args) {
        _H_76_MinimumWindowSubString_ obj = new _H_76_MinimumWindowSubString_();
        System.out.println(obj.minWindow("ADOBECODEBANC", "ABC"));
    }

    public String minWindow(String s, String t) {
        Map<Character, Integer> freqS = new HashMap<>();
        Map<Character, Integer> freqT = new HashMap<>();

        for (int i = 0; i < t.length(); ++i) {
            Character c = t.charAt(i);
            freqT.put(c, freqT.getOrDefault(c, 0) + 1);
        }

        int l = 0, r = 0, lAns = 0, rAns = s.length();
        boolean hasFound = false;
        while (l < s.length()) {
            while (r < s.length() && !validateSubString(freqS, freqT)) {
                freqS.put(s.charAt(r), freqS.getOrDefault(s.charAt(r), 0) + 1);
                r++;
            }

            if (r - l <= rAns - lAns && validateSubString(freqS, freqT)) {
                hasFound = true;
                lAns = l;
                rAns = r;
            }
            freqS.put(s.charAt(l), freqS.get(s.charAt(l)) - 1);
            l++;
        }
        return hasFound ? s.substring(lAns, rAns) : "";
    }

    private boolean validateSubString(Map<Character, Integer> freqS, Map<Character, Integer> freqT) {
        for (Map.Entry<Character, Integer> entry : freqT.entrySet()) {
            if (freqS.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }
}
