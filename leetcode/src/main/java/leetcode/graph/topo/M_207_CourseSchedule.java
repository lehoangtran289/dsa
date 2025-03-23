package leetcode.graph.topo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

/**
 * TOPOLOGICAL SORT
 */
public class M_207_CourseSchedule {
    public static void main(String[] args) {
        System.out.println(canFinish(3, new int[][]{{1, 0}, {0, 1}, {0, 2}}));
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }

        // init in-degree arr
        int[] indegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            indegree[prerequisite[0]]++;
            adj[prerequisite[1]].add(prerequisite[0]);
        }

        // init queue with all vertices with in-degree = 0
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; ++i) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            order.add(u);
            for (int v : adj[u]) {
                indegree[v]--;
                if (indegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        return order.size() == numCourses;

    }

    // Given a an acyclic graph `g` represented as a adjacency list, return a
    // topological ordering on the nodes of the graph.
    public int[] kahns(List<List<Integer>> g) {
        int n = g.size();

        // Calculate the in-degree of each node.
        int[] inDegree = new int[n];
        for (List<Integer> edges : g) {
            for (int to : edges) {
                inDegree[to]++;
            }
        }

        // q always contains the set nodes with no incoming edges.
        Queue<Integer> q = new ArrayDeque<>();

        // Find all start nodes.
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        int index = 0;
        int[] order = new int[n];
        while (!q.isEmpty()) {
            int at = q.poll();
            order[index++] = at;
            for (int to : g.get(at)) {
                inDegree[to]--;
                if (inDegree[to] == 0) {
                    q.offer(to);
                }
            }
        }
        if (index != n) {
            throw new IllegalArgumentException("Graph is not acyclic! Detected a cycle.");
        }
        return order;
    }

    public boolean canFinishKahns(int numCourses, int[][] prerequisites) {
        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            g.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            g.get(edge[0]).add(edge[1]);
        }
        if (kahns(g) == null) return false;
        return kahns(g).length == numCourses;
    }
}
