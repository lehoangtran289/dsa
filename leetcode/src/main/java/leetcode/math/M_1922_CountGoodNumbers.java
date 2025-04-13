package leetcode.math;

public class M_1922_CountGoodNumbers {
    private final int MOD = (int) Math.pow(10, 9) + 7;

    public int countGoodNumbers(long n) {
        long oddPosCount = n / 2;
        long evenPosCount = n - oddPosCount;

        long evenChoices = power(5, evenPosCount);
        long oddChoices = power(4, oddPosCount);
        return (int) ((evenChoices * oddChoices) % MOD);
    }

    // Fast exponentiation % MOD
    // idea: 3 ^ 10 = 3 ^ (2 * 5) = (3 ^ 2) ^ (10 / 2)
    // idea2 : 3 ^ 9 = 3 ^ (1 + 2 * 4) = 3 * [(3 ^ 2) ^ (9 / 2)]
    private long power(long base, long exponent) {
        long res = 1;

        while (exponent > 0) {
            if ((exponent & 1) == 1) { // odd exponent
                res = (res * base) % MOD;
            }

            base = (base * base) % MOD;
            exponent /= 2;
        }

        return res;
    }
}
