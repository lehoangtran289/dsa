package leetcode.array.slidingwindow;

public class E_2379_MinimumRecolorsToGetKConsecutiveBlackBlocks {
    public int minimumRecolors(String blocks, int k) {
        // process first window of size k
        int curWhiteCount = 0;
        for (int i = 0; i < k; ++i) {
            if (blocks.charAt(i) == 'W') curWhiteCount++;
        }

        // sliding window
        int res = curWhiteCount;

        for (int i = k; i < blocks.length(); ++i) {
            if (blocks.charAt(i) == 'W') curWhiteCount++; // next element
            if (blocks.charAt(i - k) == 'W') curWhiteCount--; // prev element

            res = Math.min(res, curWhiteCount);
        }

        return res;
    }
}
