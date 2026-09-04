package leetcode.graph.topo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * TOPOLOGICAL SORT, can be used to detect cycle in directed graph (if we can't visit all nodes, there is a cycle)
 */
public class M_207_CourseSchedule {
    static void main() {
        System.out.println(canFinish(3, new int[][]{{1, 0}, {0, 1}, {0, 2}}));
    }

    public static boolean canFinish(int n, int[][] prerequisites) {
        List<Integer>[] adj = new List[n];
        int[] indegree = new int[n];

        // init adjacent list
        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList<>();
        }

        // build indegree array and adj list
        for (int[] p : prerequisites) {
            int from = p[0], to = p[1];
            adj[from].add(to);
            indegree[to]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();

        // init queue with 0 indegree
        for (int i = 0; i < n; ++i) {
            if (indegree[i] == 0) queue.add(i);
        }

        // process
        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int course = queue.poll();
            result.add(course);

            for (int nextCourse : adj[course]) {
                indegree[nextCourse]--;

                if (indegree[nextCourse] == 0) {
                    queue.add(nextCourse);
                }
            }
        }

        return result.size() == n;
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
