package leetcode.string;

public class _M_1898_MaximumNumberOfRemovableChars {

    public static void main(String[] args) {
        System.out.println(maximumRemovals("qlevcvgzfpryiqlwy", "qlecfqlw", new int[]{12,5}));
    }

    /**
     * Binary Search to search for k - max number of first indexes in removable
     */
    public static int maximumRemovals(String s, String p, int[] removable) {
        int lo = 0, hi = removable.length;

        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        int max = 0;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            // mark remove char as *
            for (int i = 0; i < mid; ++i) {
                sChars[removable[i]] = '*';
            }

            // validate
            if (isSubsequence(sChars, pChars)) {
                lo = mid + 1;
                max = Math.max(max, mid);
            } else {
                hi = mid - 1;
            }

            // restore *
            for (int i = 0; i < mid; ++i) {
                sChars[removable[i]] = s.charAt(removable[i]);
            }
        }
        return max;
    }

    public static boolean isSubsequence(char[] s, char[] p) {
        if (p.length == 0) return true;
        if (s.length == 0) return false;

        int i = 0, j = 0;
        while (i < s.length && j < p.length) {
            if (s[i] == p[j]) {
                i++;
                j++;
            } else {
                i++;
            }
        }
        return j == p.length;
    }

}
