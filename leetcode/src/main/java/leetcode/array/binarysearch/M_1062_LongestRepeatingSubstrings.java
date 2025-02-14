package leetcode.array.binarysearch;

import java.util.HashSet;
import java.util.Set;

public class M_1062_LongestRepeatingSubstrings {
    public static void main(String[] args) {
        System.out.println(longestRepeatingSubstringBS("abcd")); // 0
    }

    // intuitive bruteforce + set
    public static int longestRepeatingSubstring(String s) {
        Set<String> set = new HashSet<>();
        int n = s.length();

        int res = 0;
        for (int i = 0; i < n; ++i) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < n; ++j) {
                sb.append(s.charAt(j));
                String str = sb.toString();

                if (set.contains(str)) {
                    res = Math.max(res, str.length());
                } else {
                    set.add(str);
                }
            }
        }

        return res;
    }

    // binary search on result
    public static int longestRepeatingSubstringBS(String s) {
        int l = 1, r = s.length() - 1;

        int res = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (hasRepeatingSubstring(s, mid)) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return res;
    }

    private static boolean hasRepeatingSubstring(String s, int length) {
        Set<String> set = new HashSet<>();

        for (int i = 0; i <= s.length() - length; i++) {
            String substring = s.substring(i, i + length);
            if (set.contains(substring)) {
                return true;
            } else {
                set.add(substring);
            }
        }
        return false;
    }
}
