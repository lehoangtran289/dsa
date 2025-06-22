package leetcode.string;

public class M_1898_MaximumNumberOfRemovableChars {

    public static void main(String[] args) {
        System.out.println(maximumRemovals("qlevcvgzfpryiqlwy", "qlecfqlw", new int[]{12, 5}));
    }

    /**
     * Binary Search to search for k - max number of first indexes in removable
     * --------------------
     * TC: O(n * log m) where n = s.length, m = removable.length
     * SC: O(n)
     */
    public static int maximumRemovals(String s, String p, int[] removable) {
        int res = 0;
        int l = 0, r = removable.length;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (isValidRemove(s, p, removable, mid)) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return res;
    }

    private static boolean isValidRemove(String s, String p, int[] removeable, int length) {
        char[] sChars = s.toCharArray();
        for (int i = 0; i < length; ++i) {
            sChars[removeable[i]] = '.';
        }

        return isSubsequence(sChars, p);
    }

    /**
     * Check if s2 is subsequence of s1
     */
    private static boolean isSubsequence(char[] s1, String s2) {
        int p1 = 0, p2 = 0;

        while (p1 < s1.length && p2 < s2.length()) {
            if (s1[p1] == s2.charAt(p2)) {
                p1++;
                p2++;
            } else {
                p1++;
            }
        }

        return p2 == s2.length();
    }

}
