package leetcode.bit;

public class H_1835_XORSumOfAND {
    public int getXORSum(int[] arr1, int[] arr2) {
        int[] bitCount2 = new int[30];
        for (int x : arr2) {
            for (int i = bitCount2.length - 1; i >= 0; --i) {
                if (((x >> i) & 1) == 1) {
                    bitCount2[i]++;
                }
            }
        }

        int[] xorResult = new int[30];
        for (int x : arr1) {
            for (int i = xorResult.length - 1; i >= 0; --i) {
                if (((x >> i) & 1) == 1) {
                    xorResult[i] += bitCount2[i];
                }
            }
        }

        int result = 0;
        for (int i = xorResult.length - 1; i >= 0; --i) {
            if (xorResult[i] % 2 != 0) {
                result += 1 << i;
            }
        }
        return result;
    }
}
