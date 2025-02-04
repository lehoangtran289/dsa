package leetcode.array;

public class M_55_JumpGame {

    public static void main(String[] args) {
        System.out.println(canJump(new int[]{2, 3, 1, 1, 4})); // true
        System.out.println(canJump(new int[]{3, 2, 1, 0, 4})); // false
    }

    public static boolean canJump(int[] nums) {
        int n = nums.length;

        int limit = nums[0];
        for (int i = 0; i <= limit; ++i) {
            if (limit >= n - 1) return true;
            limit = Math.max(limit, i + nums[i]);
        }

        return false;
    }
}
