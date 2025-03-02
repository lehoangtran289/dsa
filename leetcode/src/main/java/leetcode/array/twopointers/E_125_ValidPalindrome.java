package leetcode.array.twopointers;

// Neetcode two pointers 1
public class E_125_ValidPalindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }

    /**
     * TC : O(n), in length n of the string. </br>
     * We traverse over each character at-most once,</br>
     * until the two pointers meet in the middle, or when we break and return early.</br>
     *</p>
     *  SC : O(1). No extra space required, at all.</br>
     */
    public static boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;

        while (l <= r) {
            while (l < s.length() && !isAlphanumeric(s.charAt(l))) l++;
            while (r >= 0 && !isAlphanumeric(s.charAt(r))) r--;

            if (l <= r && toLower(s.charAt(l)) != toLower(s.charAt(r))) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    private static boolean isAlphanumeric(char c) {
        return (c >= 'a' && c <= 'z')
               || (c >= '0' && c <= '9')
               || (c >= 'A' && c <= 'Z');
    }

    private static char toLower(char c) {
        return Character.toLowerCase(c);
    }
}
