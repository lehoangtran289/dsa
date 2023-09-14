package com.leetcode.arraystring;

import org.checkerframework.checker.units.qual.A;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class KdiffPairsInAnArray {
    public static void main(String[] args) {
        System.out.println(findPairs(new int[]{1, 2, 3, 4, 5}, 1));
    }

    public static int binarySearch(int[] nums, int target, int low) {
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }

    public static int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (binarySearch(nums, nums[i] + k, i + 1) != -1) {
                set.add(nums[i]);
            }
        }
        return set.size();
    }
}
