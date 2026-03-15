package leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class M_1415_TheKthLexicographicalStringOfAllHappyStringsOfLengthN {
    public static void main(String[] args) {
        System.out.println(new M_1415_TheKthLexicographicalStringOfAllHappyStringsOfLengthN().getHappyString(1, 3));
    }

    private final char[] LETTERS = new char[]{'a', 'b', 'c'};

    public String getHappyString(int n, int k) {
        List<String> happyStrings = new ArrayList<>();
        generateHappyString(n, happyStrings, new StringBuilder());

        return k <= happyStrings.size() ? happyStrings.get(k - 1) : "";
    }

    /**
     * Backtracking
     */
    private void generateHappyString(
            int n,
            List<String> happyStrings,
            StringBuilder curStr
    ) {
        if (curStr.length() == n) {
            happyStrings.add(curStr.toString());
            return;
        }

        for (char c : LETTERS) {
            if (curStr.length() != 0 && curStr.charAt(curStr.length() - 1) == c)
                continue;

            curStr.append(c);
            generateHappyString(n, happyStrings, curStr);
            curStr.deleteCharAt(curStr.length() - 1);
        }
    }
}
