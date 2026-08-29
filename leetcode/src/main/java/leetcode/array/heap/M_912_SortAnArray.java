package leetcode.array.heap;

import java.util.Arrays;

public class M_912_SortAnArray {
    static void main() {
        System.out.println(Arrays.toString(sortArray(new int[]{5, 2, 3, 1})));
    }

    /**
     * Merge sort
     * ---
     * TC: O(N logN), each divide = logN
     * SC: O(N)
     */
    public static int[] sortArray(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private static int[] mergeSort(int[] nums, int start, int end) {
        if (start > end) return new int[]{};
        if (start == end) return new int[]{nums[start]};

        int mid = (start + end) / 2;

        int[] left = mergeSort(nums, start, mid);
        int[] right = mergeSort(nums, mid + 1, end);

        return merge(left, right);
    }

    /**
     * Merge 2 sorted arrays
     */
    private static int[] merge(int[] left, int[] right) {
        int leftLength = left.length, rightLength = right.length;
        int[] res = new int[leftLength + rightLength];
        int i = 0;

        // 2 pointers to merge 2 sorted arrays
        int p1 = 0, p2 = 0;
        while (p1 < leftLength && p2 < rightLength) {
            if (left[p1] <= right[p2]) {
                res[i++] = left[p1++];
            } else {
                res[i++] = right[p2++];
            }
        }

        while (p1 < leftLength) {
            res[i++] = left[p1++];
        }

        while (p2 < rightLength) {
            res[i++] = right[p2++];
        }

        return res;
    }

    // ----------------------------------------------------------------------

    /**
     * Quick sort
     * ---
     * TC: O(N logN), worst case O(N^2)
     * SC: O(N)
     */
    public static int[] sortArray2(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private static void quickSort(int[] nums, int start, int end) {
        if (start >= end) return;

        int pivot = partition(nums, start, end);
        quickSort(nums, start, pivot - 1);
        quickSort(nums, pivot + 1, end);
    }

    private static int partition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int i = start - 1; // boundary of elements < pivot

        for (int j = start; j <= end - 1; ++j) {
            if (nums[j] < pivot) {
                i++; // advance boundary
                swap(nums, i, j);
            }
        }

        // swap pivot to its place
        swap(nums, i + 1, end);
        return i + 1;
    }

    // ----------------------------------------------------------------

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
