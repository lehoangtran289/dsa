package leetcode.string;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class M_2182_ConstructStringWithRepeatLimit {
    public static void main(String[] args) {
        System.out.println(repeatLimitedString("aababab", 2));
        System.out.println(repeatLimitedString("cczazcc", 3));
    }

    public static String repeatLimitedString(String s, int repeatLimit) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> b - a);
        pq.addAll(map.keySet());

        StringBuilder res = new StringBuilder();
        while (!pq.isEmpty()) {
            char cur = pq.poll();
            int curFreq = map.get(cur);

            if (curFreq <= repeatLimit) {
                for (int i = 0; i < curFreq; ++i) {
                    res.append(cur);
                }
                map.remove(cur);
            } else {
                for (int i = 0; i < repeatLimit; ++i) {
                    res.append(cur);
                }
                map.put(cur, curFreq - repeatLimit);

                if (!pq.isEmpty() && pq.peek() != cur) {
                    char next = pq.poll();
                    int nextFreq = map.get(next);
                    res.append(next);

                    int nextCharRemain = nextFreq - 1;
                    if (nextCharRemain > 0) {
                        map.put(next, nextFreq - 1);
                        pq.add(next);
                    } else {
                        map.remove(next);
                    }
                    pq.add(cur);
                }
            }
        }

        return res.toString();
    }
}
