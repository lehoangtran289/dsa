package leetcode.string;

public class E_14_LongestCommonPrefix {
    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"a", "ab"}));
        System.out.println(longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
    }

    /**
     * Binary search on result
     */
    public static String longestCommonPrefix(String[] strs) {
        int l = 0, r = Integer.MAX_VALUE;
        for (String str : strs) {
            r = Math.min(r, str.length());
        }

        // O(log(m))
        while (l <= r) {
            int mid = (l + r) / 2;
            if (isCommonPrefix(strs, mid)) { // O(n)
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return strs[0].substring(0, (l + r) / 2);
    }

    /**
     * O(n * m) ~ n = strs.length, m = (str with min len)
     */
    private static boolean isCommonPrefix(String[] strs, int len) {
        String prefix = strs[0].substring(0, len);
        for (String s : strs) {
            if (!s.startsWith(prefix)) return false;
        }
        return true;
    }
}
