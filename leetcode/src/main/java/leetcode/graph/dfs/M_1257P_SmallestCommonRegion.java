package leetcode.graph.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M_1257P_SmallestCommonRegion {

    static void main() {
        System.out.println(findSmallestRegion2(
                List.of(
                        List.of("Earth", "North America", "South America"),
                        List.of("North America", "United States", "Canada"),
                        List.of("United States", "New York", "Boston"),
                        List.of("Canada", "Ontario", "Quebec"),
                        List.of("South America", "Brazil")
                ),
                "Quebec",
                "New York"
        ));
    }

    /**
     * Idea: Build graph upward & find intersection of 2 linked list (leetcode 160)
     * ---
     * TC: O(m * n)
     * SC: O(m * n)
     */
    public static String findSmallestRegion2(List<List<String>> regions, String region1, String region2) {
        // build parent map (graph)
        Map<String, String> parentMap = new HashMap<>();

        for (List<String> regionList : regions) {
            String rootRegion = regionList.getFirst();
            for (int i = 1; i < regionList.size(); ++i) {
                parentMap.put(regionList.get(i), rootRegion);
            }
        }

        // Find common ancestor by 2 pointers
        // Similar to finding intersection of 2 linked list (a + (c) + b = b + (c) + a)
        String p1 = region1, p2 = region2;

        while (!p1.equals(p2)) {
            String parent1 = parentMap.get(p1);
            String parent2 = parentMap.get(p2);

            p1 = parent1 == null ? region2 : parent1;
            p2 = parent2 == null ? region1 : parent2;
        }

        return p1;
    }

    // ----------------------------------------------------------------------------------

    /**
     * Intuition, build graph downward from root
     * Idea: Build graph, find root, dfs for LCA
     * ---
     * TC: O(m * n)
     * SC: O(m * n)
     */
    public static String findSmallestRegion1(List<List<String>> regions, String region1, String region2) {
        // build region graph
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();

        for (List<String> regionList : regions) {
            String rootRegion = regionList.getFirst();
            graph.putIfAbsent(rootRegion, new ArrayList<>());
            inDegree.put(rootRegion, inDegree.getOrDefault(rootRegion, 0));

            for (int i = 1; i < regionList.size(); ++i) {
                graph.putIfAbsent(regionList.get(i), new ArrayList<>());
                graph.get(rootRegion).add(regionList.get(i));

                inDegree.put(regionList.get(i), inDegree.getOrDefault(regionList.get(i), 0) + 1);
            }
        }

        // find root region
        String rootRegion = null;
        for (var entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                rootRegion = entry.getKey();
                break;
            }
        }

        // dfs to get LCA
        return lca(graph, rootRegion, region1, region2);
    }

    private static String lca(Map<String, List<String>> graph, String root, String region1, String region2) {
        if (root.equals(region1) || root.equals(region2)) {
            return root;
        }

        List<String> found = new ArrayList<>();
        for (String child : graph.get(root)) {
            String subtree = lca(graph, child, region1, region2);
            if (subtree != null) found.add(subtree);
        }

        if (found.size() == 2) return root;
        return found.isEmpty() ? null : found.get(0);
    }
}
