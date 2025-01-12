package leetcode.string;

import java.util.Stack;

public class M_2116_CheckIfAParenthesesStringCanBeValid {
    public static void main(String[] args) {
        System.out.println(canBeValid("())()))()(()(((())(()()))))((((()())(())", "1011101100010001001011000000110010100101"));
        System.out.println(canBeValid("((()(()()))()((()()))))()((()(()", "10111100100101001110100010001001"));
        System.out.println(canBeValid("())(()(()(())()())(())((())(()())((())))))(((((((())(()))))(", "100011110110011011010111100111011101111110000101001101001111"));
        System.out.println(canBeValid("))()))", "010100"));
    }

    static class Pair {
        Character c;
        Character locked;

        Pair(char c, char locked) {
            this.c = c;
            this.locked = locked;
        }
    }

    public static boolean canBeValid(String s, String locked) {
        int len = s.length();
        if (len % 2 != 0) return false;

        Stack<Integer> openBrackets = new Stack<>();
        Stack<Integer> unlocked = new Stack<>();

        // Iterate through the string to handle '(' and ')'
        for (int i = 0; i < len; i++) {
            if (locked.charAt(i) == '0') {
                unlocked.push(i);
            } else if (s.charAt(i) == '(') {
                openBrackets.push(i);
            } else if (s.charAt(i) == ')') {
                if (!openBrackets.empty()) {
                    openBrackets.pop();
                } else if (!unlocked.empty()) {
                    unlocked.pop();
                } else {
                    return false;
                }
            }
        }

        // Match remaining open brackets with unlocked characters
        while (
                !openBrackets.empty() &&
                !unlocked.empty() &&
                openBrackets.peek() < unlocked.peek()
        ) {
            openBrackets.pop();
            unlocked.pop();
        }

        return openBrackets.empty();
    }
}
