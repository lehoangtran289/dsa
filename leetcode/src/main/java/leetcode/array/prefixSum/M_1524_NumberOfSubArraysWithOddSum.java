package leetcode.array.prefixSum;

public class M_1524_NumberOfSubArraysWithOddSum {
    static void main() {
        System.out.println(numOfSubarrays(new int[]{1, 3, 5})); // 4
        System.out.println(numOfSubarrays(new int[]{2, 4, 6})); // 0
    }

    /**
     * Idea: Use vars to store frequencies of prefix sums
     * - If at index i-th we have EVEN current prefix sum -> looks for total number of ODD prefix sums
     * - If at index i-th we have ODD current prefix sum -> looks for total number of EVEN prefix sums
     * ---
     * TC: O(N)
     * SC: O(1)
     */
    public static int numOfSubarrays(int[] arr) {
        final int MOD = (int) 1e9 + 7;
        int oddSubarrayCount = 0, evenSubarrayCount = 0;
        int prefixSum = 0;
        int res = 0;

        for (int num : arr) {
            prefixSum += num;
            if ((prefixSum & 1) == 0) {
                res = (res + oddSubarrayCount) % MOD;
                evenSubarrayCount++;
            } else {
                res = (res + evenSubarrayCount + 1) % MOD;
                oddSubarrayCount++;
            }
        }

        return res;
    }
}
