package leetcode.array.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class M_1190_ReverseSubstringsBetweenEachPairOfParentheses {

    static void main() {
        System.out.println(reverseParentheses("(u(love)i)")); // iloveu
    }

    /**
     * Idea: Wormhole teleport with 2 passes.
     * - 1st pass: build indices pair of ( ) parentheses
     * - 2nd pass: traverse to build result, if ( or ) encountered -> teleport to other side and switch direction.
     * ---
     * TC: O(n)
     * SC: O(n)
     */
    public static String reverseParentheses(String s) {
        int n = s.length();
        Deque<Integer> openIndices = new ArrayDeque<>();
        int[] pair = new int[n];

        // pair up parentheses
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(') {
                openIndices.addFirst(i);
            } else if (s.charAt(i) == ')') {
                int j = openIndices.pollFirst();
                pair[j] = i;
                pair[i] = j;
            }
        }

        // build result string
        StringBuilder sb = new StringBuilder();

        int i = 0, dir = 1;
        while (i < n) {
            char c = s.charAt(i);
            if (c == '(' || c == ')') {
                i = pair[i];
                dir = -dir;
            } else {
                sb.append(c);
            }
            i += dir;
        }

        return sb.toString();
    }
}
