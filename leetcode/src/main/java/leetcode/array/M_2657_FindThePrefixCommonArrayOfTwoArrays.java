package leetcode.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class M_2657_FindThePrefixCommonArrayOfTwoArrays {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findThePrefixCommonArray(new int[]{1, 3, 2, 4}, new int[]{3, 1, 2, 4}))); // 0,2,3,4
    }

    /**
     * O(n^2) using hashSet
     */
    public static int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] res = new int[n];

        Set<Integer> setA = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            setA.add(A[i]);

            int cnt = 0;
            for (int j = 0; j <= i; ++j) {
                if (setA.contains(B[j])) cnt++;
            }
            res[i] = cnt;
        }

        return res;
    }

    /**
     * O(n) Single Pass with Frequency Array
     */
    public static int[] findThePrefixCommonArray2(int[] A, int[] B) {
        int n = A.length;
        int[] res = new int[n];
        int[] freq = new int[n + 1];

        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            freq[A[i]]++;
            if (freq[A[i]] == 2) cnt++;

            freq[B[i]]++;
            if (freq[B[i]] == 2) cnt++;

            res[i] = cnt;
        }

        return res;
    }
}
