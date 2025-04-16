package leetcode.array.twopointers;

import java.util.HashMap;
import java.util.Map;

public class M_2537_CountTheNumberOfGoodSubarrays {
    public static void main(String[] args) {
        System.out.println(countGood(new int[]{1, 1, 1, 1, 1}, 10)); // 1
    }

    public static long countGood(int[] nums, int k) {
        int n = nums.length;

        Map<Integer, Integer> freq = new HashMap<>();
        int curPairs = 0;
        int r = 0;
        long res = 0;

        for (int l = 0; l < n; ++l) {
            // move right pointer to find first good subarray
            while (r < n && curPairs < k) {
                freq.put(nums[r], freq.getOrDefault(nums[r], 0) + 1);

                int numFreq = freq.get(nums[r]);
                if (numFreq > 1) {
                    curPairs += numFreq - 1;
                }
                ++r;
            }

            // check if curPairs >= k -> count as good subarray
            if (curPairs >= k) res += n - r + 1;

            // increase left pointer and update curPairs
            freq.put(nums[l], freq.get(nums[l]) - 1);
            if (freq.get(nums[l]) > 0) {
                curPairs -= freq.get(nums[l]);
            } else {
                freq.remove(nums[l]);
            }
        }

        return res;
    }
}
