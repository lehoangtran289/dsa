package leetcode.array;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * Order Statistic Problem
 * ------------------------
 * Find the k-th smallest/largest element in an array
 * ------------------------
 */
public class _0_OrderStatisticProblem {

    public static void main(String[] args) {
        System.out.println(new _0_OrderStatisticProblem().findKthSmallest2(
                new int[]{3, 2, 1, 5, 5, 4, 4},
                4
        )); // 4
    }

    /**
     * Quick Select Algorithm
     * ------------------------
     * TC: O(n) on average, O(n^2) in the worst case
     * SC: O(1)
     */
    public int findKthSmallest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k - 1);
    }

    private int quickSelect(int[] nums, int l, int r, int k) {
        if (l == r) return nums[l];

        int pivot = randomPartition(nums, l, r);
        int pivotRank = pivot - l;

        if (pivotRank == k) {
            return nums[pivot];
        } else if (pivotRank > k) {
            return quickSelect(nums, l, pivot - 1, k); // search left
        } else {
            return quickSelect(nums, pivot + 1, r, k - pivotRank - 1); // search right
        }
    }

    private int randomPartition(int[] nums, int l, int r) {
        int pivotIndex = l + new Random().nextInt(r - l);
        int pivotValue = nums[pivotIndex];

        // swap pivot to end
        swap(nums, pivotIndex, r);

        int i = l;

        for (int j = l; j < r; ++j) {
            if (nums[j] < pivotValue) {
                swap(nums, i, j);
                i++;
            }
        }

        // swap pivot to its final place
        swap(nums, i, r);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * Heap-based Approach
     * ------------------------
     * TC: O(n log k)
     * SC: O(k)
     */
    public int findKthSmallest2(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        for (int num : nums) {
            maxHeap.add(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        return !maxHeap.isEmpty() ? maxHeap.peek() : -1;
    }
}
