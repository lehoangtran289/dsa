package leetcode.array.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class H_480_SlidingWindowMedian {
    private final Queue<Integer> leafHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
    private final Queue<Integer> rightHeap = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
    private final Map<Integer, Integer> invalidNumCnt = new HashMap<>();

    // track *valid* element counts separately from raw heap sizes,
    // since lazily-invalidated elements can stay buried inside a heap
    private int leftSize = leafHeap.size();
    private int rightSize = rightHeap.size();

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

        // init first window of size k
        for (int i = 0; i < k; ++i) {
            leafHeap.add(nums[i]);
        }
        for (int i = 0; i < k / 2; ++i) {
            rightHeap.add(leafHeap.poll());
        }
        leftSize = leafHeap.size();
        rightSize = rightHeap.size();
        res[idx++] = getMedian();

        // sliding window
        for (int i = k; i < n; ++i) {
            // remove nums[i - k] and add nums[i] to the correct heap, then rebalance 2 heaps
            remove(nums[i - k]);
            add(nums[i]);
            rebalance();

            // clean up invalid numbers from the top of the heaps
            prune(leafHeap);
            prune(rightHeap);

            // get median
            res[idx++] = getMedian();
        }

        return res;
    }

    private void remove(int num) {
        invalidNumCnt.put(num, invalidNumCnt.getOrDefault(num, 0) + 1);
        if (!leafHeap.isEmpty() && num <= leafHeap.peek()) {
            leftSize--;
        } else {
            rightSize--;
        }
    }

    private void add(int num) {
        if (!leafHeap.isEmpty() && leafHeap.peek() >= num) {
            leafHeap.add(num);
            leftSize++;
        } else {
            rightHeap.add(num);
            rightSize++;
        }
    }

    private void rebalance() {
        if (leftSize > rightSize + 1) {
            rightHeap.add(leafHeap.poll());
            leftSize--;
            rightSize++;
        } else if (rightSize > leftSize) {
            leafHeap.add(rightHeap.poll());
            rightSize--;
            leftSize++;
        }
    }

    private void prune(Queue<Integer> queue) {
        while (!queue.isEmpty() && invalidNumCnt.containsKey(queue.peek())) {
            int removeNum = queue.poll();
            invalidNumCnt.put(removeNum, invalidNumCnt.get(removeNum) - 1);
            if (invalidNumCnt.get(removeNum) == 0) {
                invalidNumCnt.remove(removeNum);
            }
        }
    }

    private double getMedian() {
        if (leftSize == rightSize) {
            return ((long) leafHeap.peek() + rightHeap.peek()) / 2.0;
        }
        return leafHeap.peek(); // guarantee leftSize > rightSize
    }
}
