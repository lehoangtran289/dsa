package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeSet;

public class M_3607_PowerGridMaintenance {

    /**
     * Map < station, groupId >
     * Map < groupId, TreeSet< online-station > >
     */
    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        // build graph
        List<Integer>[] adj = new List[c + 1];

        for (int i = 0; i <= c; ++i) {
            adj[i] = new ArrayList<>();
        }

        for (int[] connection : connections) {
            adj[connection[0]].add(connection[1]);
            adj[connection[1]].add(connection[0]);
        }

        // build Map < station, groupId >
        // build Map < groupId, TreeSet< online-station > >
        Map<Integer, Integer> stationGroupMap = new HashMap<>();
        Map<Integer, TreeSet<Integer>> groupStationsMap = new HashMap<>();
        bfs(c, adj, stationGroupMap, groupStationsMap);

        // process query
        List<Integer> res = new ArrayList<>();

        for (int[] query : queries) {
            int command = query[0];
            int station = query[1];
            int groupId = stationGroupMap.get(station);
            TreeSet<Integer> stationGroup = groupStationsMap.get(groupId);

            if (command == 1) {
                if (stationGroup.contains(station)) {
                    res.add(station);
                } else {
                    res.add(stationGroup.isEmpty() ? -1 : stationGroup.first());
                }
            } else {
                stationGroup.remove(station);
            }
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * BFS to find connected components (groups) in the graph
     * ------------------------------------------------
     * TC: O(c log c + edges)
     */
    private void bfs(
            int c,
            List<Integer>[] adj,
            Map<Integer, Integer> stationGroupMap,
            Map<Integer, TreeSet<Integer>> groupStationsMap
    ) {
        int groupId = 0;

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[c + 1];

        for (int station = 1; station <= c; ++station) {
            if (visited[station]) continue;

            visited[station] = true;
            queue.add(station);

            groupId++;
            stationGroupMap.put(station, groupId);

            groupStationsMap.putIfAbsent(groupId, new TreeSet<>());
            groupStationsMap.get(groupId).add(station);

            while (!queue.isEmpty()) {
                int curStation = queue.poll();

                for (int nextStation : adj[curStation]) {
                    if (!visited[nextStation]) {
                        visited[nextStation] = true;
                        queue.add(nextStation);

                        stationGroupMap.put(nextStation, groupId);
                        groupStationsMap.get(groupId).add(nextStation);
                    }
                }
            }
        }
    }
}
