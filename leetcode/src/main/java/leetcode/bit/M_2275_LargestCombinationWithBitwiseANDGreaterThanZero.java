package leetcode.bit;

public class M_2275_LargestCombinationWithBitwiseANDGreaterThanZero {
    public static void main(String[] args) {
        System.out.println(largestCombination(new int[]{16, 16, 48, 71, 62, 12, 24, 14, 17, 18, 19, 20, 10000}));
    }

    public static int largestCombination(int[] candidates) {
        int maxCandidate = 0;
        for (int n : candidates) {
            maxCandidate = Math.max(maxCandidate, n);
        }

        int[] bitIndex = new int[Integer.toBinaryString(maxCandidate).length()];
        int max = 0;

        for (int n : candidates) {
            for (int i = 0; i < bitIndex.length; ++i) {
                if ((n & (1 << i)) != 0) {
                    bitIndex[i]++;
                    max = Math.max(bitIndex[i], max);
                }
            }
        }

        return max;
    }
}