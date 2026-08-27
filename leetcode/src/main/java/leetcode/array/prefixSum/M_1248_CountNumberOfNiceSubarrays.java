package leetcode.array.prefixSum;

/**
 * Find number of subarrays with exactly k odd numbers
 */
public class M_1248_CountNumberOfNiceSubarrays {
    static void main() {
        System.out.println(numberOfSubarrays(new int[]{1, 1, 2, 1, 1}, 3)); // 2
    }

    /**
     * Idea: Exactly k = atMost(k) - atMost(k - 1) => Sliding window
     * -----------------------
     * TC: O(n)
     * SC: O(n)
     */
    public static int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    private static int atMost(int[] nums, int k) {
        int res = 0;
        int oddCount = 0;

        int l = 0;
        for (int r = 0; r < nums.length; ++r) {
            if ((nums[r] & 1) == 1) oddCount++;

            while (oddCount > k) {
                if ((nums[l] & 1) == 1) oddCount--;
                l++;
            }

            res += r - l + 1; // number of subarrays ending at r
        }

        return res;
    }
}
