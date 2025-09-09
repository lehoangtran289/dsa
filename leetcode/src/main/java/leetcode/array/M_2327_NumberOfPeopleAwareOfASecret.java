package leetcode.array;

public class M_2327_NumberOfPeopleAwareOfASecret {

    /**
     * Idea: maintain an array knows[n + 1], where knows[i] indicates number of ppl know at day i
     * - knows[i] people start sharing at [i + delay, i + forget]
     * - in the end, only people from [n - forget + 1, n] will know secret
     */
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        final int MOD = (int) 1e9 + 7;

        // knows[i] = number of new people knows at day i
        long[] knows = new long[n + 1];
        knows[1] = 1;

        // iterate for each day and update the range [i + delay, i + forget - 1] with new people
        for (int i = 1; i <= n; ++i) {
            int l = i + delay;
            int r = Math.min(n, i + forget - 1);

            for (int j = l; j <= r; ++j) {
                knows[j] = (knows[j] + knows[i]) % MOD;
            }
        }

        // sum up the people who still know the secret
        long res = 0;
        for (int i = n - forget + 1; i <= n; ++i) {
            res = (res + knows[i]) % MOD;
        }
        return (int) res;
    }
}
