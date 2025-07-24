package leetcode.string;

public class M_8_StringToIntegerATOI {
    public static void main(String[] args) {
        M_8_StringToIntegerATOI solution = new M_8_StringToIntegerATOI();
        System.out.println(solution.myAtoi("42")); // 42
        System.out.println(solution.myAtoi("   -42")); // -42
        System.out.println(solution.myAtoi("419 with words")); // 419
        System.out.println(solution.myAtoi("words and 987")); // 0
        System.out.println(solution.myAtoi("-91283472332")); // -2147483648
    }

    public static final long MAX = (long) Math.pow(2, 31);

    public int myAtoi(String s) {
        int sign = 1;
        long res = 0;

        // 1. whitespace
        s = s.trim();

        // base case
        if (s.isEmpty()) return (int) res;
        if (!isDigit(s.charAt(0)) && s.charAt(0) != '-' && s.charAt(0) != '+') return (int) res;

        // 2. signedness
        if (s.charAt(0) == '-') sign = -1;
        if (isDigit(s.charAt(0))) res = s.charAt(0) - '0';

        for (int i = 1; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (!isDigit(c)) break;

            // 3. conversion
            res = res * 10 + (c - '0');

            // 4. check overflow
            if (res >= MAX) {
                if (sign == -1) res = MAX;
                else res = MAX - 1;
            }
        }

        return (int) res * sign;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
