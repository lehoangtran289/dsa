package leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class M_22_GenerateParentheses {
    public static void main(String[] args) {
        M_22_GenerateParentheses solution = new M_22_GenerateParentheses();
        System.out.println(solution.generateParenthesis(3)); // ["((()))","(()())","(())()","()(())","()()()"]
    }

    private static final char[] PARAMS = new char[] {'(', ')'};
    private int n;

    public List<String> generateParenthesis(int n) {
        this.n = n;

        List<String> res = new ArrayList<>();
        backtrack(res, new StringBuilder());
        return res;
    }

    private void backtrack(
            List<String> res,
            StringBuilder cur
    ) {
        int curScore = getScore(cur.toString());

        // optimization to avoid unnecessary backtrack
        // curScore < 0 ~ we have more closing brackets than opening brackets
        // curScore > n ~ we have more opening brackets than n
        if (curScore < 0 || curScore > n) return;

        // base case - if we have reached the end of the string and the score is 0
        if (
                cur.length() == 2 * n &&
                curScore == 0
        ) {
            res.add(cur.toString());
            return;
        }

        for (char c : PARAMS) {
            cur.append(c);
            backtrack(res, cur);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    private int getScore(String str) {
        int score = 0;
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) == '(') {
                score++;
            } else {
                score--;
            }

            if (score < 0 || score > n) return score;
        }
        return score;
    }
}
