package leetcode.array.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class H_480_SlidingWindowMedian {

    /**
     * Use 2 heaps to maintain the left and right halves of the numbers in the sliding window
     * -----
     * TC: O(n log n)
     * SC: O(n)
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] res = new double[n - k + 1];
        int idx = 0;

        Queue<Integer> left = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        Queue<Integer> right = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
        Map<Integer, Integer> invalidNumCnt = new HashMap<>();

        // init first window
        for (int i = 0; i < k; ++i) {
            left.add(nums[i]);
        }
        for (int i = 0; i < k / 2; ++i) {
            right.add(left.poll());
        }

        // track *valid* element counts separately from raw heap sizes,
        // since lazily-invalidated elements can stay buried inside a heap
        int validLeftSize = left.size();
        int validRightSize = right.size();
        res[idx++] = getMedian(left, right, validLeftSize, validRightSize);

        // sliding window
        for (int i = k; i < n; ++i) {
            // remove nums[i - k]
            invalidNumCnt.put(nums[i - k], invalidNumCnt.getOrDefault(nums[i - k], 0) + 1);
            if (!left.isEmpty() && nums[i - k] <= left.peek()) {
                validLeftSize--;
            } else {
                validRightSize--;
            }

            // add nums[i] to the correct queue
            if (!left.isEmpty() && left.peek() >= nums[i]) {
                left.add(nums[i]);
                validLeftSize++;
            } else {
                right.add(nums[i]);
                validRightSize++;
            }

            // rebalance 2 queues
            if (validLeftSize > validRightSize + 1) {
                right.add(left.poll());
                validLeftSize--;
                validRightSize++;
            } else if (validRightSize > validLeftSize + 1) {
                left.add(right.poll());
                validRightSize--;
                validLeftSize++;
            }

            // clean up invalid numbers from the top of the heaps
            while (!left.isEmpty() && invalidNumCnt.containsKey(left.peek())) {
                int invalidNum = left.poll();
                removeFromMap(invalidNumCnt, invalidNum);
            }

            while (!right.isEmpty() && invalidNumCnt.containsKey(right.peek())) {
                int invalidNum = right.poll();
                removeFromMap(invalidNumCnt, invalidNum);
            }

            // get median
            res[idx++] = getMedian(left, right, validLeftSize, validRightSize);
        }

        return res;
    }

    private void removeFromMap(Map<Integer, Integer> map, int target) {
        map.put(target, map.get(target) - 1);
        if (map.get(target) == 0) {
            map.remove(target);
        }
    }

    private double getMedian(
            Queue<Integer> left,
            Queue<Integer> right,
            int leftSize,
            int rightSize
    ) {
        if (leftSize == rightSize) {
            return ((long) left.peek() + right.peek()) / 2.0;
        }
        return leftSize > rightSize ? left.peek() : right.peek();
    }
}
