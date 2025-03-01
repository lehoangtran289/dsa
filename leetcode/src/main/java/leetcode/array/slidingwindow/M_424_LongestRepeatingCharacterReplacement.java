package leetcode.array.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class M_424_LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        System.out.println(characterReplacement2("ABAB", 2)); // 4
        System.out.println(characterReplacement2("AABABBA", 1)); // 4
    }

    // ------------APPROACH 1: Binary search O(logn) + Sliding window O(n)

    public static int characterReplacement1(String s, int k) {
        int l = 0, r = s.length();
        int res = l;

        while (l <= r) {
            int mid = (l + r) / 2;
            if (hasRepeatingSubstring(s, k, mid)) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return res;
    }

    /**
     * O(n)
     * Sliding window with fix length
     */
    private static boolean hasRepeatingSubstring(String s, int k, int length) {
        Map<Character, Integer> map = new HashMap<>();
        int maxFreq = 0;

        int l = 0;
        for (int r = 0; r < s.length(); ++r) {
            char curChar = s.charAt(r);
            map.put(curChar, map.getOrDefault(curChar, 0) + 1);

            if (r - l + 1 > length) {
                char startChar = s.charAt(l);
                map.put(startChar, map.get(startChar) - 1);
                if (map.get(startChar) == 0) map.remove(startChar);

                l++;
            }

            maxFreq = Math.max(maxFreq, map.get(curChar));
            if (length - maxFreq <= k) return true;
        }
        return false;
    }

    // ------------APPROACH 2: Sliding window dynamic size O(n)
    public static int characterReplacement2(String s, int k) {
        Map<Character, Integer> map = new HashMap<>(); // <char, freq>
        int l = 0;
        int maxFreq = 0;
        int res = 0;

        for (int r = 0; r < s.length(); ++r) {
            char curChar = s.charAt(r);
            map.put(curChar, map.getOrDefault(curChar, 0) + 1);

            // the maximum frequency we have seen in any window yet
            maxFreq = Math.max(maxFreq, map.get(curChar));

            // move the start pointer towards right if the current window is invalid
            while (r - l + 1 - maxFreq > k) {
                char startChar = s.charAt(l);
                map.put(startChar, map.get(startChar) - 1);

                maxFreq = Math.max(maxFreq, map.get(curChar));

                l++;
            }

            // the window is valid, so update the result
            res = Math.max(res, r - l + 1);
        }

        return res;
    }
}
