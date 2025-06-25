package leetcode.bit;

public class E_231_PowerOfTwo {
    public static void main(String[] args) {
        System.out.println(new E_231_PowerOfTwo().isPowerOfTwo(1)); // true
        System.out.println(new E_231_PowerOfTwo().isPowerOfTwo(2)); // true
        System.out.println(new E_231_PowerOfTwo().isPowerOfTwo(3)); // false
        System.out.println(new E_231_PowerOfTwo().isPowerOfTwo(4)); // true
        System.out.println(new E_231_PowerOfTwo().isPowerOfTwo(5)); // false
        System.out.println(new E_231_PowerOfTwo().isPowerOfTwo(16)); // true
    }

    /**
     * Check if a number is a power of two
     * Idea: n has only MSB bit set
     * Example:
     * - 1 = 0001 (2^0)
     * - 2 = 0010 (2^1)
     * - 4 = 0100 (2^2)
     * - 8 = 1000 (2^3)
     *
     * --> n - 1 = mask for all bits below MSB
     * Example:
     * - 3 = 011
     * - 7 = 0111
     */
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return (n & (n - 1)) == 0;
    }

    public boolean isPowerOfTwo2(int n) {
        int pow = 0;

        while (pow <= 32) {
            if (Math.pow(2, pow) == n) return true;
            pow++;
        }

        return false;
    }
}
