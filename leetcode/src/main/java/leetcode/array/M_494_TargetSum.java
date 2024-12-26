package leetcode.array;

public class M_494_TargetSum {
    public static void main(String[] args) {
        System.out.println(findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
    }

    // BRUTE FORCE RECURSION SOLUTION O(2^n)----------------------------------------------------------------------------

    static int res = 0;
    public static int findTargetSumWays(int[] nums, int target) {
        check(nums, target, 0, 0);
        return res;
    }

    private static void check(int[] nums, int target, int curIdx, int curSum) {
        if (curIdx == nums.length - 1) {
            if (curSum == target) {
                res++;
            }
        } else {
            // +
            check(nums, target, curIdx + 1, curSum + nums[curIdx]);

            // -
            check(nums, target, curIdx + 1, curSum - nums[curIdx]);
        }
    }
}
