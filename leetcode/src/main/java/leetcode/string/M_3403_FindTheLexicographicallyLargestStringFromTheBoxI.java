package leetcode.string;

public class M_3403_FindTheLexicographicallyLargestStringFromTheBoxI {

    /**
     * split into num words
     * largest lexicographical has length <= word.length() - num + 1
     * Idea: Find sub-string with length <= (word.length() - num + 1) that has largest lexico
     * ---------
     * TC: O(n^2)
     * SC: O(1)
     */
    public String answerString(String word, int num) {
        int n = word.length();
        if (num == 1) return word;

        int maxLength = n - num + 1;
        String res = "";

        for (int i = 0; i < n; ++i) {
            String curStr = word.substring(i, Math.min(i + maxLength, n));

            if (curStr.compareTo(res) >= 0) {
                res = curStr;
            }
        }

        return res;
    }

    private int compare(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();

        int p1 = 0, p2 = 0;
        while (p1 < len1 && p2 < len2) {
            if (s1.charAt(p1) > s2.charAt(p2)) return 1;
            else if (s1.charAt(p1) < s2.charAt(p2)) return -1;

            p1++;
            p2++;
        }

        if (p1 > len1 && p2 > len1) return 0;
        return p1 < len1 ? 1 : -1;
    }
}
