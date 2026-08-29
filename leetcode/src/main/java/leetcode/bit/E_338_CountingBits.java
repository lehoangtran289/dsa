package leetcode.bit;

import java.util.Arrays;

public class E_338_CountingBits {
    static void main() {
        System.out.println(Arrays.toString(countBits(10))); // [0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2]
    }

    /**
     * Idea: DP + LSB
     * example: count(1001011) = count(100101) + LSB(1)
     * ---
     * TC: O(n)
     */
    public static int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int x = 0; x <= n; ++x) {
            res[x] = res[x >> 1] + (x & 1);
        }
        return res;
    }

    /**
     * Intuitive idea: for each number, use bit shift to count all set bits
     * ---
     * TC: O(n * 5)
     */
    public static int[] countBits2(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i <= n; ++i) {
            res[i] = count(i);
        }
        return res;
    }

    private static int count(int num) {
        int count = 0;
        while (num > 0) {
            if ((num & 1) == 1) count++;
            num >>= 1;
        }
        return count;
    }
}
