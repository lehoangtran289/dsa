package leetcode.bit;

public class M_3097_ShortestSubarrayWithORAtLeastK2 {
    public static void main(String[] args) {
        System.out.println(minimumSubarrayLength(new int[]{1, 2}, 0));
    }

    // binary search
    public static int minimumSubarrayLength(int[] nums, int k) {
        int l = 1, h = nums.length;
        int minLen = -1;

        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (check(nums, mid, k)) {
                minLen = mid;
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return minLen;
    }

    // Checks if there exists a subarray of given length whose bitwise OR is >= k
    public static boolean check(int[] nums, int mid, int k) {
        int[] bits = new int[32];

        // sliding window
        for (int i = 0; i < nums.length; ++i) {
            updateBits(bits, nums[i], 1);

            if (i >= mid) {
                updateBits(bits, nums[i - mid], -1);
            }

            if (convertBitToNum(bits) >= k) {
                return true;
            }
        }
        return false;
    }

    public static void updateBits(int[] bits, int num, int delta) {
        for (int i = 0; i < bits.length; ++i) {
            if (((1 << i) & num) != 0) {
                bits[i] += delta;
            }
        }
    }

    public static int convertBitToNum(int[] bits) {
        int res = 0;
        for (int i = 0; i < bits.length; ++i) {
            if (bits[i] != 0) res += (int) Math.pow(2, i);
        }
        return res;
    }

}
