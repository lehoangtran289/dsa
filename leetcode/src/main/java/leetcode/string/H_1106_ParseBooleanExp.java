package leetcode.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class H_1106_ParseBooleanExp {
    public static void main(String[] args) {
        System.out.println(new H_1106_ParseBooleanExp().parseBoolExpr("|(&(t,f,t),!(t))")); // false
        System.out.println(new H_1106_ParseBooleanExp().parseBoolExpr("!(&(f,t))")); // true
        System.out.println(new H_1106_ParseBooleanExp().parseBoolExpr("!(&(f,|(f,&(|(f)),f,t)))")); // true
    }

    public static final char AND = '&';
    public static final char OR = '|';
    public static final char NOT = '!';
    public static final char OPEN = '(';
    public static final char CLOSE = ')';
    public static final char TRUE = 't';
    public static final char FALSE = 'f';
    public static final char DELIMITER = ',';

    public boolean parseBoolExpr(String str) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); ++i) {
            char cur = str.charAt(i);
            if (cur != DELIMITER) stack.push(cur);

            if (cur == CLOSE) {
                stack.pop(); // pop )

                // init exp list + operator
                List<Character> exp = new ArrayList<>();
                while (stack.peek() != OPEN) {
                    exp.add(stack.pop());
                }
                stack.pop(); // pop (
                char op = stack.pop();

                // process
                stack.push(parse(exp, op));
            }
        }
        return stack.peek() == TRUE;
    }

    public char parse(List<Character> exp, char op) {
        if (op == AND) {
            return parseAND(exp);
        } else if (op == OR) {
            return parseOR(exp);
        } else { // NOT
            return parseNOT(exp);
        }
    }

    public char parseAND(List<Character> exp) {
        for (char c : exp) {
            if (c == FALSE) return FALSE;
        }
        return TRUE;
    }

    public char parseOR(List<Character> exp) {
        for (char c : exp) {
            if (c == TRUE) return TRUE;
        }
        return FALSE;
    }

    public char parseNOT(List<Character> exp) {
        return exp.get(0) == TRUE ? FALSE : TRUE;
    }
}
