package leetcode.array;

public class _2708_MaximumStrengthOfAGroup {
    public static void main(String[] args) {
        int[] nums = new int[]{6, -3, -4, 8, 4, 7, 6, 4, 7, 7, -3, -6, 9};
        System.out.println(maxStrength(nums));
    }

    public static long maxStrength(int[] nums) {
        long res = 1;
        boolean hasPos = false;
        boolean hasZero = false;
        int maxNeg = Integer.MIN_VALUE;
        int negCount = 0;

        for (int i : nums) {
            if (i > 0) {
                res *= i;
                hasPos = true;
            } else if (i < 0) {
                res *= i;
                maxNeg = Math.max(maxNeg, i);
                negCount++;
            } else {
                hasZero = true;
            }
        }
        if (!hasPos && negCount == 0) return 0;
        if (!hasPos && negCount == 1 && hasZero) return 0;
        if (!hasPos && negCount == 1) return maxNeg;
        return negCount % 2 == 0 ? res : res / maxNeg;
    }
}
