package leetcode.string;

import java.util.ArrayDeque;
import java.util.Queue;

public class M_2337_MovePiecesToObtainAString {
    public static void main(String[] args) {
        System.out.println(canChange("_L___RR", "LRR____"));
    }

    static class Pair {
        char c;
        int idx;

        public Pair(char c, int idx) {
            this.c = c;
            this.idx = idx;
        }
    }

    public static boolean canChange(String start, String target) {
        Queue<Pair> startQueue = new ArrayDeque<>();
        Queue<Pair> targetQueue = new ArrayDeque<>();

        for (int i = 0; i < start.length(); ++i) {
            if (start.charAt(i) != '_') startQueue.add(new Pair(start.charAt(i), i));
            if (target.charAt(i) != '_') targetQueue.add(new Pair(target.charAt(i), i));
        }

        if (startQueue.size() != targetQueue.size()) return false;

        while (!startQueue.isEmpty() || !targetQueue.isEmpty()) {
            Pair startPair = startQueue.poll();
            Pair targetPair = targetQueue.poll();

            if (
                    startPair.c != targetPair.c ||
                    (startPair.c == 'L' && startPair.idx < targetPair.idx) ||
                    (startPair.c == 'R' && startPair.idx > targetPair.idx)
            ) {
                return false;
            }
        }

        return true;
    }
}
