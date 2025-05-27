package leetcode.string;

public class M_186_ReverseWordsInAStringII {
    public static void main(String[] args) {
        char[] s = "Hello a World".toCharArray();
        reverseWords(s);
        System.out.println(s); // Output: "World a Hello"
    }

    /**
     * Reverse char[] s in place, then reverse each word in s.
     * ----------
     * TC: O(n)
     * SC: O(1)
     */
    public static void reverseWords(char[] s) {
        int n = s.length;

        // reverse char[] s
        reverse(s, 0, n - 1);

        // reverse each word in s
        int l = 0;
        for (int r = 0; r < n; ++r) {
            if (s[r] == ' ') {
                reverse(s, l, r - 1);
                l = r + 1;
            }
        }
        reverse(s, l, n - 1);
    }

    private static void reverse(char[] s, int p1, int p2) {
        while (p1 <= p2) {
            char temp = s[p1];
            s[p1] = s[p2];
            s[p2] = temp;
            p1++;
            p2--;
        }
    }
}
