package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class H_815_BusRoutes {

    static void main() {
        System.out.println(numBusesToDestination1(new int[][]{{1, 2, 7}, {3, 6, 7}}, 1, 6)); // 2
    }

    /**
     * Idea: Build map of stop -> routes[]
     * BFS on route, start with all routes of "source stop".
     *      For a route, traverse all of its stops. If a route contains "target stop" -> return
     *          From a stop, find neighbor route from the prebuilt map
     * ---
     * TC: O(M^2 * K), where M = routes.length, K = max routes.length
     *      O(M * K) = build map
     *      O(M * K * M), for each route traverse all stops, for each stop find all neighbor routes.
     * SC: O(M * K)
     */
    public static int numBusesToDestination1(int[][] routes, int source, int target) {
        // base case
        if (source == target) return 0;

        // build mapping of stop -> Route[].
        int n = routes.length;
        Map<Integer, List<Integer>> stopToRoute = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            for (int stop : routes[i]) {
                stopToRoute.putIfAbsent(stop, new ArrayList<>());
                stopToRoute.get(stop).add(i);
            }
        }

        // base cases
        if (!stopToRoute.containsKey(source) || !stopToRoute.containsKey(target)) {
            return -1;
        }

        // BFS on routes. O(M * K * M), for each route traverse all stops, for each stop find all neighbor routes.
        int res = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visitedRoutes = new boolean[n];

        for (int routeId : stopToRoute.get(source)) {
            queue.add(routeId);
            visitedRoutes[routeId] = true;
        }

        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();

            while (size-- > 0) {
                int curRouteId = queue.poll();

                for (int stop : routes[curRouteId]) {
                    if (stop == target) return res;

                    for (int nextRouteId : stopToRoute.get(stop)) {
                        if (visitedRoutes[nextRouteId]) continue;

                        visitedRoutes[nextRouteId] = true;
                        queue.add(nextRouteId);
                    }
                }
            }
        }

        return -1;
    }

    // ----------------------------------------------------

    /**
     * Intuition, build graph based on route
     */
    public int numBusesToDestination(int[][] routes, int source, int target) {
        // base case
        if (source == target) return 0;

        // build mapping of stop -> Route[]
        int n = routes.length;
        List<Route>[] graph = new List[n];
        Map<Integer, List<Route>> stopToRoute = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; ++i) {
            Route route = new Route(i, toSet(routes[i]));

            for (int j = 0; j < routes[i].length; ++j) {
                stopToRoute.putIfAbsent(routes[i][j], new ArrayList<>());
                stopToRoute.get(routes[i][j]).add(route);
            }
        }

        // base cases
        if (!stopToRoute.containsKey(source) || !stopToRoute.containsKey(target)) {
            return -1;
        }

        for (Route sourceRoute : stopToRoute.get(source)) {
            if (sourceRoute.stops.contains(target)) {
                return 1;
            }
        }

        // build graph of Route
        for (var entry : stopToRoute.entrySet()) {
            List<Route> routeList = entry.getValue();

            if (routeList.size() == 1) continue;

            for (int i = 0; i < routeList.size(); ++i) {
                for (int j = 0; j < routeList.size(); ++j) {
                    if (i == j) continue;

                    graph[routeList.get(i).id].add(routeList.get(j));
                }
            }
        }

        // BFS
        int res = 0;
        Queue<Route> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];

        for (Route route : stopToRoute.get(source)) {
            queue.add(route);
            visited[route.id] = true;
        }

        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();

            while (size-- > 0) {
                Route cur = queue.poll();
                if (cur.stops.contains(target)) return res;

                for (Route neighbor : graph[cur.id]) {
                    if (!visited[neighbor.id]) {
                        queue.add(neighbor);
                        visited[neighbor.id] = true;
                    }
                }
            }
        }

        return -1;
    }

    private Set<Integer> toSet(int[] arr) {
        Set<Integer> res = new HashSet<>();
        for (int num : arr) res.add(num);
        return res;
    }

    static class Route {
        int id;
        Set<Integer> stops;

        Route(int id, Set<Integer> stops) {
            this.id = id;
            this.stops = stops;
        }

        public String toString() {
            return id + "";
        }
    }
}
