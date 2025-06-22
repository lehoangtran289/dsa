package leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E_2138_DivideAStringIntoGroupsOfSizeK {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(divideString("abcdefghi", 3, 'x')));
        System.out.println(Arrays.toString(divideString2("abcdefghij", 3, 'x')));
    }

    /**
     * Pre-process -> build groups of size k
     * ---------------------
     * TC: O(n)
     * SC: O(k)
     */
    public static String[] divideString(String s, int k, char fill) {
        // pre-process s to align with k
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() % k != 0) {
            sb.append(fill);
        }

        // build result base on sb
        String[] res = new String[sb.length() / k];
        int curIndex = 0;

        int i = 0;
        while (i < sb.length()) {
            res[curIndex++] = sb.substring(i, i + k);
            i += k;
        }

        return res;
    }

    /**
     * build groups -> update last element
     * ---------------------
     * TC: O(n)
     * SC: O(k)
     */
    public static String[] divideString2(String s, int k, char fill) {
        List<String> res = new ArrayList<>();

        // build groups of size k
        int cur = 0;
        while (cur < s.length()) {
            res.add(s.substring(cur, Math.min(cur + k, s.length())));
            cur += k;
        }

        // process last element
        StringBuilder last = new StringBuilder(res.get(res.size() - 1));
        while (last.length() < k) {
            last.append(fill);
        }
        res.set(res.size() - 1, last.toString());

        return res.toArray(new String[0]);
    }
}
