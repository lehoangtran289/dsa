package leetcode.string;

public class E_28_FindTheIndexOfTheFirstOccurrenceInAString {
    public static void main(String[] args) {
        System.out.println(strStr("mississippi", "issi"));
    }

    public static int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();

        for (int i = 0; i <= m - n; ++i) {
            int p1 = i, p2 = 0;
            while (p2 < n && haystack.charAt(p1) == needle.charAt(p2)) {
                p1++;
                p2++;
            }
            if (p2 == n) return i;
        }

        return -1;
    }
}
