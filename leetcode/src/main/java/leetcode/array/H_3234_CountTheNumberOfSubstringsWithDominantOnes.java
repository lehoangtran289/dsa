package leetcode.array;

public class H_3234_CountTheNumberOfSubstringsWithDominantOnes {

    /**
     * Simulation
     * Mind twisted problem :v
     * --------------------------------
     * TC: O(n * sqrt(n))
     * SC: O(n)
     * --------------------------------
     */
    public int numberOfSubstrings(String s) {
        int n = s.length();

        // store last zero index before i-th
        int[] preZero = new int[n];
        int curPreZero = -1;
        for (int i = 0; i < n; ++i) {
            preZero[i] = curPreZero;
            if (s.charAt(i) == '0') {
                curPreZero = i;
            }
        }

        int res = 0;

        // consider substring of form: (prevZero)0 11..11 0/1(i)
        for (int i = 0; i < n; i++) {
            int zeroCount = s.charAt(i) == '0' ? 1 : 0;
            int j = i;

            // process to the left of j
            while (j >= 0 && zeroCount * zeroCount <= n) {
                int prevZero = preZero[j];
                int oneCount = i - prevZero - zeroCount;

                // process if valid constraint
                if (oneCount >= zeroCount * zeroCount) {
                    res += Math.min(
                            j - prevZero - 1, // number of positions in (prevZero, j]
                            oneCount - zeroCount * zeroCount + 1 // number of valid positions left
                    );
                }

                // jump to prev zero
                j = prevZero;
                zeroCount++;
            }
        }

        return res;
    }
}
