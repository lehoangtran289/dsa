package leetcode.bit;

public class M_2275_LargestCombinationWithBitwiseANDGreaterThanZero {
    public static void main(String[] args) {
        System.out.println(largestCombination(new int[]{16, 17, 71, 62, 12, 24, 14}));
    }

    public static int largestCombination(int[] candidates) {
        int[] bitIndex = new int[24];
        for (int n : candidates) {
            for (int i = 0; i < 24; ++i) {
                if ((n & (1 << i)) != 0) {
                    bitIndex[i]++;
                }
            }
        }

        int max = 0;
        for (int idx : bitIndex) {
            max = Math.max(idx, max);
        }

        return max;
    }
}