package leetcode.array;

public class M_80_RemoveDuplicatesFromSortedArrayII {
    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 1}));
    }

    static final int MAX_DUPLICATES = 2;

    // two pointer to update the array in place
    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n < 2) return n;

        int pos = 0;
        for (int i = 0; i < n - 1; ++i) {
            // handle first element
            int count = 1;
            nums[pos++] = nums[i];

            // handle next duplicate element
            while (nums[i] == nums[i + 1] && count < MAX_DUPLICATES) {
                count++;
                nums[pos++] = nums[i++];
            }

            // skip duplicates
            while (i < n - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
        }

        // handle last element
        if (nums[n - 1] != nums[n - 2]) {
            nums[pos++] = nums[n - 1];
        }

        return pos;
    }
}
