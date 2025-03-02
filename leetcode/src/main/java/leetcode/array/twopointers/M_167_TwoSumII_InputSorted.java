package leetcode.array.twopointers;

import java.util.Arrays;

// Neetcode two pointers 2
public class M_167_TwoSumII_InputSorted {

    public static void main(String[] args) {
        M_167_TwoSumII_InputSorted obj = new M_167_TwoSumII_InputSorted();
        int[] numbers = new int[]{2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(obj.twoSum(numbers, target)));
    }

    public int[] twoSum(int[] numbers, int target) {
        return twoSumTwoPointers(numbers, target);
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     * Two pointers approach.
     */
    public int[] twoSumTwoPointers(int[] numbers, int target) {
        int lo = 0;
        int hi = numbers.length - 1;

        while (lo < hi) {
            int sum = numbers[lo] + numbers[hi];
            if (sum == target) {
                return new int[]{lo + 1, hi + 1};
            } else if (sum < target) {
                lo++;
            } else {
                hi--;
            }
        }
        return null;
    }

    /**
     * Time complexity: O(nlogn)
     * Space complexity: O(1)
     * For each element, binary search for the other element in the left and right array.
     */
    public int[] twoSumBinarySearch(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int curNum = numbers[i];
            int left = binarySearch(numbers, 0, i - 1, target - curNum);
            if (left != -1) {
                return new int[]{left + 1, i + 1};
            }

            int right = binarySearch(numbers, i + 1, numbers.length - 1, target - curNum);
            if (right != -1) {
                return new int[]{i + 1, right + 1};
            }
        }
        return null;
    }

    public int binarySearch(int[] numbers, int lo, int hi, int target) {
        if (lo > hi) return -1;

        while (lo <= hi) {
            int mid = hi - (hi - lo) / 2;
            if (numbers[mid] == target) {
                return mid;
            } else if (numbers[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }
}