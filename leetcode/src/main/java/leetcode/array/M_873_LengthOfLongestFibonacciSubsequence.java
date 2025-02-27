package leetcode.array;

import java.util.HashSet;
import java.util.Set;

public class M_873_LengthOfLongestFibonacciSubsequence {
    public static void main(String[] args) {
        System.out.println(lenLongestFibSubseq(new int[]{1, 3, 5})); // 0
        System.out.println(lenLongestFibSubseq(new int[]{1, 2, 3, 4, 5, 6, 7, 8})); // 5
        System.out.println(lenLongestFibSubseq(new int[]{1, 3, 7, 11, 12, 14, 18})); // 3
    }

    /**
     * Bruteforce approach. Try every start pair in the array
     * TC: O(n^2 * log(max(arr)))
     */
    public static int lenLongestFibSubseq(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) set.add(num);

        int maxLen = 0;
        for (int i = 0; i < arr.length - 1; ++i) {
            for (int j = i + 1; j < arr.length; ++j) {
                int curLen = 2;
                int num1 = arr[i];
                int num2 = arr[j];

                while (set.contains(num1 + num2)) {
                    curLen++;
                    int temp = num1;
                    num1 = num2;
                    num2 = temp + num2;
                }

                // fibonacci subarray start with 3 elements
                if (curLen > 2) {
                    maxLen = Math.max(maxLen, curLen);
                }
            }
        }

        return maxLen;
    }
}
