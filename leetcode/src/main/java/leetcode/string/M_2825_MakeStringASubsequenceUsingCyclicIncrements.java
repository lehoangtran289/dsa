package leetcode.string;

public class M_2825_MakeStringASubsequenceUsingCyclicIncrements {
    public static void main(String[] args) {
        System.out.println(canMakeSubsequence("zc", "ad"));
        System.out.println(canMakeSubsequence("dm", "e"));
    }

    public static boolean canMakeSubsequence(String str1, String str2) {
        int p2 = 0;
        for (int i = 0; i < str1.length(); ++i) {
            if (p2 == str2.length()) return true;

            char c1 = str1.charAt(i);
            char c2 = str2.charAt(p2);

            if (c1 == c2 || (c1 + 1 - 'a') % 26 + 'a' == c2) {
               p2++;
            }
        }

        return p2 == str2.length();
    }
}
