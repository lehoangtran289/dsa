package leetcode.array.stack;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 2 ways to check the balance parentheses: score vs stack
 */
public class M_1249_MinimumRemoveToMakeValidParentheses {
    public static void main(String[] args) {
        System.out.println(minRemoveToMakeValid("lee(t(c)o)de)"));
        System.out.println(minRemoveToMakeValid("a)b(c)d"));
        System.out.println(minRemoveToMakeValid("))(("));
        System.out.println(minRemoveToMakeValid("(a(b(c)d)"));
    }

    // --------------------------------------------------- CACH 1

    /**
     * Time: O(n)
     */
    public static String minRemoveToMakeValid(String s) {
        Set<Integer> invalidIds = getInvalidIndexes2(s);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            if (!invalidIds.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    private static Set<Integer> getInvalidIndexes2(String s) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> invalids = new HashSet<>();

        for (int i = 0; i < s.length(); ++i) {
            char curChar = s.charAt(i);

            if (curChar == '(') {
                stack.add(i);
            } else if (curChar == ')') {
                if (stack.isEmpty()) {
                    invalids.add(i);
                } else {
                    char peek = s.charAt(stack.peek());
                    if (peek == '(') {
                        stack.pop();
                    } else {
                        invalids.add(i);
                    }
                }
            }
        }

        invalids.addAll(stack);
        return invalids;
    }

    // --------------------------------------------------- CACH 2

    public static String minRemoveToMakeValid1(String s) {
        // process s from L -> R to get the invalid parentheses
        Set<Integer> invalidIds = getInvalidIndexes(s);

        // process s from R -> L to get the invalid parentheses
        Set<Integer> invalidIdsReversed = getInvalidIndexes(reverse(s));
        for (int id : invalidIdsReversed) {
            invalidIds.add(s.length() - 1 - id);
        }

        // skip those unvalid parentheses
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            if (!invalidIds.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    private static Set<Integer> getInvalidIndexes(String s) {
        Set<Integer> invalids = new HashSet<>(); // keep tracks all invalids parentheses ids

        int score = 0; // to detect unbalance
        for (int i = 0; i < s.length(); ++i) {
            char curChar = s.charAt(i);

            if (curChar == '(') {
                score++;
            } else if (curChar == ')') {
                score--;
            } else {
                continue;
            }

            if (score < 0) {
                score = 0;
                invalids.add(i);
            }
        }

        return invalids;
    }

     private static String reverse(String s) {
         StringBuilder sb = new StringBuilder();
         for (int i = s.length() - 1; i >=0; --i) {
             char curChar = s.charAt(i);

             if (curChar == '(') sb.append(')');
             else if (curChar == ')') sb.append('(');
             else sb.append(curChar);
         }
         return sb.toString();
     }
}
