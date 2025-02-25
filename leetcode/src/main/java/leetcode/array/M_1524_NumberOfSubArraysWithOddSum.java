package leetcode.array;

public class M_1524_NumberOfSubArraysWithOddSum {
    public static void main(String[] args) {
        System.out.println(numOfSubarrays(new int[]{1, 3, 5})); // 4
        System.out.println(numOfSubarrays(new int[]{2, 4, 6})); // 0
    }

    /**
     * We can make a crucial observation: </br>
     * - If two prefix sums have the same parity, their difference will be even, meaning the subarray sum is even.</br>
     * - If two prefix sums have different parity, their difference will be odd, meaning the subarray sum is odd.</br>
     * ==> This leads to an efficient way to count odd subarrays as we traverse the array. </br>
     * <p>
     * We maintain a cumulative prefixSum while keeping track of how many times we've seen an even or odd prefix sum before the current index.</br>
     * <p>
     * As we process each element:</br>
     * - If prefixSum is even, it means the subarray sum from the start to the current index is even. </br>
     *      + To form an odd subarray, we need to subtract a previously seen odd prefix sum. </br>
     *      + So, we add the count of previously seen odd prefix sums to our answer. </br>
     *  <p>
     * - If prefixSum is odd, the subarray sum from the start to the current index is odd.  </br>
     *      + To form another odd subarray, we need to subtract a previously seen even prefix sum. </br>
     *      + So, we add the count of previously seen even prefix sums to our answer. </br>
     */
    public static int numOfSubarrays(int[] arr) {
        int modulo = (int) (Math.pow(10, 9) + 7);

        int res = 0;
        int prefixSum = 0;
        int oddSumCount = 0;
        int evenSumCount = 0;

        for (int num : arr) {
            prefixSum += num;

            if (prefixSum % 2 == 1) { // odd prefix sum
                oddSumCount++;
                // add current sub array since it is odd (1) + all even-prefix-sum count up to this num (eventSumCount)
                res += 1 + evenSumCount;
            } else { // even
                evenSumCount++;
                // add odd-prefix-sum count up to this num (oddSumCount) since cur is even
                res += oddSumCount;
            }

            res = res % modulo;

        }

        return res;
    }
}
