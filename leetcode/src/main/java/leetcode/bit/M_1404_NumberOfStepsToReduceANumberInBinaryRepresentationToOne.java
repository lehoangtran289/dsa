package leetcode.bit;

public class M_1404_NumberOfStepsToReduceANumberInBinaryRepresentationToOne {
    public static void main(String[] args) {
        System.out.println(numSteps("1101")); // 6
        System.out.println(numSteps("1111")); // 5
    }

    /**
     * Just do the operations :D
     * Idea: stringbuilder to represent the binary number
     */
    public static int numSteps(String s) {
        int res = 0;
        StringBuilder sb = new StringBuilder(s);

        while (!isOne(sb)) {
            if (sb.charAt(sb.length() - 1) == '1') {
                addOne(sb);
            } else {
                divideTwo(sb);
            }
            res++;
        }

        return res;
    }

    private static boolean isOne(StringBuilder sb) {
        int sum = 0;
        for (int i = 0; i < sb.length(); ++i) {
            char c = sb.charAt(i);

            sum += c - '0';
            if (sum > 1) return false;
        }
        return sb.charAt(sb.length() - 1) == '1';
    }

    private static void addOne(StringBuilder sb) {
        int remain = 1;

        for (int i = sb.length() - 1; i >= 0; --i) {
            int sum = remain + (sb.charAt(i) - '0');

            if (sum > 1) {
                remain = 1;
                sb.setCharAt(i, '0');
            } else {
                remain = 0;
                sb.setCharAt(i, sum == 0 ? '0' : '1');
            }
        }

        if (remain == 1) sb.insert(0, '1');
    }

    private static void divideTwo(StringBuilder sb) {
        // shift right = delete last char
        sb.deleteCharAt(sb.length() - 1);
    }
}
