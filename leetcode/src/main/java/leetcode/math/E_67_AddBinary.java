package leetcode.math;

public class E_67_AddBinary {

    /**
     * Sum 2 binary strings
     * -----
     * TC: O(max(m, n)) where m and n are the lengths of the input strings
     * SC: O(max(m, n)) for the result string
     */
    public String addBinary(String a, String b) {
        if (a.length() < b.length()) return addBinary(b, a);

        StringBuilder res = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1, j = b.length() - 1;

        while (i >= 0) {
            int digitA = a.charAt(i) - '0';
            int digitB = j >= 0 ? b.charAt(j) - '0' : 0;
            int digitSum = digitA + digitB;

            // sum digit by digit, and consider the carry
            // can rewrite the logic as:
            // int sum = digitA + digitB + carry;
            // res.append(sum % 2);
            // carry = sum / 2;

            if (carry == 0) {
                if (digitSum > 1) {
                    carry = 1;
                    res.append(0);
                } else if (digitSum == 0) {
                    res.append(0);
                } else if (digitSum == 1) {
                    res.append(1);
                }
            } else {
                if (digitSum > 1) {
                    res.append(1);
                } else if (digitSum == 0) {
                    carry = 0;
                    res.append(1);
                } else if (digitSum == 1) {
                    res.append(0);
                }
            }

            i--;
            j--;
        }

        if (carry == 1) res.append(1);

        return res.reverse().toString();
    }
}
