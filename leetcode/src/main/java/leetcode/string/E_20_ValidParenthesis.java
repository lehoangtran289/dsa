package leetcode.string;

import java.util.ArrayDeque;
import java.util.Deque;

public class E_20_ValidParenthesis {
    static void main() {
        System.out.println(new E_20_ValidParenthesis().isValid("]]]]"));
    }

    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.addFirst(c);
                continue;
            }

            if (stack.isEmpty()) return false;

            char open = stack.pollFirst();
            if (open == '(' && c != ')') return false;
            if (open == '[' && c != ']') return false;
            if (open == '{' && c != '}') return false;
        }

        return stack.isEmpty();
    }
}
