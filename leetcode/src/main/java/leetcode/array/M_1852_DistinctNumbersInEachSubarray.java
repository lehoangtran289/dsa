package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class M_1852_DistinctNumbersInEachSubarray {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(distinctNumbers(new int[]{1, 2, 3, 2, 2, 1, 3}, 3)));
    }

    public static int[] distinctNumbers(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];

        // init first k array
        int curId = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < k; ++i) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }
        res[curId++] = freq.size();

        // traverse the rest of the array
        for (int i = k; i < n; ++i) {
            // remove nums[i - k]
            int curFreq = freq.get(nums[i - k]);
            if (curFreq == 1) freq.remove(nums[i - k]);
            else freq.put(nums[i - k], curFreq - 1);

            // add nums[i]
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
            res[curId++] = freq.size();
        }

        return res;
    }
}
