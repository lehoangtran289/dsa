package leetcode.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class M_1462_CourseScheduleIV {
    public static void main(String[] args) {
        System.out.println(checkIfPrerequisite(
                5,
                new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}},
                new int[][]{{0, 4}, {4, 0}, {1, 3}, {3, 0}}
        ));
    }

    public static List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Integer>[] adjLst = new List[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            adjLst[i] = new ArrayList<>();
        }

        // init inDegree arr
        int[] inDegree = new int[numCourses];
        for (int[] p : prerequisites) {
            inDegree[p[1]]++;
            adjLst[p[0]].add(p[1]);
        }

        // init queue with 0 inDegree
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; ++i) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // Map key to the set of prerequisite nodes.
        Set<Integer>[] processed = new Set[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            processed[i] = new HashSet<>();
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int adj : adjLst[cur]) {
                // add to processed prerequisites
                processed[adj].add(cur);
                // add all prerequisites of cur to adj prerequisite set.
                processed[adj].addAll(processed[cur]);

                // topo sort
                inDegree[adj]--;
                if (inDegree[adj] == 0) {
                    queue.add(adj);
                }
            }
        }

        // return if q[0] is prerequisite of q[1]
        // -> check if Set<Integer> prerequisites of q[1] contains q[0]
        List<Boolean> res = new ArrayList<>();
        for (int[] q : queries) {
            res.add(processed[q[1]].contains(q[0]));
        }

        return res;
    }
}
