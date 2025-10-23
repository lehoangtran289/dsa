package contest.weekly438;

public class E_3461_CheckIfDigitsAreEqualInStringAfterOperationsI {
    public static void main(String[] args) {
        System.out.println(hasSameDigits("3902"));
        System.out.println(hasSameDigits("34789"));
    }

    public static boolean hasSameDigits(String s) {
        int n = s.length();
        int[] digits = new int[n];

        for (int i = 0; i < n; ++i) {
            digits[i] = s.charAt(i) - '0';
        }

        // need total of n - 2 operations
        for (int i = 0; i < n - 2; ++i) {
            for (int j = 0; j < n - i - 1; ++j) {
                digits[j] = (digits[j] + digits[j + 1]) % 10;
            }
        }

        return digits[0] == digits[1];
    }
}
