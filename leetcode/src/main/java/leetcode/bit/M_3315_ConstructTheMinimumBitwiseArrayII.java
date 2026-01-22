package leetcode.bit;

import java.util.Arrays;
import java.util.List;

public class M_3315_ConstructTheMinimumBitwiseArrayII {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(minBitwiseArray(List.of(2, 3, 5, 7, 11)))); // [ -1, 1, 4, 3, 9 ]
    }

    public static int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] res = new int[n];

        for (int i = 0; i < n; ++i) {
            res[i] = nums.get(i) == 2 ? -1 : helper(nums.get(i));
        }

        return res;
    }

    // a OR (a+1) = take a, flip the first 0 above the trailing-ones block to 1, and set all lower bits to 1”
    // x = a | (a + 1)
    // -> find trailing-ones block, set all bits to 0 except last bit on the left -> that is (a + 1)
    private static int helper(int num) {
        int mask = 1;
        int res = num;

        while ((res & (mask << 1)) != 0) {
            res &= ~mask; // unset bit at mask
            mask <<= 1;
        }

        return res - 1;
    }
}
