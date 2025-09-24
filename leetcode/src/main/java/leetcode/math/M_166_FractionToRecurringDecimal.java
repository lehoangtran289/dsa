package leetcode.math;

import java.util.HashMap;
import java.util.Map;

public class M_166_FractionToRecurringDecimal {
    public static void main(String[] args) {
        System.out.println(fractionToDecimal(1, 6)); // "0.1(6)"
    }

    /**
     * Idea: HashMap to store seen remainder
     * -----------------------
     * TC: O(N) - N is the length of the result string
     * SC: O(N)
     */
    public static String fractionToDecimal(int numerator, int denominator) {
        StringBuilder res = new StringBuilder();

        // cover negative fraction
        if (
                (numerator < 0 && denominator > 0)
                || (numerator > 0 && denominator < 0)
        ) res.append('-');

        // convert to long
        long numer = Math.abs((long) numerator);
        long denom = Math.abs((long) denominator);

        long quot = numer / denom;
        long rem = numer % denom;
        res.append(quot);

        if (rem == 0) return res.toString();

        // init map to store seen remainder
        Map<Long, Integer> seenRem = new HashMap<>();
        res.append('.');

        while (rem != 0) {
            if (seenRem.containsKey(rem)) {
                res.insert(seenRem.get(rem), "(");
                res.append(')');
                break;
            }

            seenRem.put(rem, res.length());
            rem *= 10;
            quot = rem / denom;
            rem = rem % denom;
            res.append(quot);
        }

        return res.toString();
    }
}
