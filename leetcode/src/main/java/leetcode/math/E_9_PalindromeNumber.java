package leetcode.math;

public class E_9_PalindromeNumber {
    public static void main(String[] args) {
        System.out.println(isPalindrome(123));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) return false;

        int save = x;
        int reverseX = 0;
        while (x != 0) {
            reverseX = reverseX * 10 + x % 10;
            x /= 10;
        }

        return reverseX == save;
    }
}
