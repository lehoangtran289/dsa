package leetcode.array.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class M_2386_FindTheKSumOfAnArray {
    static void main() {
        System.out.println(kSum(new int[]{2, -2, 4}, 5)); // 2
    }

    /**
     * Idea: max sum = sum of all positive numbers in the array
     * next largest sum = either exclude a positive number, OR include a negative number
     * -> This means we are deducting a certain value from the max sum (-positive or +negative)
     * -> we sort array by its abs values
     * -> Use heap to keep track of next smallest deduction sum, forming by 2 possible choices:
     *      + add next element to current sum
     *      + skip previous element and add next element to current sum
     * (This forms all subsequence of deduction sum)
     * ---
     * result = max sum - k-th the smallest sum.
     * ---
     * TC: O(nlogn)
     * SC: O(k)
     */
    public static long kSum(int[] nums, int k) {
        // calculate max sum & convert negative values to its abs
        long maxSum = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] >= 0) maxSum += nums[i];
            else nums[i] = -nums[i];
        }

        Arrays.sort(nums);

        // max heap stores [cur sum, last index used]
        PriorityQueue<long[]> minHeap = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        minHeap.add(new long[]{nums[0], 0});

        long curSum = 0;
        while (k-- > 1) {
            var current = minHeap.poll();
            curSum = current[0];
            int curIndex = (int) current[1];

            if (curIndex < nums.length - 1) {
                // case 1: include next element
                minHeap.add(new long[]{curSum + nums[curIndex + 1], curIndex + 1});

                // case 2: exclude previous element before including next element
                minHeap.add(new long[]{curSum - nums[curIndex] + nums[curIndex + 1], curIndex + 1});
            }
        }

        return maxSum - curSum;
    }
}
