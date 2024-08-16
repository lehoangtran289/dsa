package leetcode.array.binarysearch;

public class _H_410_SplitArrayLargestSum {
    public static void main(String[] args) {
        _H_410_SplitArrayLargestSum splitArrayLargestSum = new _H_410_SplitArrayLargestSum();
        int[] nums = {7, 2, 5, 10, 8};
        int k = 2;
        System.out.println(splitArrayLargestSum.splitArray(nums, k));
    }

    /**
     * After the binary search terminates, <br>
     * the variable lo will hold the smallest value that satisfies the condition of splitting the array into exactly k or fewer subarrays. <br>
     * This value is the minimum largest sum across all possible ways of splitting the array into k subarrays. <br>
     *
     * The reason we return `lo` is that it is the point where we find the minimum possible largest sum after the binary search has converged.
     */
    public int splitArray(int[] nums, int k) {
        int lo = -1;
        int hi = 0;
        for (int i = 0; i < nums.length; ++i) {
            lo = Math.max(nums[i], lo);
            hi += nums[i];
        }

        while (lo <= hi) {
            int mid = hi + (lo - hi) / 2;
            if (countSplits(nums, mid) <= k) { // the array chunk too little (fewer sub arrays) -> minimize sum
                hi = mid - 1;
            } else { // the array chunk too much, we need to increase the sum -> increase sum
                lo = mid + 1;
            }
        }
        return lo;
    }

    private int countSplits(int[] nums, int target) {
        int sum = 0;
        int count = 1;
        for (int i = 0; i < nums.length; ++i) {
            if (sum + nums[i] <= target) {
                sum += nums[i];
            } else {
                count++;
                sum = nums[i];
            }
        }
        return count;
    }

}
