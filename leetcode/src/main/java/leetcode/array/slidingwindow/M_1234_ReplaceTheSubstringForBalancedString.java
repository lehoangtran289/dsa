package leetcode.array.slidingwindow;

public class M_1234_ReplaceTheSubstringForBalancedString {
    public static void main(String[] args) {
        System.out.println(balancedString("QWER")); // 0
        System.out.println(balancedString("QQWE")); // 1
        System.out.println(balancedString("QQQW")); // 2
    }

    /**
     * Sliding Window
     * TC: O(n)
     * SC: O(1)
     */
    public static int balancedString(String s) {
        int n = s.length();
        int k = s.length() / 4;
        int[] freq = new int[4];

        // find frequency of each character in s
        for (int i = 0; i < n; ++i) {
            freq[toIndex(s, i)]++;
        }

        // find number of characters should be changed
        for (int i = 0; i < 4; ++i) {
            freq[i] = Math.max(0, freq[i] - k);
        }

        // early return if string is already balanced
        if (isValid(freq)) return 0;

        // sliding window
        int res = n;
        int l = 0;
        for (int r = 0; r < n; ++r) {
            freq[toIndex(s, r)]--;

            while (l <= r && isValid(freq)) {
                res = Math.min(res, r - l + 1);

                freq[toIndex(s, l)]++;
                l++;
            }
        }

        return res;
    }

    private static boolean isValid(int[] freq) {
        for (int f : freq) {
            if (f > 0) return false;
        }
        return true;
    }

    private static int toIndex(String s, int i) {
        char c = s.charAt(i);
        if (c == 'Q') return 0;
        else if (c == 'W') return 1;
        else if (c == 'E') return 2;
        else if (c == 'R') return 3;
        else return -1;
    }
}
