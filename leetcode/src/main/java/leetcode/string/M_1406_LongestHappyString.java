package leetcode.string;

import java.util.PriorityQueue;

public class M_1406_LongestHappyString {
    public static void main(String[] args) {
        System.out.println(new M_1406_LongestHappyString().longestDiverseString(1, 1, 7));
    }

    public String longestDiverseString(int a, int b, int c) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> y.count - x.count);

        if (a > 0) pq.add(new Pair('a', a));
        if (b > 0) pq.add(new Pair('b', b));
        if (c > 0) pq.add(new Pair('c', c));

        StringBuilder ans = new StringBuilder();

        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            int count = p.count;
            char character = p.character;

            // If three consecutive characters exists, pick the second most
            // frequent character.
            if (
                    ans.length() >= 2 &&
                    ans.charAt(ans.length() - 1) == p.character &&
                    ans.charAt(ans.length() - 2) == p.character
            ) {
                if (pq.isEmpty()) break;

                Pair temp = pq.poll();
                ans.append(temp.character);
                if (temp.count - 1 > 0) {
                    pq.add(new Pair(temp.character, temp.count - 1));
                }
            } else {
                count--;
                ans.append(character);
            }

            // If count is greater than zero, add it to priority queue.
            if (count > 0) {
                pq.add(new Pair(character, count));
            }
        }

        return ans.toString();
    }

    static class Pair {
        char character;
        int count;

        public Pair(char character, int count) {
            this.character = character;
            this.count = count;
        }
    }
}
