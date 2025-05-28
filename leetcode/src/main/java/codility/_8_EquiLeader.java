package codility;

import java.util.HashMap;
import java.util.Map;

public class _8_EquiLeader {
    public int solution(int[] A) {
        int n = A.length;

        // find leader and its frequency
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : A) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int leader = 0, leaderCount = 0;
        boolean isLeaderFound = false;

        for (int num : A) {
            int count = freq.get(num);
            if (count > n / 2) {
                leader = num;
                leaderCount = count;
                isLeaderFound = true;
            }
        }

        if (!isLeaderFound) return 0;

        // find equiLeader
        int res = 0;
        int curCount = 0;

        for (int i = 0; i < n; ++i) {
            if (A[i] == leader) {
                curCount++;
            }

            int leftLength = i + 1;
            int rightLength = n - leftLength;

            if (
                    curCount > leftLength / 2 &&
                    (leaderCount - curCount) > rightLength / 2
            ) {
                res++;
            }
        }

        return res;
    }
}
