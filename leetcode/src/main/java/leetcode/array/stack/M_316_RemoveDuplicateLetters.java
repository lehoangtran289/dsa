package leetcode.array.stack;

import java.util.ArrayDeque;
import java.util.Deque;

// Same as M_1081_SmallestSubsequenceOfDistinctCharacters
public class M_316_RemoveDuplicateLetters {
    static void main() {
        System.out.println(removeDuplicateLetters("bcabc")); // Output: "abc"
        System.out.println(removeDuplicateLetters("cbacdcbc")); // Output: "acdb"
    }

    /**
     * Monotonic stack
     * ---
     * TC: O(n), where n is the length of s
     * SC: O(n)
     */
    public static String removeDuplicateLetters(String s) {
        // count the frequency of each character in the string
        int[] charFreq = new int[128];
        for (char c : s.toCharArray()) {
            charFreq[c]++;
        }

        boolean[] isInStack = new boolean[128];
        Deque<Character> deque = new ArrayDeque<>(); // monotonic stack, increasing order (Lexico smaller)

        for (char c : s.toCharArray()) {
            charFreq[c]--;

            if (isInStack[c]) continue; // skip if already in stack

            // remove last character that > current char
            // and will appear later in stack
            while (
                    !deque.isEmpty()
                    && deque.peekLast() > c
                    && charFreq[deque.peekLast()] > 0
            ) {
                isInStack[deque.peekLast()] = false;
                deque.pollLast();
            }

            // add current char to stack to mark it as uniquely included
            isInStack[c] = true;
            deque.addLast(c);
        }

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.pollFirst());
        }
        return sb.toString();
    }
}
