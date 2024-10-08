package leetcode.string;

import java.util.ArrayDeque;
import java.util.Deque;

public class _1963_MinSwapsBalanceString {

    public static void main(String[] args) {
        System.out.println(minSwaps("]]][[["));
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
