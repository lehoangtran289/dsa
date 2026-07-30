package leetcode.array.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class H_218_TheSkylineProblem {
    private static final int START = 0;
    private static final int END = 1;

    static void main() {
        System.out.println(getSkyline(new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}}));
        // Output: [[2, 10], [3, 15], [7, 12], [12, 0], [15, 10], [20, 8], [24, 0]]
    }

    /**
     * Idea: Line sweep, iterate through all events (start/end of buildings)
     * and maintain a max heap to track the current max height (with lazy deletion).
     * ---
     * TC: O(n log n) - due to sorting and heap operations
     * SC: O(n) - for the heap and events list
     */
    public static List<List<Integer>> getSkyline(int[][] buildings) {
        List<int[]> events = new ArrayList<>();
        for (int i = 0; i < buildings.length; ++i) {
            int[] b = buildings[i];
            events.add(new int[]{b[0], i, START}); // [pos, id, status]
            events.add(new int[]{b[1], i, END});
        }
        events.sort((a, b) -> Integer.compare(a[0], b[0])); // sort by pos

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0])); // [height, end]
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < events.size(); ++i) {
            int curPos = events.get(i)[0];

            // process all events at this pos
            while (i < events.size() && events.get(i)[0] == curPos) {
                if (events.get(i)[2] == START) {
                    int bId = events.get(i)[1];
                    int endPos = buildings[bId][1];
                    int height = buildings[bId][2];

                    maxHeap.add(new int[]{height, endPos});
                }
                i++;
            }
            i--;

            // lazy deletion
            while (!maxHeap.isEmpty() && maxHeap.peek()[1] <= curPos) {
                maxHeap.poll();
            }

            // get max height at pos
            int curMaxHeight = maxHeap.isEmpty() ? 0 : maxHeap.peek()[0];
            if (res.isEmpty() || curMaxHeight != res.getLast().get(1)) {
                res.add(Arrays.asList(curPos, curMaxHeight));
            }
        }

        return res;
    }

    // --------------------------------------------------------------------


    /**
     * Brute force. Idea: transform left & right index of building to 0-index array heights
     * ---
     * TC: O(n^2)
     * SC: O(n)
     */
    public static List<List<Integer>> getSkyline1(int[][] buildings) {
        TreeSet<Integer> sortedEdges = new TreeSet<>(); // O(nlogn)
        for (int[] b : buildings) {
            sortedEdges.add(b[0]);
            sortedEdges.add(b[1]);
        }

        List<Integer> edges = new ArrayList<>(sortedEdges);
        Map<Integer, Integer> edgeIndexMap = new HashMap<>();

        for (int i = 0; i < edges.size(); ++i) { // O(n)
            edgeIndexMap.put(edges.get(i), i);
        }

        int[] heights = new int[edges.size()];

        for (int[] b : buildings) { // O(n * n)
            int indexLeft = edgeIndexMap.get(b[0]);
            int indexRight = edgeIndexMap.get(b[1]);

            // check right-exclusive range [indexLeft, indexRight) since we want to catch where height change.
            for (int i = indexLeft; i < indexRight; ++i) {
                heights[i] = Math.max(heights[i], b[2]);
            }
        }

        List<List<Integer>> res = new ArrayList<>();

        res.add(Arrays.asList(edges.getFirst(), heights[0]));
        for (int i = 1; i < heights.length; ++i) {
            if (heights[i] != heights[i - 1]) {
                res.add(Arrays.asList(edges.get(i), heights[i]));
            }
        }

        return res;
    }
}
