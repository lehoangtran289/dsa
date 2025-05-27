package leetcode.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class M_151_ReverseWordsInAString {
    public static void main(String[] args) {
        M_151_ReverseWordsInAString solution = new M_151_ReverseWordsInAString();
        System.out.println(solution.reverseWords("  Hello World  ")); // Output: "World Hello"
        System.out.println(solution.reverseWords2("  Hello World  ")); // Output: "World Hello"
    }

    /**
     * Stack approach
     * ----------
     * TC: O(n)
     * SC: O(n)
     */
    public String reverseWords(String s) {
        // trim
        s = s.trim();

        // add to stack
        Stack<String> stack = new Stack<>();
        StringBuilder curString = new StringBuilder();

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (c == ' ') {
                if (curString.length() != 0) {
                    stack.add(curString.toString());
                    curString.setLength(0);
                }
            } else {
                curString.append(c);
            }
        }
        stack.add(curString.toString());

        // build result from stack
        StringBuilder res = new StringBuilder();

        while (!stack.isEmpty()) {
            res.append(stack.pop()).append(' ');
        }

        // remove last space
        res.setLength(res.length() - 1);
        return res.toString();
    }

    /**
     * Trim + Split + Reverse join approach
     * ----------
     * TC: O(n) + O(n) + O(n) = O(n)
     */
    public String reverseWords2(String s) {
        // trim
        s = s.trim();

        // split
        List<String> wordList = splitString(s);

        // reverse and join
        StringBuilder res = new StringBuilder();

        for (int i = wordList.size() - 1; i >= 0; --i) {
            res.append(wordList.get(i));
            if (i != 0) res.append(' ');
        }

        return res.toString();
    }

    private List<String> splitString(String s) {
        List<String> res = new ArrayList<>();
        StringBuilder curSb = new StringBuilder();

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (c == ' ') {
                if (curSb.length() > 0) {
                    res.add(curSb.toString());
                    curSb.setLength(0);
                }
            } else {
                curSb.append(c);
            }
        }
        res.add(curSb.toString());

        return res;
    }
}
