package leetcode.bit;

public class M_371_SumOfTwoIntegers {

    /**
     * Idea: negative number is represented in 2-compliment form (-a = ~a + 1)
     * -> Just take sum as if it is sum of 2 positive number
     */
    public int getSum(int a, int b) {
        if (a < b) return getSum(b, a);

        StringBuilder sb = new StringBuilder();
        int remain = 0;

        // binary form = 31 bit number, with bit 31 = signed bit
        for (int i = 0; i < 32; ++i) {
            int bitA = (1 << i) & a;
            int bitB = (1 << i) & b;

            if ((bitA ^ bitB) != 0) { // 1 ^ 0
                sb.append(remain == 0 ? '1' : '0');
            } else {
                if (bitA == 0) { // 0 ^ 0
                    if (remain == 0) {
                        sb.append('0');
                    } else {
                        sb.append('1');
                        remain = 0;
                    }
                } else { // 1 ^ 1
                    if (remain == 0) {
                        sb.append('0');
                        remain = 1;
                    } else {
                        sb.append('1');
                    }
                }
            }
        }

        int res = 0;
        for (int i = 0; i < sb.length(); ++i) {
            if (sb.charAt(i) != '0') res |= (1 << i);
        }
        return res;
    }
}
