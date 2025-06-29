package leetcode.math;

public class E_342_PowerOfFour {
    public static void main(String[] args) {
        System.out.println(isPowerOfFour(64)); // true
    }

    /**
     * Math
     * --------------------
     * x = 4 ^ pow
     * pow = log4(x) => pow = log10(x) / log10(4)
     * Check if pow is an integer
     */
    public static boolean isPowerOfFour(int n) {
        double pow = Math.log10(n) / Math.log10(4);
        return n > 0 && pow == (int) pow;
    }

    /**
     * Iteration
     */
    public static boolean isPowerOfFour2(int n) {
        if (n <= 0) return false;

        while (n % 4 == 0) {
            n /= 4;
        }
        return n == 1;
    }
}
