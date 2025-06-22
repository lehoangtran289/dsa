package leetcode.string;

public class M_1525_NumberOfGoodWaysToSplitAString {
    public static void main(String[] args) {
        System.out.println(numSplits("aacaba")); // 2
        System.out.println(numSplits("abcd")); // 1
        System.out.println(numSplits("aaaaa")); // 4
    }

    /**
     * count frequency
     * maintain a left and right frequency count, adjust while traversing from left to right
     * ----------------------
     * TC: O(n)
     * SC: O(1)
     */
    public static int numSplits(String s) {
        int n = s.length();
        int res = 0;

        // count frequency in s
        int[] rightFreq = new int[26];
        int[] leftFreq = new int[26];
        int rightDistincts = 0;
        int leftDistincts = 0;

        // calculate right frequency and distinct number in right array
        for (char c : s.toCharArray()) {
            if (rightFreq[c - 'a'] == 0) rightDistincts++;
            rightFreq[c - 'a']++;
        }

        // traverse to calculate left frequency
        for (char c : s.toCharArray()) {
            // increase left
            if (leftFreq[c - 'a'] == 0) leftDistincts++;
            leftFreq[c - 'a']++;

            // decrease right
            rightFreq[c - 'a']--;
            if (rightFreq[c - 'a'] == 0) rightDistincts--;

            // check if it is a good split
            if (leftDistincts == rightDistincts) res++;
        }

        return res;
    }
}
