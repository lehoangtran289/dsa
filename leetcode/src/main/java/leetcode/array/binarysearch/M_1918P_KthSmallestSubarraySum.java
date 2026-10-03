package leetcode.array.binarysearch;

public class M_1918P_KthSmallestSubarraySum {

    static void main() {
        System.out.println(kthSmallestSubarraySum(new int[]{1, 2, 3, 4}, 2)); // 2
    }

    /**
     * Idea: k-th smallest subarray sum ~ at a given sum, there are k smaller subarray sums
     * Since nums[i] > 0, increasing "target sum" would increase number of subarray sums < "target sum"
     * => Use binary search to find the smallest "target sum" such that at least k subarray sums are <= "target sum" ~ count(target sum) >= k
     * - How to find number of subarray sums < target sum? 2 pointers -> find max segment with sum < target sum ending at i-th -> #subarrays = r - l + 1
     * ---
     * TC: O(n * logn)
     */
    public static int kthSmallestSubarraySum(int[] nums, int k) {
        int res = 0;
        int l = 1, r = 1 << 30;

        while (l <= r) {
            int mid = (l + r) >>> 1;
            int count = countSubarraysWithSmallerSum(nums, mid);

            if (count >= k) {
                res = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return res;
    }

    private static int countSubarraysWithSmallerSum(int[] nums, int targetSum) {
        int res = 0;

        int curSum = 0;
        int l = 0;
        for (int r = 0; r < nums.length; ++r) {
            curSum += nums[r];

            while (curSum > targetSum) {
                curSum -= nums[l++];
            }

            res += r - l + 1;
        }
        return res;
    }
}
