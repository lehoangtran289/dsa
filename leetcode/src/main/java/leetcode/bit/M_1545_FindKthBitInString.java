package leetcode.bit;

public class M_1545_FindKthBitInString {
    public static void main(String[] args) {
        System.out.println(new M_1545_FindKthBitInString().findKthBit(4, 11));
    }

    public char findKthBit(int n, int k) {
        StringBuilder str = new StringBuilder("0");

        // generate until reach n or kth element
        for (int i = 1; i < n && k > str.length(); ++i) {
            str.append('1');
            for (int j = str.length() - 2; j >= 0; --j) {
                if (str.charAt(j) == '1') {
                    str.append('0');
                } else {
                    str.append('1');
                }
            }
        }
        System.out.println(str);
        return str.charAt(k - 1);
    }
}
