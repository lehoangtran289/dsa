package leetcode.greedy;

public class M_1007_MinimumDominoRotationsForEqualRow {

    /**
     *  Check if the first tile can be used to make all tiles equal.
     *  If not, check if the first tile of the bottom row can be used.
     *  ----
     *  TC: O(n)
     *  SC: O(1)
     */
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int res = check(tops[0], tops, bottoms);
        return res != -1 ? res : check(bottoms[0], tops, bottoms);
    }

    private int check(int tile, int[] tops, int[] bottoms) {
        int n = tops.length;
        int rotTopCount = 0, rotBotCount = 0;

        for (int i = 0; i < n; ++i) {
            if (tops[i] != tile && bottoms[i] != tile) return -1;

            if (tops[i] == tile) rotBotCount++;
            if (bottoms[i] == tile) rotTopCount++;
        }

        return n - Math.max(rotBotCount, rotTopCount);
    }

    /**
     * Naive approach
     * 1. Calculate the frequency of each number in both rows.
     * 2. Find the number with the maximum frequency.
     * 3. Check if that number can be used to make all tiles equal.
     * ---
     * TC: O(n)
     * SC: O(1)
     */
    public int minDominoRotations2(int[] tops, int[] bottoms) {
        int n = tops.length;

        // calculate freq in tops and bottoms;
        int[] topFreq = new int[7];
        int[] botFreq = new int[7];

        for (int i = 0; i < n; ++i) {
            topFreq[tops[i]]++;
            botFreq[bottoms[i]]++;
        }

        // find max frequency number (1 -> 6)
        int maxFreq = 0, target = 0;
        for (int i = 1; i <= 6; ++i) {
            if (topFreq[i] + botFreq[i] > maxFreq) {
                maxFreq = topFreq[i] + botFreq[i];
                target = i;
            }
        }

        // if exists a row not having number <maxFreqTile> -> return -1
        for (int i = 0; i < n; ++i) {
            if (tops[i] != target && bottoms[i] != target) return -1;
        }

        return n - Math.max(topFreq[target], botFreq[target]);
    }
}
