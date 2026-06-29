package leetcode.string;

public class H_76_MinimumWindowSubString {
    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }

    /**
     * Sliding window. Note: only update result while shrinking "l"
     * ---
     * TC: O(|s| + |t|), where |s| is the length of string s and |t| is the length of string t
     * SC: O(1)
     */
    public static String minWindow(String s, String t) {
        int[] freqS = new int[128];
        int[] freqT = new int[128];

        for (char c : t.toCharArray()) {
            freqT[c]++;
        }

        int res = 1 << 30;
        int start = 0, end = 0;

        int l = 0;
        for (int r = 0; r < s.length(); ++r) {
            char c = s.charAt(r);
            freqS[c]++;

            while (isIncluded(freqS, freqT)) {
                if (res >= r - l + 1) {
                    res = r - l + 1;
                    start = l;
                    end = r;
                }

                char leftChar = s.charAt(l);
                freqS[leftChar]--;
                l++;
            }
        }

        return res == 1 << 30 ? "" : s.substring(start, end + 1);
    }

    private static boolean isIncluded(int[] freqS, int[] freqT) {
        for (int i = 0; i < freqT.length; ++i) {
            if (freqT[i] != 0 && freqS[i] < freqT[i]) return false;
        }
        return true;
    }
}
