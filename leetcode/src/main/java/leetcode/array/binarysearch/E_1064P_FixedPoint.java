package leetcode.array.binarysearch;

public class E_1064P_FixedPoint {

    /**
     * Binary search
     * ---
     * Check l <= r: if l == r, Check the last single element
     * Check l < r: Skip last single element, you have to manually check it
     * ---
     * TC: O(log n) binary search
     * SC: O(1)
     */
    public int fixedPoint(int[] arr) {
        int res = -1;
        int l = 0, r = arr.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (arr[mid] == mid) {
                res = mid;
                r = mid - 1;
            } else if (arr[mid] > mid) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return res;
    }
}
