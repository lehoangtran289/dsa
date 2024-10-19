package leetcode.string;

import java.util.Arrays;

public class M_670_MaximumSwap {
    public static void main(String[] args) {
        System.out.println(new M_670_MaximumSwap().maximumSwap(2736));
        System.out.println(new M_670_MaximumSwap().maximumSwap(9973));
    }

    // swap (first element in the left) that is less than (its max right element)
    public int maximumSwap(int num) {
        char[] nums = String.valueOf(num).toCharArray();
        int[] maxRight = new int[nums.length];
        Arrays.fill(maxRight, -1);

        // first pass: find max element in right side
        int curMax = nums[nums.length - 1];
        int curMaxIdx = nums.length - 1;
        maxRight[maxRight.length - 1] = curMaxIdx;
        for (int i = nums.length - 2; i >= 0; --i) {
            if (nums[i] > curMax) {
                curMax = nums[i];
                curMaxIdx = i;
            }
            maxRight[i] = curMaxIdx;
        }

        // debug
//        for (int n : maxRight) {
//            System.out.print(n + " ");
//        }

        // second pass: greedy
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] < nums[maxRight[i]]) {
                char temp = nums[i];
                nums[i] = nums[maxRight[i]];
                nums[maxRight[i]] = temp;
                break;
            }
        }

        return Integer.parseInt(new String(nums));
    }
}
