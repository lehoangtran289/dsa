package leetcode.array.prefixSum;

public class M_2145_CountTheHiddenSequences {
    public static void main(String[] args) {
        System.out.println(numberOfArrays(new int[]{1, -3, 4}, 1, 6)); // 2
    }

    /**
     * -----------------------------------------------------------
     * Prefix sum
     * Idea: We can find the min and max prefix sum of the differences array. => range of min and max elements in array
     * -----------------------------------------------------------
     * TC: O(n)
     * SC: O(1)
     */
    public static int numberOfArrays(int[] differences, int lower, int upper) {
        long curSum = 0;
        long max = 0, min = 0;

        for (int diff : differences) {
            curSum += diff;
            max = Math.max(max, curSum);
            min = Math.min(min, curSum);
        }

        if (max - min > upper - lower) return 0;
        return (int) ((upper - lower) + 1 - (max - min));
    }
}
