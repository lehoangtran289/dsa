package leetcode.array;

import java.util.Arrays;

public class M_2657_FindThePrefixCommonArrayOfTwoArrays {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findThePrefixCommonArray(new int[]{1, 3, 2, 4}, new int[]{3, 1, 2, 4}))); // 0,2,3,4
    }

    /**
     * O(n) Single Pass with Frequency Array
     */
    public static int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] res = new int[n];
        int[] freq = new int[n + 1];

        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (freq[A[i]] == 1) cnt++; // Element already appeared so it's common
            if (freq[B[i]] == 1) cnt++;
            if (A[i] == B[i]) cnt++;

            freq[A[i]]++;
            freq[B[i]]++;
            res[i] = cnt;
        }

        return res;
    }
}
