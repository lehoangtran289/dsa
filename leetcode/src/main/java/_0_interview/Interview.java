package _0_interview;

public class Interview {
    public static void main(String[] args) {
        System.out.println(solution("ABAB@")); // A1B1A1B1
        System.out.println(solution("AABBCC")); // A2B2C2
        System.out.println(solution("AA@ABB")); // A3B2
        System.out.println(solution("AAAA")); // A4
        System.out.println(solution(null));
        System.out.println(solution("A"));
    }

    public static String solution(String str) {
        if (str == null || str.isEmpty()) return "";

        StringBuilder res = new StringBuilder();
        char curChar = str.charAt(0);
        int curCount = 0;

        for (int i = 0; i < str.length(); ++i) {
            if (!isAlphabetOrDigit(str.charAt(i))) continue;

            if (str.charAt(i) != curChar) {
                res.append(curChar).append(curCount);
                curChar = str.charAt(i);
                curCount = 1;
            } else {
                curCount++;
            }
        }
        res.append(curChar).append(curCount);
        return res.toString();
    }

    private static boolean isAlphabetOrDigit(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }
}
