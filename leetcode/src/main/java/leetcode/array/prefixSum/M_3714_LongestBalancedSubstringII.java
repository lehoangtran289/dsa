package leetcode.array.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class M_3714_LongestBalancedSubstringII {

    /**
     * Prefix Sum + HashMap
     * -----------
     * Idea: We can break down the problem into three cases:
     * 1. Single character substring (a), (b), (c)
     * 2. Two characters substring, (a,b), (b,c), (c,a)
     * 3. Three characters substring (a,b,c)
     * <p>
     * For case 1, we can simply iterate through the string and count consecutive characters.
     * <p>
     * For case 2, we can use a prefix sum approach similar to the "Contiguous Array" problem. We maintain a count that increments for one character and decrements for the other. We use a HashMap to store the first occurrence of each count value.
     * <p>
     * For case 3, we can maintain counts of 'a', 'b', and 'c'. A substring is balanced if the counts of 'a', 'b', and 'c' are all equal. We can use a HashMap to store the first occurrence of the difference between these counts.
     * <p>
     * -----------
     * TC: O(n) for each case, so overall O(n)
     * SC: O(n) for the HashMaps
     */
    public int longestBalanced(String s) {
        int res = 0;

        // case 1: single char substring (a), (b), (c)
        res = longestBalancedOneChar(s);

        // case 2: 2 chars substring, (a,b), (b,c), (c,a)
        res = max(
                res,
                longestBalancedTwoChars(s, 'a', 'b'),
                longestBalancedTwoChars(s, 'b', 'c'),
                longestBalancedTwoChars(s, 'c', 'a')
        );

        // case 3: 3 chars substring (a,b,c)
        res = max(res, longestBalancedThreeChars(s));

        return res;
    }

    private int longestBalancedOneChar(String s) {
        int n = s.length();
        int res = 1;
        int count = 1;

        for (int i = 1; i < n; ++i) {
            if (s.charAt(i) == s.charAt(i - 1)) count++;
            else count = 1;

            res = Math.max(res, count);
        }

        return res;
    }

    private int longestBalancedTwoChars(String s, char first, char second) {
        int n = s.length();
        int res = 0;
        Map<Integer, Integer> seenCount = new HashMap<>();
        int count = 0;
        int lastReset = -1;

        for (int i = 0; i < n; ++i) {
            char curChar = s.charAt(i);

            if (curChar == first) count++;
            else if (curChar == second) count--;
            else {
                count = 0;
                lastReset = i;
                seenCount = new HashMap<>();
            }

            if (count == 0) {
                res = Math.max(res, i - lastReset);
            }

            if (seenCount.containsKey(count)) {
                res = Math.max(res, i - seenCount.get(count));
            } else {
                seenCount.put(count, i);
            }
        }

        return res;
    }

    private int longestBalancedThreeChars(String s) {
        int n = s.length();
        int res = 0;
        Map<String, Integer> seenPairs = new HashMap<>();
        int[] abcCount = new int[3];

        for (int i = 0; i < n; ++i) {
            char curChar = s.charAt(i);

            abcCount[curChar - 'a']++;

            if (abcCount[0] != 0 && abcCount[0] == abcCount[1] && abcCount[0] == abcCount[2])
                res = i + 1;

            int abCount = abcCount[0] - abcCount[1];
            int acCount = abcCount[0] - abcCount[2];
            String key = abCount + "&" + acCount;

            if (seenPairs.containsKey(key)) {
                res = Math.max(res, i - seenPairs.get(key));
            } else {
                seenPairs.put(key, i);
            }
        }

        return res;
    }

    private int max(int... params) {
        int max = -(1 << 30);
        for (int param : params) {
            max = Math.max(max, param);
        }
        return max;
    }
}
