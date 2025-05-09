package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class M_2685_CountTheNumberOfCompleteComponents {
    public static void main(String[] args) {
        System.out.println(countCompleteComponents(
                6,
                new int[][]{{0, 1}, {0, 2}, {1, 2}, {3, 4}}
        ));
    }

    public static int countCompleteComponents(int n, int[][] edges) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        int res = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; ++i) {
            if (visited[i]) continue;

            // find component using BFS
            List<Integer> component = new ArrayList<>();
            Queue<Integer> queue = new ArrayDeque<>();
            component.add(i);
            queue.add(i);

            while (!queue.isEmpty()) {
                int curNode = queue.poll();
                visited[curNode] = true;

                for (int neighbor : adj[curNode]) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        component.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }

            // check if component valid by checking if size of adj = component size - 1
            boolean isValid = true;
            for (int node : component) {
                if (adj[node].size() != component.size() - 1) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) res++;
        }

        return res;
    }
}
