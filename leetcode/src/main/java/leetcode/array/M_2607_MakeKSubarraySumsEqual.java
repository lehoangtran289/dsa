package leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class M_2607_MakeKSubarraySumsEqual {
    public static void main(String[] args) {
        System.out.println(makeSubKSumEqual(new int[]{1, 4, 1, 3}, 2)); // Output: 1
    }

    /**
     * Idea: make i-th and (i + k)-th element equal so that sub-array will be equal.
     * -> collect list of i-th ... (i + k)-th element. Then calculate diff of each element to its median.
     * --------
     * gcd(n, k) is needed to find number of distinct groups.
     * ex: n = 6, k = 2 -> 2 groups {0, 2, 4} and {1, 3, 5}
     */
    public static long makeSubKSumEqual(int[] arr, int k) {
        int n = arr.length;
        int subarrays = gcd(n, k);
        long res = 0;

        for (int i = 0; i < subarrays; ++i) {
            List<Integer> group = new ArrayList<>();

            for (int j = i; j < n; j += subarrays) {
                group.add(arr[j]); // collect i-th number
            }

            Collections.sort(group);
            int median = group.get(group.size() / 2);

            for (int num : group) {
                res += Math.abs(num - median);
            }
        }

        return res;
    }

    // gcd(8, 12) = gcd(12, 8) = gcd(8, 4) = gcd(4, 0) = 4
    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
