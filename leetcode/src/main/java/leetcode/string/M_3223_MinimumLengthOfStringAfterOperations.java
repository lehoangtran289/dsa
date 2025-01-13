package leetcode.string;

public class M_3223_MinimumLengthOfStringAfterOperations {
    public int minimumLength(String s) {
        int[] arr = new int[26];
        for (char c : s.toCharArray()) {
            arr[c - 'a']++;
        }

        int res = 0;
        for (int cnt : arr) {
            if (cnt < 3) res += cnt;
            else res += (cnt % 2 == 1 ? 1 : 2);
        }

        return res;
    }
}
