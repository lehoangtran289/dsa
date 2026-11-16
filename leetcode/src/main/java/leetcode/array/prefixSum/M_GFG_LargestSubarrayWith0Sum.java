package leetcode.array.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class M_GFG_LargestSubarrayWith0Sum {
    static void main() {
        System.out.println(maxLength(new int[]{15, -2, 2, -8, 1, 7, 10, 23})); // 5
        System.out.println(maxLength(new int[]{2, 10, 4})); // 0
        System.out.println(maxLength(new int[]{1, 0, -4, 3, 1, 0})); // 5
    }

    public static int maxLength(int[] arr) {
        Map<Integer, Integer> sumIndexMap = new HashMap<>();
        int res = 0;
        int prefixSum = 0;

        // base case for subarray starting from 0
        sumIndexMap.put(0, -1);

        for (int i = 0; i < arr.length; ++i) {
            prefixSum += arr[i];

            if (sumIndexMap.containsKey(prefixSum)) {
                res = Math.max(res, i - sumIndexMap.get(prefixSum));
            }

            if (!sumIndexMap.containsKey(prefixSum)) {
                sumIndexMap.put(prefixSum, i);
            }
        }

        return res;
    }
}
