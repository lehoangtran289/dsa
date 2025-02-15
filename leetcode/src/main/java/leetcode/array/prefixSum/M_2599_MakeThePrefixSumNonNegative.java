package leetcode.array.prefixSum;

import java.util.PriorityQueue;

public class M_2599_MakeThePrefixSumNonNegative {
    public static void main(String[] args) {
        System.out.println(makePrefSumNonNegative(new int[]{6, -6, -3, 3, 1, 5, -4, -3, -2, -3, 4, -1, 4, 4, -2, 6, 0})); // 1
    }

    public static int makePrefSumNonNegative(int[] nums) {
        int n = nums.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>(); // store neg number in order
        long prefixSum = 0;
        int res = 0;

        for (int i = 0; i < n; ++i) {
            if (nums[i] < 0) {
                queue.add(nums[i]);
            }

            prefixSum += nums[i];

            if (prefixSum < 0 && !queue.isEmpty()) {
                res++;
                prefixSum -= queue.poll();
            }
        }

        return res;
    }
}
