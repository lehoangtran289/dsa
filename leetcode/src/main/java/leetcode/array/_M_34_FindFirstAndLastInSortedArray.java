package leetcode.array;

public class _M_34_FindFirstAndLastInSortedArray {
    public int first(int[] nums, int target) {
        int ans = -1;
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                ans = mid;
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    public int last(int[] nums, int target) {
        int ans = -1;
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                ans = mid;
                low = mid + 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1 , -1};

        int lo = 0, hi = nums.length - 1;
        int index = -1;
        while (lo <= hi) {
            int mid = hi - (hi - lo) / 2;
            if (nums[mid] == target) {
                index = mid;
                break;
            } else if (nums[mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        if (index == -1) return new int[]{-1 , -1};

        int first = index, last = index;
        while (first >= 0 && nums[first] == target) {
            first--;
        }
        while (last < nums.length && nums[last] == target) {
            last++;
        }

        return new int[]{first + 1, last - 1};
    }
}
