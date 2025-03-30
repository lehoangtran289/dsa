package leetcode.string;

public class E_392_IsSubsequence {
    public static void main(String[] args) {
        System.out.println(isSubsequence(
                "abc",
                "ahbgdc"
        )); // true
    }

    public static boolean isSubsequence(String s, String t) {
        int p1 = 0, p2 = 0;

        while (p1 < s.length() && p2 < t.length()) {
            char c1 = s.charAt(p1);
            char c2 = t.charAt(p2);

            if (c1 == c2) {
                p1++;
                p2++;
            } else {
                p2++;
            }
        }

        return p1 == s.length();
    }
}
