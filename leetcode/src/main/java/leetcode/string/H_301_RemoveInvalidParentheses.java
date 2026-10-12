package leetcode.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class H_301_RemoveInvalidParentheses {
    static void main() {
        System.out.println(new H_301_RemoveInvalidParentheses().removeInvalidParentheses("()())()"));
        // [()()(), (())()]
    }

    private String s;
    private Set<String> validStrings;

    /**
     * Idea: Backtracking + pruning
     * Generate all valid strings using backtracking, precount number of invalid parentheses to remove for pruning
     * --
     * TC: O(2^N) - generate all possible strings, N = length of s
     * SC: O(N) - recursion stack
     */
    public List<String> removeInvalidParentheses(String s) {
        this.s = s;
        this.validStrings = new HashSet<>();

        // count number of invalid open and close parentheses to remove
        int openRemoves = 0, closeRemoves = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                openRemoves++;
            } else if (c == ')') {
                if (openRemoves == 0) {
                    closeRemoves++;
                } else {
                    openRemoves--;
                }
            }
        }

        backtrack(0, openRemoves, closeRemoves, 0, new StringBuilder());

        return new ArrayList<>(validStrings);
    }

    private void backtrack(
            int index,
            int openRemoves, int closeRemoves,
            int score,
            StringBuilder sb
    ) {
        // base case
        if (score < 0 || s.length() - index < openRemoves + closeRemoves) return;

        // if we reach the end of the string, check if we have a valid string
        if (index == s.length()) {
            if (openRemoves == 0 && closeRemoves == 0 && score == 0) {
                validStrings.add(sb.toString());
            }
            return;
        }

        char currentChar = s.charAt(index);

        // if current char is letter
        if (currentChar != '(' && currentChar != ')') {
            sb.append(currentChar);
            backtrack(index + 1, openRemoves, closeRemoves, score, sb);
            sb.setLength(sb.length() - 1);
            return;
        }

        // If current char is parenthesis, we have two choices: remove it or keep it
        // Case 1: remove parenthesis
        if (currentChar == '(' && openRemoves > 0) {
            backtrack(index + 1, openRemoves - 1, closeRemoves, score, sb);
        }
        if (currentChar == ')' && closeRemoves > 0) {
            backtrack(index + 1, openRemoves, closeRemoves - 1, score, sb);
        }

        // Case 2: Keep this parenthesis
        sb.append(currentChar);
        if (currentChar == '(') {
            backtrack(index + 1, openRemoves, closeRemoves, score + 1, sb);
        } else {
            backtrack(index + 1, openRemoves, closeRemoves, score - 1, sb);
        }
        sb.setLength(sb.length() - 1);
    }
}
