package leetcode.string;

public class E_14_LongestCommonPrefix {
    static void main() {
        System.out.println(longestCommonPrefix(new String[]{"a", "ab"}));
        System.out.println(longestCommonPrefix2(new String[]{"dog", "racecar", "car"}));
    }

    /**
     * Vertical scan
     * ---
     * TC: O(n * m) ~ n = strs.length, m = (str with min len)
     * SC: O(1)
     */
    public static String longestCommonPrefix2(String[] strs) {
        for (int i = 0; i < strs[0].length(); ++i) {
            char c = strs[0].charAt(i);

            for (int j = 1; j < strs.length; ++j) {
                if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    /**
     * Binary search on result
     * ---
     * TC: O(n * m * log(m)) ~ n = strs.length, m = (str with min len)
     * SC: O(1)
     */
    public static String longestCommonPrefix(String[] strs) {
        int l = 0, r = Integer.MAX_VALUE;
        for (String str : strs) {
            r = Math.min(r, str.length());
        }

        // O(log(m))
        while (l <= r) {
            int mid = (l + r) / 2;
            if (isCommonPrefix(strs, mid)) { // O(m * n)
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return strs[0].substring(0, (l + r) / 2);
    }

    /**
     * TC: O(n * m) ~ n = strs.length, m = (str with min len)
     * SC: O(1)
     */
    private static boolean isCommonPrefix(String[] strs, int len) {
        String prefix = strs[0].substring(0, len);
        for (String s : strs) {
            if (!s.startsWith(prefix)) return false;
        }
        return true;
    }
}
