package leetcode.string;

import java.util.ArrayDeque;
import java.util.Deque;

public class _M_1963_MinSwapsBalanceString {

    public static void main(String[] args) {
        System.out.println(minSwaps2("[[[]]]][][]][[]]][[["));
    }

    public static int minSwaps2(String s) {
        int open = 0, close = 0;

        for (int i = 0; i < s.length(); ++i) {
            int c = s.charAt(i);

            if (c == '[') open++;
            else {
                if (open > 0) open--;
                else close++;
            }
        }
        return (int) Math.ceil(((double) (open + close) / 2) / 2.0);
    }

    public static int minSwaps(String s) {
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < s.length(); ++i) {
            int c = s.charAt(i);

            if (c == '[') {
                st.addLast(c); // add [
            } else if (c == ']') {
                if (!st.isEmpty() && st.peekLast() == '[') {
                    st.pollLast(); // poll []
                } else {
                    st.addLast(c); // add ]
                }
            }
        }

        return (int) Math.ceil(((double) st.size() / 2) / 2.0);
    }
}
