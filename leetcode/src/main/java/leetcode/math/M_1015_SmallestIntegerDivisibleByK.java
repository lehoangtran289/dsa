package leetcode.math;

public class M_1015_SmallestIntegerDivisibleByK {
    /**
     * since 111...11 | k, k cannot be 2 & 5
     * Using euler theorem: k & 10 coprime
     * => 10^phi(k) = 1 mod k
     * <=> 10^phi(9k) = 1 mod 9k
     * <=> 10^phi(9k) - 1 = 9999..999 = 9k * x
     * => 111...111 = k * x
     * Therefore there exists 11...111 that divisible by k
     */
    public int smallestRepunitDivByK(int k) {
        if (k == 1) return 1;
        if ((k & 1) == 0 || k % 5 == 0) return -1;

        int remainder = 1, length = 1;
        while (remainder != 0) {
            remainder = (remainder * 10 + 1) % k;
            length++;
        }

        return length;
    }
}
