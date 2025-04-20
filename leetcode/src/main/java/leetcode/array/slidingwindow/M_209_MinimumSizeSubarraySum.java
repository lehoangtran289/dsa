package leetcode.array.slidingwindow;

public class M_209_MinimumSizeSubarraySum {
    /**
     * Sliding window
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int res = n + 1;
        int l = 0;
        int sum = 0;

        for (int r = 0; r < n; ++r) {
            sum += nums[r];

            while (l <= r && sum >= target) {
                sum -= nums[l];
                l++;
                res = Math.min(res, r - l + 2);
            }
        }

        return res == n + 1 ? 0 : res;
    }

    /**
     * Binary search
     * Time complexity: O(n * log(n))
     * Space complexity: O(1)
     */
    public int minSubArrayLen2(int target, int[] nums) {
        int n = nums.length;
        int res = n + 1;
        int l = 0, r = n;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (isValid(target, nums, mid)) {
                res = Math.min(res, mid);
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return res == n + 1 ? 0 : res;
    }

    private boolean isValid(int target, int[] nums, int size) {
        int sum = 0;

        // process first <size> elements
        for (int i = 0; i < size; ++i) {
            sum += nums[i];
            if (sum >= target) return true;
        }

        for (int i = size; i < nums.length; ++i) {
            sum += nums[i];
            sum -= nums[i - size];
            if (sum >= target) return true;
        }

        return false;
    }
}
