package leetcode.bit;

public class M_2429_MinimizeXOR {

    // 1   = 0001 (1)
    // 12  = 1100 (2)
    // #set_bits(1) = 1
    // #set_bits(2) = 2
    // (1) < (2) -> 0011 = 3

    // 1   = 1011 (1)
    // 12  = 1100 (2)
    // #set_bits(1) = 3
    // #set_bits(2) = 2
    // (1) > (2) ->  = 3

    // 3 = 0011 (1)
    // 5 = 0101 (2)
    // #set_bits(1) = 2
    // #set_bits(2) = 2
    // (1) = (2) -> 0011 = 3
    public static void main(String[] args) {
        System.out.println(1 << 3);
        System.out.println(minimizeXor(1, 12)); // 3
    }

    /**
     * we need to adjust to match the number of set bits in num2 while trying to keep it as close to num1 as possible
     * If (1) > (2), we remove extra 1s from result by unsetting bits, starting from the LSB set
     *          (because they are less important for matching num1 closely).
     * If (1) < (2), we add more 1s to result by setting bits, starting from the LSB unset.
     */
    public static int minimizeXor(int num1, int num2) {
        int cnt1 = Integer.bitCount(num1);
        int cnt2 = Integer.bitCount(num2);

        if (cnt1 < cnt2) {
            int i = 0;
            while (cnt1 < cnt2) {
                if (!isSet(num1, i)) {
                    num1 = set(num1, i);
                    cnt1++;
                }
                ++i;
            }
        } else if (cnt1 > cnt2) {
            int i = 0;
            while (cnt1 > cnt2) {
                if (isSet(num1, i)) {
                    num1 = unset(num1, i);
                    cnt1--;
                }
                ++i;
            }
        }

        return num1;
    }

    private static boolean isSet(int x, int idx) {
        return (x & (1 << idx)) != 0;
    }

    private static int set(int x, int idx) {
        return x | (1 << idx);
    }

    private static int unset(int x, int idx) {
        return x & ~(1 << idx);
    }
}
