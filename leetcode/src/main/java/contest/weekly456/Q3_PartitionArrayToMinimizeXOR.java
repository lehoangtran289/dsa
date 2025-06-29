package contest.weekly456;

public class Q3_PartitionArrayToMinimizeXOR {
    public static void main(String[] args) {
        System.out.println(minXor(new int[]{331, 307, 121, 266, 397, 451, 279, 305}, 4)); // Output: 266
    }

    public static int minXor(int[] nums, int k) {
        int res = 0;
        int l = 0, r = Integer.MAX_VALUE;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            Boolean[][] memo = new Boolean[nums.length][k + 1];
            if (canPartition(nums, 0, k, mid, memo)) {
                res = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return res;
    }

    private static boolean canPartition(
            int[] nums,
            int index,
            int k,
            int limit,
            Boolean[][] memo
    ) {
        if (k == 0) return index == nums.length;
        if (index == nums.length) return false;
        if (memo[index][k] != null) return memo[index][k];

        int xor = 0;
        for (int i = index; i < nums.length; i++) {
            xor ^= nums[i];
            if (xor > limit) continue;

            // prune if remaining elements < remaining partitions
            if (nums.length - (i + 1) < (k - 1)) break;

            if (canPartition(nums, i + 1, k - 1, limit, memo)) {
                return memo[index][k] = true;
            }
        }

        return memo[index][k] = false;
    }
}
