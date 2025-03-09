package leetcode.array.slidingwindow;

public class M_1100_FindKLengthSubstringsWithNoRepeatedCharacters {
    public static void main(String[] args) {
        System.out.println(numKLenSubstrNoRepeats("havefunonleetcode", 5)); // 6
    }

    public static int numKLenSubstrNoRepeats(String s, int k) {
        if (k > s.length()) return 0;

        int res = 0;
        int[] freq = new int[26];
        int l = 0;

        for (int r = 0; r < s.length(); ++r) {
            int idx = s.charAt(r) - 'a';
            freq[idx]++;

            while (l <= r && freq[idx] > 1) {
                freq[s.charAt(l) - 'a']--;
                l++;
            }

            if (r - l + 1 >= k) {
                res++;
            }
        }

        return res;
    }
}
