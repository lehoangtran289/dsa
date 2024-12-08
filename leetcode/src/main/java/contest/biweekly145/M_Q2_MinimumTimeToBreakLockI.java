package contest.biweekly145;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class M_Q2_MinimumTimeToBreakLockI {
    public static void main(String[] args) {
        System.out.println(findMinimumTime(Arrays.asList(3, 4, 1), 1)); // 4
        System.out.println(findMinimumTime(Arrays.asList(2, 5, 4), 2)); // 5
    }

    public static int findMinimumTime(List<Integer> strength, int K) {
        List<Integer> idx = new ArrayList<>();
        for (int i = 0; i < strength.size(); i++) {
            idx.add(i);
        }

        int res = Integer.MAX_VALUE;
        for (List<Integer> ids : permute(idx)) {
            res = Math.min(res, simulateTime(strength, ids, K));
        }

        return res;
    }

    private static int simulateTime(List<Integer> strength, List<Integer> ids, int K) {
        int time = 0;
        int energy;
        int x = 1;

        for (int id : ids) {
            energy = 0;
            while (energy < strength.get(id)) {
                energy += x;
                time++;
            }
            x += K;
        }

        return time;
    }

    private static List<List<Integer>> permute(List<Integer> locks) {
        List<List<Integer>> result = new ArrayList<>();
        permuteHelper(locks, 0, result);
        return result;
    }

    private static void permuteHelper(List<Integer> locks, int start, List<List<Integer>> result) {
        if (start == locks.size()) {
            result.add(new ArrayList<>(locks));
            return;
        }

        for (int i = start; i < locks.size(); i++) {
            Collections.swap(locks, start, i);
            permuteHelper(locks, start + 1, result);
            Collections.swap(locks, start, i); // Backtrack
        }
    }
}
