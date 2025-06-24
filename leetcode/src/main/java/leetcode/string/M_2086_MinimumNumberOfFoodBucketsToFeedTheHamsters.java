package leetcode.string;

public class M_2086_MinimumNumberOfFoodBucketsToFeedTheHamsters {
    public static void main(String[] args) {
        System.out.println(minimumBuckets("H..H")); // 2
    }

    /**
     * Greedy approach.
     * Idea: Count the number of hamsters and check for overlaps.
     * ---------------------
     * TC: O(N)
     * SC: O(1)
     */
    public static int minimumBuckets(String hamsters) {
        char[] arr = hamsters.toCharArray();
        int n = arr.length;

        // base cases
        if (
                hamsters.equals("H") ||
                hamsters.startsWith("HH") ||
                hamsters.endsWith("HH") ||
                hamsters.contains("HHH")
        ) return -1;

        // count all H
        int hCount = 0;
        for (char c : arr) {
            if (c == 'H') hCount++;
        }

        // count overlap hamster
        int overlapCount = 0;
        for (int i = 0; i < n - 2; ++i) {
            if (arr[i] == 'H' && arr[i + 1] == '.' && arr[i + 2] == 'H') {
                overlapCount++;
                i += 2;
            }
        }

        return hCount - overlapCount;
    }
}
