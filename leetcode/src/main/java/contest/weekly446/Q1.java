package contest.weekly446;

import java.util.HashSet;
import java.util.Set;

public class Q1 {
    public static void main(String[] args) {
        System.out.println(calculateScore(new String[]{"jump", "add", "add", "jump", "add", "jump"}, new int[]{2, 1, 3, 1, -2, -3}));
    }

    public static long calculateScore(String[] inst, int[] values) {
        Set<Integer> seen = new HashSet<>();
        int n = inst.length;
        long res = 0;

        int i = 0;
        while (0 <= i && i < n) {
            if (seen.contains(i)) break;

            if (inst[i].equals("add")) {
                res += values[i];
                seen.add(i);
                i++;
            } else if (inst[i].equals("jump")) {
                seen.add(i);
                i += values[i];
            }
        }

        return res;
    }
}
