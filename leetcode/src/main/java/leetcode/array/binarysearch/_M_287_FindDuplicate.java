package leetcode.array.binarysearch;

public class _M_287_FindDuplicate {
    public static void main(String[] args) {
        _M_287_FindDuplicate findDuplicate = new _M_287_FindDuplicate();
        System.out.println(findDuplicate.findDuplicate(new int[]{1, 3, 4, 2, 2}));
        System.out.println(findDuplicate.findDuplicate(new int[]{3, 1, 3, 4, 2}));
    }

    /**
     * Count the number of elements that are less than or equal to mid. <br/>
     * If the count is less than or equal to mid, the duplicate element must be in the right half. <br/>
     * Otherwise, the duplicate element must be in the left half.
     */
    public int findDuplicate(int[] nums) {
        int lo = -1, hi = nums.length - 1;

        while (lo + 1 < hi) {
            int mid = hi - (hi - lo) / 2;

            int count = 0;
            for (int n : nums) {
                if (n <= mid) count++;
            }

            if (count <= mid) {
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return hi;
    }
}
