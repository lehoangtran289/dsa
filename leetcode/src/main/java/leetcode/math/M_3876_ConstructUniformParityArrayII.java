package leetcode.math;

public class M_3876_ConstructUniformParityArrayII {

    /**
     * Math
     * Idea: parity of the array must follow the parity of the minimum number in the array
     * 1. if min num is even -> all nums must be even
     * 2. if min num is odd -> we can translate whole array to odd no matter what
     */
    public boolean uniformArray(int[] nums) {
        int minNum = 1 << 30;
        boolean hasOdd = false;

        for (int num : nums) {
            minNum = Math.min(minNum, num);
            if ((num & 1) == 1) hasOdd = true;
        }

        // if min num is even -> all nums must be even
        if ((minNum & 1) == 0) return !hasOdd;

        // if min num is odd -> we can translate whole array to odd no matter what
        return true;
    }

    // -----------------------------------------------------------

    /**
     * Intuition
     */
    public boolean uniformArray2(int[] nums1) {
        return tryOdd(nums1) || tryEven(nums1);
    }

    private boolean tryOdd(int[] nums) {
        int minOdd = 1 << 30;

        for (int num : nums) {
            if ((num & 1) == 1) minOdd = Math.min(minOdd, num);
        }

        for (int num : nums) {
            if ((num & 1) == 0 && num - minOdd < 1) return false;
        }
        return true;
    }

    private boolean tryEven(int[] nums) {
        for (int num : nums) {
            if ((num & 1) == 1) return false;
        }
        return true;
    }
}
