package com.leetcode.treeheap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class _M_912_SortAnArray {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortArray(new int[]{5, 2, 3, 1})));
    }

    public static int[] sortArray(int[] nums) {
        int[] res = new int[nums.length];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.add(num);
        }
        for (int i = 0; i < res.length; i++) {
            res[i] = pq.poll();
        }
        return res;
    }
}
