package contest.weekly438;

public class E_Q1_CheckIfDigitsAreEqualInStringAfterOperationsI {
    public static void main(String[] args) {
        System.out.println(hasSameDigits("3902"));
        System.out.println(hasSameDigits("34789"));
    }

    public static boolean hasSameDigits(String s) {
        while (s.length() > 2) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < s.length() - 1; i++) {
                int digit1 = s.charAt(i) - '0';
                int digit2 = s.charAt(i + 1) - '0';
                sb.append((digit1 + digit2) % 10);
            }

            s = sb.toString();
        }

        return s.charAt(0) == s.charAt(1);
    }
}
