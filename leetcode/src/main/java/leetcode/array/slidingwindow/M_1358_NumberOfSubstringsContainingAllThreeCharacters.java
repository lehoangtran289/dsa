package leetcode.array.slidingwindow;

public class M_1358_NumberOfSubstringsContainingAllThreeCharacters {
    public static void main(String[] args) {
        System.out.println(numberOfSubstrings("abcabc")); // 10
    }

    public static int numberOfSubstrings(String s) {
        int res = 0;
        int[] freq = new int[3];
        int l = 0;

        for (int r = 0; r < s.length(); ++r) {
            char rChar = s.charAt(r);
            freq[rChar - 'a']++;

            while (isAtLeast1(freq)) {
                res += s.length() - r;

                // try shrink left
                char lChar = s.charAt(l);
                freq[lChar - 'a']--;

                l++;
            }
        }

        return res;
    }

    private static boolean isAtLeast1(int[] freq) {
        return freq[0] > 0 && freq[1] > 0 && freq[2] > 0;
    }
}
