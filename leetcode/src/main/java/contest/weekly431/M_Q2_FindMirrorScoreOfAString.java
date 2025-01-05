package contest.weekly431;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class M_Q2_FindMirrorScoreOfAString {
    public static void main(String[] args) {
        System.out.println(calculateScore("aaczzx")); //9
        System.out.println(calculateScore("abcdef")); //0
        System.out.println(calculateScore("aaazzz")); //9
    }

    public static long calculateScore(String s) {
        int len = s.length();
        int[] marks = new int[len];
        long score = 0;

        Map<Character, Stack<Integer>> map = new HashMap<>();
        for (int i = 0; i < len; ++i) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, new Stack<>());
            }
            map.get(c).add(i);

            if (map.containsKey(mirror(c))) {
                Stack<Integer> pq = map.get(mirror(c));
                if (!pq.isEmpty()) {
                    int idx = pq.pop();
                    if (marks[i] == 1 || marks[idx] == 1) {
                        continue;
                    }
                    marks[i] = 1;
                    marks[idx] = 1;
                    score += Math.abs(i - idx);
                }
            }
        }

        return score;
    }

    private static char mirror(char c) {
        return (char) ('z' - (c - 'a'));
    }
}
