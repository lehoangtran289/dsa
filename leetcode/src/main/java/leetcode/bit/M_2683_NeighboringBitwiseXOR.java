package leetcode.bit;

public class M_2683_NeighboringBitwiseXOR {
    public static void main(String[] args) {
        System.out.println(doesValidArrayExist(new int[]{1, 1, 0}));
    }

    public static boolean doesValidArrayExist(int[] derived) {
        int cnt = 0;
        for (int n : derived) cnt ^= n;
        return cnt == 0;
    }
}
