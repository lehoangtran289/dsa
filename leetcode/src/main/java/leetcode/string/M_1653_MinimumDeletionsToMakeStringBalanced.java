package leetcode.string;

import java.util.Stack;

/**
 * Calculate the minimum number of deletions required to make a given string balanced.
 * A string is considered balanced if every 'a' comes before every 'b'.
 */
public class M_1653_MinimumDeletionsToMakeStringBalanced {
    public static void main(String[] args) {
        System.out.println(minimumDeletions("aababbab")); // 2
        System.out.println(minimumDeletions2("aababbab")); // 2
        System.out.println(minimumDeletions3("aababbab")); // 2
    }

    /**
     * 3 passes solution
     * --------------------
     * TC: O(n)
     * SC: O(n)
     */
    public static int minimumDeletions(String s) {
        int res = Integer.MAX_VALUE;
        int n = s.length();

        int[] prefB = new int[n];
        int[] suffA = new int[n];

        // calculate number of 'b' before each index
        for (int i = 1; i < n; ++i) {
            prefB[i] = prefB[i - 1] + (s.charAt(i - 1) == 'b' ? 1 : 0);
        }

        // calculate number of 'a' after each index
        for (int i = n - 2; i >= 0; --i) {
            suffA[i] = suffA[i + 1] + (s.charAt(i + 1) == 'a' ? 1 : 0);
        }

        // calculate minimum deletions required
        for (int i = 0; i < n; ++i) {
            res = Math.min(res, Math.abs(prefB[i] + suffA[i]));
        }

        return res;
    }

    /**
     * 1 pass using stack, remove 'ba' pairs
     * --------------------
     * TC: O(n)
     * SC: O(n)
     */
    public static int minimumDeletions2(String s) {
        int n = s.length();
        Stack<Character> charStack = new Stack<>();
        int res = 0;

        for (int i = 0; i < n; ++i) {
            char curChar = s.charAt(i);

            if (
                    !charStack.isEmpty() &&
                    charStack.peek() == 'b' &&
                    curChar == 'a'
            ) {
                res++;
                charStack.pop();
            } else {
                charStack.add(curChar);
            }
        }

        return res;
    }

    /**
     * 1 pass using variable count, remove 'ba' pairs
     * --------------------
     * TC: O(n)
     * SC: O(1)
     */
    public static int minimumDeletions3(String s) {
        int res = 0;
        int n = s.length();
        int bCount = 0;

        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);

            if (bCount > 0 && c == 'a') {
                res++;
                bCount--;
            } else if (c == 'b') {
                bCount++;
            }
        }

        return res;
    }
}
