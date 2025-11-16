package leetcode.math;

public class M_1513_NumberOfSubstringsWithOnly1s {
    public static void main(String[] args) {
        System.out.println(numSub("0110111")); // 9
    }

    /**
     * Math
     * Idea: count length of continuous '1's segments
     * Count number of substrings: length * (length + 1) / 2
     * --------------------------------
     * TC: O(n)
     * SC: O(1)
     * --------------------------------
     */
    public static int numSub(String s) {
        final int MOD = (int) 1e9 + 7;
        long res = 0;
        long onesLength = 0;

        for (char c : s.toCharArray()) {
            if (c == '1') {
                onesLength++;
            } else {
                res = (res + onesLength * (onesLength + 1) / 2) % MOD;
                onesLength = 0;
            }
        }
        res = (res + onesLength * (onesLength + 1) / 2) % MOD;
        return (int) res;
    }
}
