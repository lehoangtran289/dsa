package leetcode.array;

import java.util.*;

public class M_3350_AdjacentIncreasingSubarraysDetectionII {

    /**
     * 1 pass counting
     * Idea: count the length of increasing subarray, when encounter a non-increasing element,
     * store the previous count, reset current count to 1, and calculate the result
     * -----------------------
     * TC: O(n)
     * SC: O(1)
     */
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        int count = 1, prevCount = 1;
        int res = 0;

        for (int i = 0; i < n - 1; ++i) {
            if (nums.get(i) < nums.get(i + 1)) {
                count++;
            } else {
                prevCount = count;
                count = 1;
            }

            res = Math.max(res, Math.max(
                    Math.min(prevCount, count), count / 2));
        }

        return res;
    }

    /**
     * Binary Search + 2 Pointers
     * Idea: binary search the length of subarray k,
     * and use 2 pointers to check if we can split the array into k increasing subarrays
     * -----------------------
     * TC: O(n log n)
     * SC: O(1)
     */
    public int maxIncreasingSubarrays2(List<Integer> nums) {
        int n = nums.size();
        int l = 1, r = n / 2;
        int res = 1;

        while (l <= r) {
            int mid = (l + r) / 2;

            if (canSplit(nums, mid)) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return res;
    }

    private boolean canSplit(List<Integer> nums, int k) {
        int n = nums.size();
        int p1 = 0, p2 = k;
        int count = 1;

        while (p2 < n) {
            if (count == k) return true;

            if (
                    nums.get(p1) < nums.get(p1 + 1)
                    && (p2 == n - 1 || nums.get(p2) < nums.get(p2 + 1))
            ) {
                count++;
            } else {
                count = 1;
            }

            p1++;
            p2++;
        }

        return false;
    }
}
