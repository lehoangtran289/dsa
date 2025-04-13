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
    private long power(long base, long exponent) {
        long res = 1;

        // Calculate the exponentiation using binary exponentiation
        while (exponent > 0) {
            // If the exponent is odd, multiply the result by the base
            if (exponent % 2 == 1) {
                res = (res * base) % MOD;
            }

            // Square the base and halve the exponent
            base = (base * base) % MOD;
            exponent /= 2;
        }

        return res;
    }
}
