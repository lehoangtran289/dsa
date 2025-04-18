package leetcode.string;

public class M_38_CountAndSay {
    public static void main(String[] args) {
        System.out.println(countAndSay(4)); // 1211
        System.out.println(countAndSay(1)); // 1
    }

    /**
     * -----------------------------------------------------------
     * Iterative approach
     * -----------------------------------------------------------
     * TC: O(n * m) where n is the number of iterations and m is the length of the string
     * SC: O(m) where m is the length of the string
     */
    public static String countAndSay(int n) {
        String res = "1";
        for (int i = 2; i <= n; ++i) {
            StringBuilder sb = new StringBuilder();
            char prev = res.charAt(0);
            int count = 1;

            for (int j = 1; j < res.length(); ++j) {
                if (res.charAt(j) == prev) {
                    count++;
                } else {
                    sb.append(count).append(prev);
                    prev = res.charAt(j);
                    count = 1;
                }
            }
            sb.append(count).append(prev);
            res = sb.toString();
        }

        return res;
    }

    /**
     * -----------------------------------------------------------
     * Recursive approach
     * -----------------------------------------------------------
     * TC: O(n * m) where n is the number of iterations and m is the length of the string
     * SC: O(m) where m is the length of the string
     */
    public static String countAndSay2(int n) {
        if (n == 1) return "1";
        String prevStr = countAndSay2(n - 1);

        StringBuilder sb = new StringBuilder();
        int count = 1;
        char prevChar = prevStr.charAt(0);

        for (int i = 0; i < prevStr.length(); ++i) {
            if (prevStr.charAt(i) == prevChar) {
                count++;
            } else {
                sb.append(count).append(prevChar);
                prevChar = prevStr.charAt(i);
                count = 1;
            }
        }
        sb.append(count).append(prevChar);
        return sb.toString();
    }
}
