package leetcode.array.slidingwindow;

import java.util.TreeMap;

public class H_3013_DivideAnArrayIntoSubarraysWithMinimumCostII {

    /**
     * Sliding Window + Two Sorted Partitions (TreeMap)
     * -----------------------------
     * - Within a window, we keep two balanced partitions using TreeMaps:
     * - Left partition (k smallest elements)
     * - Right partition (remaining elements)
     * - As we slide the window, we add the new element to the appropriate partition and
     * remove the outgoing element, rebalancing the partitions as necessary to ensure
     * that the left partition always contains exactly k smallest elements.
     * -----------------------------
     * - Time Complexity: O(n log(dist)) due to TreeMap operations for each of the n elements.
     * - Space Complexity: O(dist) for storing elements in the two partitions.
     */
    private final TreeMap<Integer, Integer> left = new TreeMap<>(); // k smallest elements
    private final TreeMap<Integer, Integer> right = new TreeMap<>(); // remaining elements
    private long curSum; // sum in each window
    private int leftSize; // maintain exactly k element in the left partition

    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        k--; // exclude nums[0]
        curSum = nums[0];

        // add first window to left partition and balance it
        for (int i = 1; i <= dist + 1; ++i) {
            curSum += nums[i];
            add(left, nums[i]);
            leftSize++;
        }

        // balance left partition to only k elements
        while (leftSize > k) {
            extractLeftToRight();
        }

        long res = curSum;

        // sliding window
        for (int i = 2; i < n - dist; ++i) {
            int elementToRemove = nums[i - 1];
            int elementToAdd = nums[i + dist];

            if (left.containsKey(elementToRemove)) {
                remove(left, elementToRemove);
                leftSize--;
                curSum -= elementToRemove;
            } else {
                remove(right, elementToRemove);
            }

            // determine which partition to include new element
            if (elementToAdd < left.lastKey()) {
                add(left, elementToAdd);
                leftSize++;
                curSum += elementToAdd;
            } else {
                add(right, elementToAdd);
            }

            // Rebalance partitions to maintain exactly k elements in left
            while (leftSize < k) {
                extractRightToLeft();
            }
            while (leftSize > k) {
                extractLeftToRight();
            }

            // Update minimum answer
            res = Math.min(res, curSum);
        }

        return res;
    }

    // move largest element in left partition to the right partition
    private void extractLeftToRight() {
        int lastKey = left.lastKey();
        curSum -= lastKey;

        remove(left, lastKey);
        leftSize--;
        add(right, lastKey);
    }

    // move smallest element in right partition to the left partition
    private void extractRightToLeft() {
        int firstKey = right.firstKey();
        curSum += firstKey;

        remove(right, firstKey);
        add(left, firstKey);
        leftSize++;
    }

    // HashMap helpers
    private void add(TreeMap<Integer, Integer> map, int element) {
        map.put(element, map.getOrDefault(element, 0) + 1);
    }

    private void remove(TreeMap<Integer, Integer> map, int element) {
        map.put(element, map.getOrDefault(element, 0) - 1);
        if (map.get(element) <= 0) map.remove(element);
    }
}
