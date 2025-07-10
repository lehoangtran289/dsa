package leetcode.string;

public class M_443_StringCompression {

    /**
     * count chars and append to StringBuilder
     * -------------------
     * TC: O(n)
     * SC: O(1) - in-place
     */
    public int compress(char[] chars) {
        StringBuilder res = new StringBuilder();
        char curChar = chars[0];
        int count = 0;

        for (char c : chars) {
            if (c == curChar) {
                count++;
            } else {
                res.append(curChar);
                if (count > 1) res.append(count);

                count = 1;
                curChar = c;
            }
        }
        // process remaining
        res.append(curChar);
        if (count > 1) res.append(count);

        // build output
        for (int i = 0; i < Math.min(res.length(), chars.length); ++i) {
            chars[i] = res.charAt(i);
        }
        return res.length();
    }
}
