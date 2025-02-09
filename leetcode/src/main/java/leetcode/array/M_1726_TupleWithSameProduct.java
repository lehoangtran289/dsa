package leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class M_1726_TupleWithSameProduct {
    public static void main(String[] args) {
        System.out.println(tupleSameProduct(new int[]{2, 3, 4, 6, 8, 12})); // 40
        System.out.println(tupleSameProduct(new int[]{1, 2, 4, 5, 10})); // 16
    }

    /**
     * 2 -> 8 = 2 * (2 - 1) * 4
     * 3 -> 24 = 3 * (3 - 1) * 4
     */
    public static int tupleSameProduct(int[] nums) {
        Map<Integer, Integer> productFreq = new HashMap<>();

        for (int i = 0; i < nums.length - 1; ++i) {
            for (int j = i + 1; j < nums.length - 1; ++j) {
                int prod = nums[i] * nums[j];
                productFreq.put(prod, productFreq.getOrDefault(prod, 0) + 1);
            }
        }

        int res = 0;
        for (Map.Entry<Integer, Integer> entry : productFreq.entrySet()) {
            if (entry.getValue() >= 2)
                res += numberOfPairs(entry.getValue()) * 8;
        }

        return res;
    }

    /**
     * k_C_n = n! / (k! * (n - k)!)
     */
    private static int numberOfPairs(int n) {
        return (int) (factorial(n) / (factorial(n - 2) * factorial(2)));
    }

    private static long factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }
}
