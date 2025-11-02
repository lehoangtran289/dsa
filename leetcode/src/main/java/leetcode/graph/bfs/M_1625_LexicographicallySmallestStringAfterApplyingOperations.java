package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class M_1625_LexicographicallySmallestStringAfterApplyingOperations {
    public static void main(String[] args) {
        System.out.println(findLexSmallestString("5525", 9, 2)); // "2050");
    }

    /**
     * BFS
     */
    public static String findLexSmallestString(String s, int a, int b) {
        String minStr = s;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        visited.add(s);
        queue.add(s);

        while (!queue.isEmpty()) {
            String cur = queue.poll();
            if (cur.compareTo(minStr) < 0) minStr = cur;

            String addedStr = addToString(cur, a);
            if (!visited.contains(addedStr)) {
                visited.add(addedStr);
                queue.add(addedStr);
            }

            String rotatedStr = rotateString(cur, b);
            if (!visited.contains(rotatedStr)) {
                visited.add(rotatedStr);
                queue.add(rotatedStr);
            }
        }

        return minStr;
    }

    private static String addToString(String cur, int a) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < cur.length(); ++i) {
            char c = cur.charAt(i);
            if ((i & 1) == 1) {
                char newChar = (char) ((c - '0' + a) % 10 + '0');
                res.append(newChar);
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    private static String rotateString(String cur, int b) {
        int n = cur.length();
        int start = n - b;
        return cur.substring(start, n) + cur.substring(0, start);
    }
}
