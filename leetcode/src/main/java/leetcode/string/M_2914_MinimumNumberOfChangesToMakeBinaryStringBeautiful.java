package leetcode.string;

public class M_2914_MinimumNumberOfChangesToMakeBinaryStringBeautiful {
    public static void main(String[] args) {
        System.out.println(minChanges("1001"));
        System.out.println(minChanges("10"));
        System.out.println(minChanges("0000"));
    }

    /**
     * Use ^ to beat 100% time complexity <br/>
     * ~ Similar to if (s.charAt(i) != s.charAt(i + 1)) res ++;
     */
    public static int minChanges(String s) {
        int res = 0;
        for (int i = 0; i < s.length() - 1; i += 2) {
            res += s.charAt(i) ^ s.charAt(i + 1);
        }
        return res;
    }
}
