package leetcode.string;

import java.util.Stack;

public class E_20_ValidParenthesis {
    public static void main(String[] args) {
        System.out.println(new E_20_ValidParenthesis().isValid("]]]]"));
    }

    public boolean isValid(String s) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); ++i) {
            int c = s.charAt(i);
            if (c == '{' || c == '[' || c == '(')  stack.add(c);
            else if (stack.isEmpty()) return false;

            if (c == '}' && stack.pop() != '{') return false;
            if (c == ']' && stack.pop() != '[') return false;
            if (c == ')' && stack.pop() != '(') return false;
        }
        return stack.isEmpty();
    }
}
