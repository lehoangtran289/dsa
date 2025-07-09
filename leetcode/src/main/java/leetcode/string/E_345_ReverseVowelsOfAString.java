package leetcode.string;

import java.util.Stack;

public class E_345_ReverseVowelsOfAString {

    /**
     * 1. Two Pointers
     * --------------------
     * TC: O(n)
     * SC: O(n)
     */
    public String reverseVowels(String s) {
        int l = 0, r = s.length() - 1;
        char[] sChars = s.toCharArray();

        while (l < r) {
            // find left vowel
            while (l < s.length() && !isVowel(sChars[l])) l++;

            // find right vowel
            while (r >= 0 && !isVowel(sChars[r])) r--;

            // swap
            if (l < r) {
                char temp = sChars[l];
                sChars[l] = sChars[r];
                sChars[r] = temp;
            }

            l++;
            r--;
        }

        return new String(sChars);
    }

    /**
     * 2. Stack
     * --------------------
     * TC: O(n)
     * SC: O(n)
     */
    public String reverseVowels2(String s) {
        // Collect all vowels in a stack
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (isVowel(c)) stack.add(c);
        }

        // pop stack to reverse vowels
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; ++i) {
            if (isVowel(arr[i])) {
                arr[i] = stack.pop();
            }
        }

        return new String(arr);
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c =='o' || c == 'u'
               || c == 'A' || c == 'E' || c == 'I' || c =='O' || c == 'U';
    }
}
