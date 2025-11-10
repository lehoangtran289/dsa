package leetcode.array.prefixSum;

public class _0_PrefixSum {
    public static void main(String[] args) {
        int[] prefix = buildPrefixSum(new int[]{1, 2, 3, 4, 5});

        // sum in range [i..j] = prefix[j + 1] - prefix[i]
        System.out.println(prefix[1 + 1] - prefix[0]); // sum of nums[0..1] = 1 + 2 = 3
        System.out.println(prefix[3 + 1] - prefix[1]); // sum of nums[1..3] = 2 + 3 + 4 = 9
    }

    public static int[] buildPrefixSum(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n + 1]; // prefix[0] = 0 for convenience

        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }

        return prefix;
    }
}
