package leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class M_1726_TupleWithSameProduct {
    public static void main(String[] args) {
        System.out.println(tupleSameProduct(new int[]{2,3,4,6,8,12})); // 40
        System.out.println(tupleSameProduct(new int[]{1, 2, 4, 5, 10})); // 16
    }

    /**
     * 2 -> 8 = 2 * (2 - 1) * 4
     * 3 -> 24 = 3 * (3 - 1) * 4
     */
    public static int tupleSameProduct(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> productFreq = new HashMap<>();

        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                 int prod = nums[i] * nums[j];
                 productFreq.put(prod, productFreq.getOrDefault(prod, 0) + 1);
            }
        }

        int res = 0;
        for (Map.Entry<Integer, Integer> entry : productFreq.entrySet()) {
            int freq = entry.getValue();
            res += freq * (freq - 1) * 4;
        }

        return res;
    }
}
