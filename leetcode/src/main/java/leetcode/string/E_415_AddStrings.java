package leetcode.string;

public class E_415_AddStrings {
    public static void main(String[] args) {
        System.out.println(addStrings(
                "96",
                "927"
        ));
    }

    // SUM LARGE NUMBER
    public static String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder();

        int remain = 0;
        int p1 = num1.length() - 1;
        int p2 = num2.length() - 1;

        while (p1 >= 0 || p2 >= 0) {
            int num1Digit = p1 >= 0 ? num1.charAt(p1) - '0' : 0;
            int num2Digit = p2 >= 0 ? num2.charAt(p2) - '0' : 0;

            int sum = num1Digit + num2Digit + remain;
            int resultDigit = sum % 10;
            remain = sum / 10;
            res.append(resultDigit);

            p1--;
            p2--;
        }

        if (remain != 0) res.append(remain);

        return res.reverse().toString();
    }
}
