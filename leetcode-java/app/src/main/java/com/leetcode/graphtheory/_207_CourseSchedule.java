package com.leetcode.graphtheory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class _207_CourseSchedule {
    public static void main(String[] args) {
         System.out.println(canFinish(2, new int[][]{{1, 0}}));
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }

        // init in-degree arr
        int[] indegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int prev = prerequisite[0];
            int next = prerequisite[1];
            indegree[next]++;
            adj[prev].add(next);
        }

        // init queue with all vertices with in-degree = 0
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; ++i) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        int count = 0;
        while(!queue.isEmpty()){
            int u = queue.poll();
            count++;
            for (int i = 0; i < adj[u].size(); ++i) {
                int v = adj[u].get(i);
                indegree[v]--;
                if (indegree[v] == 0) {
                    queue.add(v);
                }
            }
        }

        return count == numCourses;

    }
}
