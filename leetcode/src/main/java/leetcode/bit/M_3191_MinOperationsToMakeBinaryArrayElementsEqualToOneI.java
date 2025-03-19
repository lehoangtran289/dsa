package leetcode.bit;

public class M_3191_MinOperationsToMakeBinaryArrayElementsEqualToOneI {
    public static void main(String[] args) {
        System.out.println(minOperations(new int[]{0, 1, 1, 1, 0, 0})); // 3
        System.out.println(minOperations(new int[]{0, 1, 1, 1})); // -1
    }

    /**
     * Sliding window approach
     * TC: O(N)
     * SC: O(1)
     */
    public static int minOperations2(int[] nums) {
        int n = nums.length;

        int count = 0;
        for (int i = 0; i < n - 3; ++i) {
            if (nums[i] == 0) {
                count++;
                nums[i + 2] ^= 1;
                nums[i + 1] ^= 1;
                nums[i] ^= 1;
            }
        }

        boolean isConsecutiveSameValue = nums[n - 3] == nums[n - 2] && nums[n - 3] == nums[n - 1];
        if (!isConsecutiveSameValue) return -1;

        return nums[n - 3] == 0 ? count + 1 : count;
    }

    /**
     * Recursive approach
     * TC: O(N)
     * SC: O(1)
     */
    public static int minOperations(int[] nums) {
        return minOps(nums, 0, 0);
    }

    public static int minOps(
            int[] nums,
            int curId,
            int count
    ) {
        // base cases
        if (curId == nums.length - 3) {
            boolean isConsecutiveSameValue = nums[curId] == nums[curId + 1] && nums[curId] == nums[curId + 2];
            if (!isConsecutiveSameValue) return -1;

            return nums[curId] == 0 ? count + 1 : count;
        }

        if (nums[curId] == 1) {
            return minOps(nums, curId + 1, count);
        } else {
            nums[curId + 1] ^= 1;
            nums[curId + 2] ^= 1;
            nums[curId] ^= 1;
            return minOps(nums, curId + 1, count + 1);
        }
    }
}
