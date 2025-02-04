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

    /**
     * Another way to solve the problem:
     * ----
     * having X liters of gasoline at start.
     * -1 every time go to next station.
     * But at every station tank can be filled up with a new one containing station provided liters of gasoline.
     * If you run out of gasoline, you can not go further.
     */
    public static boolean canJump2(int[] nums) {
        int gas = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (gas == 0) {
                return false;
            }
            gas = Math.max(gas - 1, nums[i]);
        }
        return true;
    }
}
