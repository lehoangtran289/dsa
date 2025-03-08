package leetcode.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class M_128_LongestConsecutiveSequence {
    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2})); // 4
    }

    /**
     * HashSet
     * TC: O(n)
     */
    public static int longestConsecutive3(int[] nums) {
        if (nums.length <= 1) return nums.length;

        Set<Integer> set = new HashSet<>();
        for (int n : nums) set.add(n);

        int res = 1;
        for (int n : set) {
            // find first element of the sequence
            if (set.contains(n - 1)) continue;

            // find the length of the sequence
            int curNum = n;
            int curCount = 1;
            while (set.contains(curNum + 1)) {
                curNum++;
                curCount++;
            }
            res = Math.max(res, curCount);
        }
        return res;
    }

    /**
     * Sorting the array
     * TC: O(nlogn)
     */
    public static int longestConsecutive1(int[] nums) {
        if (nums.length <= 1) return nums.length;
        Arrays.sort(nums);

        int res = 1;
        int curCount = 1;

        for (int i = 1; i < nums.length; ++i) {
            // skip duplicate elements
            if (nums[i] == nums[i - 1]) continue;

            // find the length of the sequence
            if (nums[i] - nums[i - 1] == 1) {
                curCount++;
            } else {
                curCount = 1;
            }
            res = Math.max(res, curCount);
        }

        return res;
    }

    /**
     * Using treeset to sort the array
     * TC: O(nlogn)
     */
    public static int longestConsecutive(int[] nums) {
        if (nums.length <= 1) return nums.length;

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        for (int n : nums) pq.add(n);

        int res = 1;
        int prevNum = pq.poll();
        int curCount = 1;

        while (!pq.isEmpty()) {
            int curNum = pq.poll();
            if (curNum == prevNum) continue;
            if (curNum - prevNum == 1) {
                curCount++;
            } else {
                curCount = 1;
            }
            prevNum = curNum;

            res = Math.max(res, curCount);
        }

        return res;
    }
}
