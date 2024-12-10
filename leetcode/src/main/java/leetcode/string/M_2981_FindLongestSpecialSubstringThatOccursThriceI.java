package leetcode.string;

import java.util.HashMap;

public class M_2981_FindLongestSpecialSubstringThatOccursThriceI {
    public static void main(String[] args) {
        System.out.println(maximumLength("aaau")); // 1
        System.out.println(maximumLength("cccerrrecdcdccedecdcccddeeeddcdcddedccdceeedccecde")); // 2
        System.out.println(maximumLength("abcaba")); // 1
    }

    public static int maximumLength(String s) {
        int res = -1;
        int l = 0, r = s.length();

        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (check(s, mid)) {
                l = mid;
                res = mid;
            } else {
                r = mid;
            }
        }

        return res;
    }

    private static boolean check(String s, int length) {
        if (length == 0) return false;

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i <= s.length() - length; i++) {
            String sub = s.substring(i, i + length);
            if (!isSubStringValid(sub)) continue;

            int freq = map.getOrDefault(sub, 0) + 1;
            if (freq == 3) return true;
            map.put(sub, freq);
        }
        return false;
    }

    private static boolean isSubStringValid(String s) {
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) != s.charAt(i - 1)) return false;
        }
        return true;
    }
}
