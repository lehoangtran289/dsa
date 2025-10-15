package leetcode.array.binarysearch;

public class H_4_MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2})); // 2.0
    }

    /**
     * Simulation: Merged Sort
     * ----------------------
     * TC: O(n + m)
     * SC: O(n + m)
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        // merged sort
        int[] merged = new int[n + m];
        int curIndex = 0, i = 0, j = 0;

        while (i < n && j < m) {
            merged[curIndex++] = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
        }
        while (i < n) merged[curIndex++] = nums1[i++];
        while (j < m) merged[curIndex++] = nums2[j++];

        // return median of the merged array
        if (merged.length % 2 == 0) {
            int mid1 = merged[merged.length / 2];
            int mid2 = merged[merged.length / 2 - 1];
            return (double) (mid1 + mid2) / 2;
        }
        return merged[merged.length / 2];
    }

    /**
     * Binary Search
     * --------------
     * TC: O(log(min(n, m)))
     * SC: O(1)
     */
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        // ensure nums1 is the smaller array
        if (n > m) {
            return findMedianSortedArrays2(nums2, nums1);
        }

    }
}
