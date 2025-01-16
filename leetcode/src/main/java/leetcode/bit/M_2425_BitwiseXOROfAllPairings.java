package leetcode.bit;

public class M_2425_BitwiseXOROfAllPairings {
    public static void main(String[] args) {
        System.out.println(xorAllNums(new int[]{1, 2}, new int[]{3, 4}));
        System.out.println(xorAllNums(new int[]{2, 1, 3}, new int[]{10, 2, 5, 0}));
    }

    // X ^ 0 = X
    public static int xorAllNums(int[] nums1, int[] nums2) {
        int xor1 = 0;
        int xor2 = 0;

        if ((nums2.length & 1) == 1) { // odd length 2
            for (int n : nums1) xor1 ^= n;
        }

        if ((nums1.length & 1) == 1) { // odd length 1
            for (int j : nums2) xor2 ^= j;
        }

        return xor1 ^ xor2;
    }
}
