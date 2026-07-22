package leetcode.array;

public class M_3499_MaximizeActiveSectionWithTradeI {
    static void main() {
        System.out.println(maxActiveSectionsAfterTrade("110001111")); // 6
        System.out.println(maxActiveSectionsAfterTrade("0000")); // 0
        System.out.println(maxActiveSectionsAfterTrade("11111")); // 5
    }

    /**
     * Idea: count all ones + find max 2 consecutive zero blocks
     * ---
     * TC: O(n)
     * SC: O(1)
     */
    public static int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        int oneCount = 0;

        for (char c : s.toCharArray()) {
            if (c == '1')
                oneCount++;
        }

        int maxZeroBlock = 0;
        int prevBlock = -1;

        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) != '0') continue;

            int start = i;
            i++;
            while (i < n && s.charAt(i) == '0') { // count the length of the zero block
                i++;
            }
            int block = i - start;

            if (prevBlock != -1) { // must exist >= 2 blocks to trade
                maxZeroBlock = Math.max(maxZeroBlock, block + prevBlock);
            }
            prevBlock = block;

            i--; // decrement i to account for the outer loop increment
        }

        return oneCount + maxZeroBlock;
    }
}
