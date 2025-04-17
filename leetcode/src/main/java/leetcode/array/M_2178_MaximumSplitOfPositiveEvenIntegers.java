package leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class M_2178_MaximumSplitOfPositiveEvenIntegers {
    public static void main(String[] args) {
        System.out.println(maximumEvenSplit(12)); // [2, 4, 6]
        System.out.println(maximumEvenSplit(7)); // []
        System.out.println(maximumEvenSplit(28)); // [2, 4, 6, 16]
    }

    public static List<Long> maximumEvenSplit(long finalSum) {
        if ((finalSum & 1) == 1) return new ArrayList<>();

        List<Long> res = new ArrayList<>();
        long curSum = 0;
        long num = 2;

        // greedy and find breaking point
        while (curSum < finalSum) {
            curSum += num;
            res.add(num);
            num += 2;
        }

        if (curSum == finalSum) return res;

        // add remaining to last number to avoid duplication
        int lastIndex = res.size() - 1;
        long lastNum = res.get(lastIndex);
        res.remove(lastIndex);
        curSum -= lastNum;

        // update last number
        long remaining = finalSum - curSum;
        res.set(lastIndex - 1, res.get(lastIndex - 1) + remaining);

        return res;
    }
}
