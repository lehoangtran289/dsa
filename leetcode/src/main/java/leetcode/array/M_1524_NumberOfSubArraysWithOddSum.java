package leetcode.array;

public class M_1524_NumberOfSubArraysWithOddSum {
    public static void main(String[] args) {
        System.out.println(numOfSubarrays(new int[]{1, 3, 5})); // 4
        System.out.println(numOfSubarrays(new int[]{2, 4, 6})); // 0
    }

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
