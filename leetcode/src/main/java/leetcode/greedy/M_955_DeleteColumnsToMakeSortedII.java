package leetcode.greedy;

public class M_955_DeleteColumnsToMakeSortedII {
    public static void main(String[] args) {
        System.out.println(minDeletionSize(new String[]{"xga", "xfb", "yfa"})); // 1
    }

    /**
     * Greedy
     * Idea:
     * - Try to keep each column if possible
     * - Use a boolean array to track which adjacent string pairs are already sorted
     * ----------------------------------
     * TC: O(m * n)
     * SC: O(n)
     */
    public static int minDeletionSize(String[] strs) {
        int cols = strs[0].length();
        int rows = strs.length;
        int res = 0;

        boolean[] sortedPair = new boolean[rows - 1];

        for (int i = 0; i < cols; ++i) {
            boolean needRemove = false;
            for (int j = 0; j < rows - 1; ++j) {
                if (!sortedPair[j] && strs[j].charAt(i) > strs[j + 1].charAt(i)) {
                    needRemove = true;
                    res++;
                    break;
                }
            }
            if (needRemove) continue;

            for (int j = 0; j < rows - 1; ++j) {
                if (strs[j].charAt(i) < strs[j + 1].charAt(i)) {
                    sortedPair[j] = true;
                }
            }
        }

        return res;
    }
}
