package leetcode.array;

public class M_3085_MinimumDeletionsToMakeStringKSpecial {
    public static void main(String[] args) {
        System.out.println(minimumDeletions("aabcaba", 0)); // 3
    }

    /**
     * Counting
     * Idea: count frequency of each char. Then choose each char as a base frequency. Delete chars from other frequency so that it falls between [base, base + k]
     * ---------------------
     * TC: O(n)
     * SC: O(1)
     */
    public static int minimumDeletions(String word, int k) {
        // count frequency of each char
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        // process deletion if cur freq - min freq > k
        int res = Integer.MAX_VALUE;

        for (int base : freq) {
            if (base == 0) continue;

            int deleted = 0;

            for (int other : freq) {
                if (other == 0) continue;

                if (other < base) { // other frequency < base frequency -> delete all
                    deleted += other;
                } else if (other > base + k) {
                    deleted += (other - base - k);
                }
            }

            res = Math.min(res, deleted);
        }

        return res;
    }
}
