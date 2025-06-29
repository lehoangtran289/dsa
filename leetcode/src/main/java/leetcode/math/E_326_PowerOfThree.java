package leetcode.math;

public class E_326_PowerOfThree {
    public static void main(String[] args) {
        System.out.println(isPowerOfThree(243)); // true
    }

    /**
     * Math
     * --------------------
     * x = 3 ^ pow
     * pow = log3(x) => pow = log10(x) / log10(3)
     * Check if pow is an integer
     */
    public static boolean isPowerOfThree(int n) {
        double pow = Math.log10(n) / Math.log10(3);
        return n > 0 && pow == (int) pow;
    }

    /**
     * Iteration
     * --------------------
     * Keep dividing by 3 until n is not divisible by 3
     * If n becomes 1, then it is a power of 3
     */
    public static boolean isPowerOfThree2(int n) {
        if (n <= 0) return false;

        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }
}
