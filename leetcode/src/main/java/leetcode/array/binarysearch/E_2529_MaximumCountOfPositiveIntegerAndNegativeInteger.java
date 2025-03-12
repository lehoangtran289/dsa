package leetcode.array.binarysearch;

public class E_2529_MaximumCountOfPositiveIntegerAndNegativeInteger {
    /**
     * Brute force
     * TC: O(N)
     * SC: O(1)
     */
    public int maximumCount(int[] nums) {
        int neg = 0;
        int pos = 0;
        for (int n : nums) {
            if (n < 0) neg++;
            else if (n > 0) pos++;
        }
        return Math.max(neg, pos);
    }

    /**
     * Binary search since input is sorted
     * TC: O(logN)
     * SC: O(1)
     */
    public int maximumCount1(int[] nums) {
        int maxNegId = findMaxNegId(nums);
        int minPosId = findMinPosId(nums);

        return Math.max(
                maxNegId + 1,
                minPosId == -1 ? 0 : nums.length - minPosId
        );
    }

    private int findMaxNegId(int[] nums) {
        int l = 0, r = nums.length - 1;
        int res = -1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < 0) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return res;
    }

    private int findMinPosId(int[] nums) {
        int l = 0, r = nums.length - 1;
        int res = -1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > 0) {
                res = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return res;
    }
}
