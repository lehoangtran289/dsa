package leetcode.array.prefixSum;

public class M_974_SubarraySumsDivisibleByK {
    public static void main(String[] args) {
        System.out.println(subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5)); // 7
    }

    /**
     * PREFIX SUM + COUNTING
     * TC: O(n)
     * SC: O(k)
     * ----
     * Idea: Sum of subarrays that has same remainder when / k are divisible by k.
     */
    public static int subarraysDivByK(int[] nums, int k) {
        int[] modGroups = new int[k];
        int res = 0;
        int prefixSum = 0;

        for (int num : nums) {
            prefixSum += num;
            int mod = ((prefixSum % k) + k) % k; // handle negative numbers

            if (mod == 0) res++; // no need to check modGroups since % k = 0

            if (modGroups[mod] > 0) {
                res += modGroups[mod];
            }
            modGroups[mod]++;
        }

        return res;
    }
}
