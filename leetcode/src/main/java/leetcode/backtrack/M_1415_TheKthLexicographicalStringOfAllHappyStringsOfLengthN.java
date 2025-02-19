package leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class M_1415_TheKthLexicographicalStringOfAllHappyStringsOfLengthN {
    public static void main(String[] args) {
        System.out.println(new M_1415_TheKthLexicographicalStringOfAllHappyStringsOfLengthN().getHappyString(1, 3));
    }

    private final char[] CHARS = new char[] {'a', 'b', 'c'};
    private int n;

    public String getHappyString(int n, int k) {
        this.n = n;
        List<String> res = new ArrayList<>();
        backtrack(new StringBuilder(), res);

        return k <= res.size() ? res.get(k - 1) : "";
    }

    private void backtrack(
            StringBuilder sb,
            List<String> res
    ) {
        if (sb.length() == n) {
            res.add(sb.toString());
            return;
        }

        for (char c : CHARS) {
            if (sb.length() == 0 || sb.charAt(sb.length() - 1) != c) {
                sb.append(c);
                backtrack(sb, res);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
