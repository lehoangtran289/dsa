package leetcode.string;

import java.util.ArrayDeque;

public class E_1790_CheckIdOneStringSwapCanMakeStringsEqual {
    public static void main(String[] args) {
        System.out.println(areAlmostEqual("qgqeg", "gqgeq"));  // q g q g --- g q g q
        System.out.println(areAlmostEqual("bank", "kanb")); // b k --- k b
    }

    public static boolean areAlmostEqual(String s1, String s2) {
        ArrayDeque<Character> q1 = new ArrayDeque<>();
        ArrayDeque<Character> q2 = new ArrayDeque<>();
        int diffCount = 0;

        for (int i = 0; i < s1.length(); ++i) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);

            if (c1 != c2) {
                q1.add(c1);
                q2.add(c2);

                diffCount++;
                if (diffCount > 2) return false;
            }

        }

        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (q1.pollFirst() != q2.pollLast()) return false;
        }

        return true;
    }
}
