package leetcode.dp;

import java.util.ArrayList;
import java.util.List;

public class M_2178_MaximumSplitOfPositiveEvenIntegers {
    public static void main(String[] args) {
        System.out.println(new M_2178_MaximumSplitOfPositiveEvenIntegers().maximumEvenSplit(40));
        System.out.println(new M_2178_MaximumSplitOfPositiveEvenIntegers().maximumEvenSplit(12));
        System.out.println(new M_2178_MaximumSplitOfPositiveEvenIntegers().maximumEvenSplit(7));
        System.out.println(new M_2178_MaximumSplitOfPositiveEvenIntegers().maximumEvenSplit(28));
        System.out.println(new M_2178_MaximumSplitOfPositiveEvenIntegers().maximumEvenSplit(90));
        System.out.println(new M_2178_MaximumSplitOfPositiveEvenIntegers().maximumEvenSplit(2));
    }

    private long finalSum;
    private List<Long> res;

    public List<Long> maximumEvenSplit(long finalSum) {
        if (finalSum % 2 != 0) return new ArrayList<>();

        this.finalSum = finalSum;
        this.res = new ArrayList<>();
        List<Long> cur = new ArrayList<>();

        backtrack(cur, finalSum, 2);

        return res;
    }

    private void backtrack(
            List<Long> cur,
            long curSum,
            long start
    ) {
        if (curSum == 0) {
            res = new ArrayList<>(cur);
            return;
        }

        for (long i = start + 2; i <= finalSum; i += 2) {
            cur.add(i);

            backtrack(cur, curSum - i, i + 2);
            if (!res.isEmpty()) return;

            // backtrack
            cur.remove(cur.size() - 1);
        }
    }
}
