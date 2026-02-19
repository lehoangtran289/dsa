package leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class E_696_CountBinarySubstrings {
    public static void main(String[] args) {
        System.out.println(countBinarySubstrings("00110011")); // 6
        System.out.println(countBinarySubstrings("10101")); // 4
    }

    /**
     * 1 pass
     * -----
     * TC: O(n) where n is the length of the input string
     * SC: O(1)
     */
    public static int countBinarySubstrings(String s) {
        int res = 0;
        int curCount = 1, prevCount = 0;

        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                curCount++;
            } else {
                res += Math.min(prevCount, curCount);
                prevCount = curCount;
                curCount = 1;
            }
        }
        res += Math.min(prevCount, curCount);

        return res;
    }

    /**
     * 2 passes
     * -----
     * TC: O(n) where n is the length of the input string
     * SC: O(n) for the groups list
     */
    public static int countBinarySubstrings2(String s) {
        List<Integer> groups = new ArrayList<>();
        int count = 1, digit = s.charAt(0) - '0';

        for (int i = 1; i < s.length(); ++i) {
            int curDigit = s.charAt(i) - '0';

            if (curDigit == digit) {
                count++;
            } else {
                groups.add(count);
                count = 1;
                digit = curDigit;
            }
        }
        groups.add(count);

        // count substrings
        int res = 0;

        for (int i = 1; i < groups.size(); ++i) {
            res += Math.min(groups.get(i), groups.get(i - 1));
        }

        return res;
    }
}
