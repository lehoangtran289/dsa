package leetcode.array.binarysearch;

public class _M_81_SearchInRotatedSortedArrayII {
    public static void main(String[] args) {
        _M_81_SearchInRotatedSortedArrayII search = new _M_81_SearchInRotatedSortedArrayII();
        System.out.println(search.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 0)); // true
        System.out.println(search.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 3)); // false
        System.out.println(search.search(new int[]{1, 3, 1, 1, 1}, 3)); // true
    }

    public boolean search(int[] nums, int target) {
        if (nums == null) return false;

        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            System.out.println(lo + " " + mid + " " + hi);

            if (nums[mid] == target) {
                return true;
            }

            // handle duplicate end and start
            while (nums[mid] == nums[lo] && mid != lo) {
                lo++;
            }
            while (nums[mid] == nums[hi] && mid != hi) {
                hi--;
            }

            // same as prev problem
            if (nums[lo] <= nums[mid]) {
                if (target >= nums[lo] && target < nums[mid]) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
        }
        return nums[lo] == target;
    }
}
