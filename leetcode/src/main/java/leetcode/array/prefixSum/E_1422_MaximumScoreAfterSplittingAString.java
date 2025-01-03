package leetcode.array.prefixSum;

public class E_1422_MaximumScoreAfterSplittingAString {
    public static void main(String[] args) {
        System.out.println(maxScore("011101"));
    }

    public static int maxScoreIntuitive(String s) {
        int len = s.length();
        int[] prefixZeros = new int[len];
        int[] postfixOnes = new int[len];

        prefixZeros[0] = s.charAt(0) == '0' ? 1 : 0;
        postfixOnes[len - 1] = s.charAt(len - 1) == '1' ? 1 : 0;

        for (int i = 1; i < len; ++i) {
            prefixZeros[i] = prefixZeros[i - 1] + (s.charAt(i) == '0' ? 1 : 0);
        }

        for (int i = len - 2; i >= 0; --i) {
            postfixOnes[i] = postfixOnes[i + 1] + (s.charAt(i) == '1' ? 1 : 0);
        }

        int res = 0;
        for (int i = 0; i < len - 1; ++i) {
            res = Math.max(res, prefixZeros[i] + postfixOnes[i + 1]);
        }
        return res;
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
