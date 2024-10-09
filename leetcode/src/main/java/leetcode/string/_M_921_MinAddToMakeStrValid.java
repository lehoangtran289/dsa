package leetcode.string;

import java.util.ArrayDeque;
import java.util.Deque;

public class _M_921_MinAddToMakeStrValid {
    public int minAddToMakeValid(String s) {
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < s.length(); ++i) {
            int c = s.charAt(i);

            if (c == '(') {
                st.addLast(c); // add [
            } else if (c == ')') {
                if (!st.isEmpty() && st.peekLast() == '(') {
                    st.pollLast(); // poll []
                } else {
                    st.addLast(c); // add ]
                }
            }
        }

        return st.size();
    }
}
