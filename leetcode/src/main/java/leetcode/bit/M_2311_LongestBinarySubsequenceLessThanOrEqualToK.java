package leetcode.bit;

public class M_2311_LongestBinarySubsequenceLessThanOrEqualToK {
    public static void main(String[] args) {
        System.out.println(longestSubsequence("1001010", 5)); // 5
    }

    /**
     * Greedy approach
     * Idea: Traverse right to left, calculate binary -> int, if > k -> skip, only count 0
     * ---------------------
     * TC: O(N)
     * SC: O(1)
     */
    public static int longestSubsequence(String s, int k) {
        int n = s.length();
        int res = 0;
        int num = 0;

        for (int i = n - 1; i >= 0; --i) {
            char digit = s.charAt(i);

            if (digit == '0') {
                res++;
            } else if (num <= k) {
                num += (int) Math.pow(2, n - 1 - i);
                if (num <= k) res++;
            }
        }

        return res;
    }
}
