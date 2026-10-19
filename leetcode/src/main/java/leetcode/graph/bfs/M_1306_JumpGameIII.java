package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class M_1306_JumpGameIII {
    static void main() {
        System.out.println(canReach(new int[]{4, 2, 3, 0, 3, 1, 2}, 5)); // true
    }

    /**
     * Idea: transform to graph problem, root = start point
     * ---
     * TC: O(N) - visit every index at most once
     * SC: O(N) - store nodes
     */
    public static boolean canReach(int[] arr, int start) {
        int n = arr.length;

        // init BFS states
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int curIndex = queue.poll();
            if (arr[curIndex] == 0) return true;

            int rightIndex = curIndex + arr[curIndex];
            int leftIndex = curIndex - arr[curIndex];

            if (rightIndex < n && !visited[rightIndex]) {
                queue.add(rightIndex);
                visited[rightIndex] = true;
            }

            if (leftIndex >= 0 && !visited[leftIndex]) {
                queue.add(leftIndex);
                visited[leftIndex] = true;
            }
        }

        return false;
    }
}
