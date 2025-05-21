package leetcode.graph.topo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class M_1136_ParallelCourses {

    /**
     * Topo sort
     * ------------------
     * TC: O(V + E)
     * SC: O(V + E)
     */
    public int minimumSemesters(int n, int[][] relations) {
        // init adjacent list
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList<>();
        }

        // init indegree array
        int[] indegree = new int[n];
        for (int[] r : relations) {
            int from = r[0] - 1, to = r[1] - 1;
            indegree[to]++;
            adj[from].add(to);
        }

        // process topo sort
        int res = 0;
        int sortedCourses = 0;
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < n; ++i) {
            if (indegree[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();

            for (int i = 0; i < size; ++i) {
                int u = queue.poll();
                sortedCourses++;

                for (int v : adj[u]) {
                    indegree[v]--;
                    if (indegree[v] == 0) queue.add(v);
                }
            }
        }

        return sortedCourses != n ? -1 : res;
    }
}
