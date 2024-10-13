package leetcode.greedy;

import java.util.Arrays;

public class _1561_MaximumNumberOfCoinsYouCanGet {
    public static void main(String[] args) {
        System.out.println(maxCoins(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1}));
    }

    public static int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int res = 0;

        for (int i = piles.length / 3; i < piles.length; i += 2)
            res += piles[i];

        return res;
    }
}
