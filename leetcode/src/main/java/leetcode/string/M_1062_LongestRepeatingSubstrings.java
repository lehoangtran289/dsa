package leetcode.string;

import java.util.HashSet;
import java.util.Set;

public class M_1062_LongestRepeatingSubstrings {
    public static void main(String[] args) {
        System.out.println(longestRepeatingSubstring("abcd")); // 0
    }

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
}
