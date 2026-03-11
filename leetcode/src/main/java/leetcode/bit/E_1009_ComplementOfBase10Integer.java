package leetcode.bit;

public class E_1009_ComplementOfBase10Integer {

    /**
     * Idea: complement of n + n = 111...111 (a number with all bits set to 1)
     * ---
     * Time: O(log n) - we need to find the number of bits in n
     * Space: O(1)
     */
    public int bitwiseComplement(int n) {
        int ones = 1;
        while (ones < n) {
            ones = (ones << 1) | 1; // construct 111....111
        }
        return ones - n;
    }

    /**
     * Idea: flip bits one by one until n becomes 0
     * flip operation: n ^= mask, where mask is 1, 10, 100, ...
     * ---
     * Time: O(log n) - we need to find the number of bits in n
     * Space: O(1)
     */
    public int bitwiseComplement1(int n) {
        if (n == 0) return 1;

        int steps = n, mask = 1;
        while (steps != 0) {
            n ^= mask;
            mask <<= 1;
            steps >>= 1;
        }

        return n;
    }
}
