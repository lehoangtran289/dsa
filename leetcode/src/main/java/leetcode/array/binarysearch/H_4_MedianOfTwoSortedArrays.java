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
        if (nums1.length > nums2.length) return findMedianSortedArrays2(nums2, nums1);

        int n1 = nums1.length, n2 = nums2.length;
        int total = n1 + n2;
        int target = (total + 1) / 2; // target is the number of elements in the left partition

        int l = 0, r = n1;
        while (l <= r) {
            int take1 = l + (r - l) / 2; // number of elements to take from nums1
            int take2 = target - take1; // number of elements to take from nums2

            int maxLeft1 = take1 == 0 ? Integer.MIN_VALUE : nums1[take1 - 1]; // max of left partition from nums1
            int minRight1 = take1 == n1 ? Integer.MAX_VALUE : nums1[take1]; // min of right partition from nums1
            int maxLeft2 = take2 == 0 ? Integer.MIN_VALUE : nums2[take2 - 1]; // max of left partition from nums2
            int minRight2 = take2 == n2 ? Integer.MAX_VALUE : nums2[take2]; // min of right partition from nums2

            if (maxLeft1 > minRight2) {
                r = take1 - 1;
                continue;
            }

            if (minRight1 < maxLeft2) {
                l = take1 + 1;
                continue;
            }

            // found the correct partition
            if (total % 2 == 1) {
                return Math.max(maxLeft1, maxLeft2);
            } else {
                return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
            }
        }

        return 0.0;
    }
}
