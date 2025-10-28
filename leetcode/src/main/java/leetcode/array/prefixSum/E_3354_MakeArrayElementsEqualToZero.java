package leetcode.array.prefixSum;

public class E_3354_MakeArrayElementsEqualToZero {

    /**
     * Prefix Sum without extra space
     * ---------------------------
     * TC: O(n)
     * SC: O(1)
     */
    public int countValidSelections(int[] nums) {
        int totalSum = 0;
        for (int num : nums) totalSum += num;

        int res = 0;
        int leftSum = 0;

        for (int num : nums) {
            leftSum += num;

            if (num == 0) {
                int rightSum = totalSum - leftSum;

                if (leftSum == rightSum) res += 2;
                else if (Math.abs(leftSum - rightSum) == 1) res++;
            }
        }

        return res;
    }
}
