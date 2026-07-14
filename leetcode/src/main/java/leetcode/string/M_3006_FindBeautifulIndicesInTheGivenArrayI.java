package leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class M_3006_FindBeautifulIndicesInTheGivenArrayI {

    /**
     * String matching (indexOf) + 2 pointers
     * ---
     * n = s.length(), a = a.length(), b = b.length()
     * TC: O(n * (a + b)) + O(n)
     * SC: O(n)
     */
    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        // build all positions of a and b in s
        List<Integer> indicesA = getIndices(s, a);
        List<Integer> indicesB = getIndices(s, b);

        // traverse and build result
        // Use 2 pointers instead of binary search here
        List<Integer> res = new ArrayList<>();

        int pA = 0, pB = 0;
        while (pA < indicesA.size() && pB < indicesB.size()) {
            int i = indicesA.get(pA);
            int j = indicesB.get(pB);

            if (Math.abs(i - j) <= k) {
                res.add(i);
                pA++;
            } else if (i < j) {
                pA++;
            } else {
                pB++;
            }
        }

        return res;
    }

    private List<Integer> getIndices(String s, String target) {
        List<Integer> res = new ArrayList<>();
        int idx = s.indexOf(target); // O(s.length() * target.length())

        while (idx != -1) {
            res.add(idx);
            idx = s.indexOf(target, idx + 1);
        }
        return res;
    }

    /**
     * Since both lists are sorted, you actually don't need binary search.
     * 2 pointers can be utilized here.
     */
    @Deprecated
    private boolean isBeautiful(List<Integer> indicesB, int i, int k) {
        int l = 0, r = indicesB.size() - 1;

        while (l <= r) {
            int mid = r - (r - l) / 2;
            int j = indicesB.get(mid);

            if (Math.abs(i - j) <= k) {
                return true;
            } else if (i > j) {
                l = mid + 1;
            } else if (i < j) {
                r = mid - 1;
            }
        }

        return false;
    }
}
