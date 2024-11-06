package leetcode.bit;

public class M_3011_FindIfArrayCanBeSorted {
    public static void main(String[] args) {
        System.out.println(canSortArray1(new int[]{8, 4, 2, 30, 15}));
    }

    /**
     * Bubble sort
     */
    public static boolean canSortArray1(int[] nums) {
        for (int i = 0; i < nums.length - 1; ++i) {
            for (int j = i + 1; j < nums.length - i - 1; ++j) {
                if (nums[i] > nums[j]) {
                    if (countOnes(nums[i]) != countOnes(nums[j])) return false;
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return true;
    }

    private static int countOnes(int num) {
        return Integer.bitCount(num);
    }
}
