package leetcode.graph.topo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class M_210_CourseScheduleII {

    /**
     * Topo sort
     * ---
     * TC: O(V + E), V = numCourses, E = prerequisites.length
     * SC: O(V + E)
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        int[] indegree = new int[numCourses];

        for (int i = 0; i < numCourses; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int[] prereq : prerequisites) {
            int from = prereq[1], to = prereq[0];
            indegree[to]++;
            graph[from].add(to);
        }

        int[] res = new int[numCourses];
        int resIndex = 0;
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < numCourses; ++i) {
            if (indegree[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int curCourse = queue.poll();
            res[resIndex++] = curCourse;

            for (int nextCourse : graph[curCourse]) {
                indegree[nextCourse]--;
                if (indegree[nextCourse] == 0) {
                    queue.add(nextCourse);
                }
            }
        }

        return resIndex == numCourses ? res : new int[]{};
    }
}
