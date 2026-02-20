package leetcode.array.heap;

import java.util.PriorityQueue;

public class H_295_FindMedianFromDataStream {

    /**
     * Use 2 heaps to maintain the left and right halves of the numbers
     * -----
     * TC: O(log n) for addNum, O(1) for findMedian
     * SC: O(n) for the heaps
     */
    static class TwoHeapSolution {

        private final PriorityQueue<Integer> leftHalf;
        private final PriorityQueue<Integer> rightHalf;

        public TwoHeapSolution() {
            this.leftHalf = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
            this.rightHalf = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
        }

        public void addNum(int num) {
            if (!leftHalf.isEmpty() && num < leftHalf.peek()) {
                leftHalf.add(num);

                if (leftHalf.size() > rightHalf.size()) {
                    rightHalf.add(leftHalf.poll());
                }
            } else {
                rightHalf.add(num);

                if (rightHalf.size() > leftHalf.size()) {
                    leftHalf.add(rightHalf.poll());
                }
            }
        }

        public double findMedian() {
            if (leftHalf.size() == rightHalf.size()) {
                if (leftHalf.isEmpty()) return 0; // no elements added yet

                return (double) (leftHalf.peek() + rightHalf.peek()) / 2;
            }

            return leftHalf.size() > rightHalf.size() ? leftHalf.peek() : rightHalf.peek();
        }
    }

    /**
     * Use counting sort approach, since the input numbers are in a small range [0, 100]
     * -----
     * TC: O(1) for addNum, O(100) for findMedian
     * SC: O(1) for the count array
     */
    static class FollowUpSolution {
        private final int[] count;
        private int totalCount;

        public FollowUpSolution() {
            this.count = new int[101];
            this.totalCount = 0;
        }

        public void addNum(int num) {
            count[num]++;
            totalCount++;
        }

        public double findMedian() {
            int mid1 = (totalCount + 1) / 2;
            int mid2 = (totalCount + 2) / 2;
            int cumulativeCount = 0;
            int median1 = -1, median2 = -1;

            for (int i = 0; i < count.length; ++i) {
                cumulativeCount += count[i];

                // when cumulativeCount reaches mid1, we found the first median
                if (cumulativeCount >= mid1 && median1 == -1) {
                    median1 = i;
                }

                // when cumulativeCount reaches mid2, we found the second median
                if (cumulativeCount >= mid2) {
                    median2 = i;
                    break;
                }
            }

            return (median1 + median2) / 2.0;
        }
    }
}
