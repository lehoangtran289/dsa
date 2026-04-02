package leetcode.array.stack;

import java.util.Stack;

public class M_394_DecodeString {
    public static void main(String[] args) {
        System.out.println(decodeString("3[a]2[bc]")); // "aaabcbc"
    }

    /**
     * 2 Stacks: 1 for string, 1 for count
     * -----
     * TC: O(maxK * n), traverse n, repeat maxK times when encounter ']'
     * SC: O(m + n)
     */
    public static String decodeString(String s) {
        Stack<String> stringStack = new Stack<>();
        Stack<Integer> countStack = new Stack<>();

        StringBuilder cur = new StringBuilder(); // current string ready to be multiplied when encounter ']'
        int k = 0;

        for (char c : s.toCharArray()) {
            if (isDigit(c)) {
                k = k * 10 + (c - '0');
            } else if (isLetter(c)) {
                cur.append(c);
            } else if (c == '[') {
                stringStack.push(cur.toString());
                countStack.push(k);
                k = 0;
                cur = new StringBuilder();
            } else if (c == ']') {
                int freq = countStack.pop();
                StringBuilder decodedSb = new StringBuilder(stringStack.pop());

                for (int i = 1; i <= freq; ++i) {
                    decodedSb.append(cur);
                }
                cur = decodedSb;
            }
        }

        return cur.toString();
    }

    /**
     * 1 Stack: store char, when encounter ']', pop until '[', then pop number, repeat string and push back to stack
     * -----
     * TC: O(maxK^countK * n)
     * SC: ...
     */
    public static String decodeString2(String s) {
        int n = s.length();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);

            if (c == ']') {
                StringBuilder innerSb = new StringBuilder();
                while (stack.peek() != '[') {
                    innerSb.append(stack.pop());
                }
                innerSb.reverse();
                stack.pop(); // pop [

                int base = 1;
                int k = 0;
                while (!stack.isEmpty() && isDigit(stack.peek())) {
                    k += (stack.pop() - '0') * base;
                    base *= 10;
                }

                StringBuilder decodedSb = new StringBuilder();
                for (int j = 1; j <= k; ++j) {
                    decodedSb.append(innerSb);
                }

                for (int j = 0; j < decodedSb.length(); ++j) {
                    stack.push(decodedSb.charAt(j));
                }
            } else {
                stack.push(c);
            }
        }

        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) res.append(stack.pop());
        return res.reverse().toString();
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isLetter(char c) {
        return c >= 'a' && c <= 'z';
    }
}
