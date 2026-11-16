package leetcode.array.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class M_GFG_SubarrayWithGivenXOR {
    static void main() {
        System.out.println(subarrayXor(new int[]{4, 2, 2, 6, 4}, 6)); // 4
        System.out.println(subarrayXor(new int[]{5, 6, 7, 8, 9}, 5)); // 2
        System.out.println(subarrayXor(new int[]{15, 11, 6, 17, 9, 16, 16, 19, 11, 13, 14}, 6)); // 4
    }

    /**
     * Idea: prefix XOR, since complement = target ^ curXOR
     * -> Maintain a map of prefix XOR count
     * Traverse for each element in A, we calculate prefixXOR and check if there exists its complement -> add to result
     * ---
     * TC: O(N)
     * SC: O(N)
     */
    public static long subarrayXor(int arr[], int k) {
        // code here
        Map<Integer, Integer> xorCountMap = new HashMap<>();
        int curXor = 0;
        long res = 0;

        for (int num : arr) {
            curXor ^= num;

            // for subarray starting from 0
            if (curXor == k) res++;

            int complement = k ^ curXor;
            if (xorCountMap.containsKey(complement)) {
                res += xorCountMap.get(complement);
            }

            // update frequency map
            xorCountMap.put(curXor, xorCountMap.getOrDefault(curXor, 0) + 1);

        }

        return res;
    }
}
