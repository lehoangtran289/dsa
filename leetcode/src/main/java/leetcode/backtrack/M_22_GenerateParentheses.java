package leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class M_22_GenerateParentheses {
    static void main() {
        M_22_GenerateParentheses solution = new M_22_GenerateParentheses();
        System.out.println(solution.generateParenthesis(3)); // ["((()))","(()())","(())()","()(())","()()()"]
    }

    /**
     * Backtrack but keep only valid path ~ Prune asap
     * ---
     * TC: O(2^n) - all possible combination
     * SC: O(n) - depth of tree
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(res, n, 0, 0, new StringBuilder());
        return res;
    }

    private void backtrack(
            List<String> res,
            int n,
            int leftCount,
            int rightCount,
            StringBuilder sb
    ) {
        if (sb.length() == 2 * n) {
            res.add(sb.toString());
            return;
        }

        if (leftCount < n) {
            sb.append('(');
            backtrack(res, n, leftCount + 1, rightCount, sb);
            sb.setLength(sb.length() - 1);
        }

        if (leftCount > rightCount) {
            sb.append(')');
            backtrack(res, n, leftCount, rightCount + 1, sb);
            sb.setLength(sb.length() - 1);
        }
    }
}
