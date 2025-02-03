package leetcode.array;

public class M_80_RemoveDuplicatesFromSortedArrayII {
    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{1, 1, 1, 2, 2, 3}));
    }

    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n < 2) return n;

        int pos = 0;
        int i = 0;

        while (i < n - 1) {
            if (nums[i] == nums[i + 1]) {
                nums[pos++] = nums[i++];
            }

            while (i < n - 1 && nums[i] == nums[i + 1]) {
                i++;
            }

            nums[pos++] = nums[i++];
        }

        // handle last element
        if (nums[n - 1] != nums[n - 2]) {
            nums[pos++] = nums[n - 1];
        }

        return pos;
    }
}
