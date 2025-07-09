package leetcode.array;

public class E_605_CanPlaceFlowers {

    /**
     * Greedy
     * -------------------
     * TC: O(n)
     * SC: O(1)
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        int len = flowerbed.length;

        for (int i = 0; i < len; ++i) {
            if (flowerbed[i] == 1) continue;

            // check left and right of current position (flowerbed[i] == 0)
            // skip check if boundary
            if (
                    (i == 0 || flowerbed[i - 1] == 0) &&
                    (i == len - 1 || flowerbed[i + 1] == 0)
            ) {
                count++;
                flowerbed[i] = 1;
            }
        }

        return count >= n;
    }
}
