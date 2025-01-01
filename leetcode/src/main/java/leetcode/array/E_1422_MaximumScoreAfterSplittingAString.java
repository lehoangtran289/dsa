package leetcode.array;

public class E_1422_MaximumScoreAfterSplittingAString {
    public static void main(String[] args) {
        System.out.println(maxScore("011101"));
    }

    public static int maxScore(String s) {
        int len = s.length();

        int ones = 0;
        for (int i = 0; i < len; ++i) {
            if (s.charAt(i) == '1') ones++;
        }

        int res = 0;
        int zeros = 0;
        for (int i = 0; i < len - 1; ++i) {
            if (s.charAt(i) == '0') {
                zeros++;
            } else {
                ones--;
            }
            res = Math.max(res, zeros + ones);
        }

        return res;
    }
}
