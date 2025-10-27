package leetcode.string;

public class M_2125_NumberOfLaserBeamsInABank {

    /**
     * Simulation
     * -------------------------------
     * TC: O(n * m) where n is the number of rows and m is the number of columns in the bank.
     * SC: O(1)
     */
    public int numberOfBeams(String[] bank) {
        int res = 0;
        int prev = 0, cur = 0;

        for (String b : bank) {
            cur = count(b);
            if (cur == 0) continue;

            res += prev * cur;
            prev = cur;
        }

        return res;
    }

    private int count(String s) {
        int res = 0;
        for (char c : s.toCharArray()) {
            res += c - '0';
        }
        return res;
    }
}
