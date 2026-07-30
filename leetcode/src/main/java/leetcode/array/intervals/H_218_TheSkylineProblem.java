package leetcode.array.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
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
        // build event list
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < buildings.length; ++i) {
            int[] b = buildings[i];
            events.add(new Event(i, b[0], b[2], START));
            events.add(new Event(i, b[1], b[2], END));
        }
        events.sort((a, b) -> Integer.compare(a.pos, b.pos)); // sort by position

        // max heap to sort by events' height
        PriorityQueue<Event> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.height, a.height));
        Set<Integer> removedPos = new HashSet<>(); // for lazy deletion
        List<List<Integer>> res = new ArrayList<>();

        // line sweep through each position
        for (int i = 0; i < events.size(); ++i) {
            int pos = events.get(i).pos;

            // process all events at this pos
            while (i < events.size() && events.get(i).pos == pos) {
                if (events.get(i).status == START) {
                    maxHeap.add(events.get(i));
                } else {
                    removedPos.add(events.get(i).id); // remove that ID
                }
                i++;
            }
            i--;

            // Lazy deletion past events
            while (!maxHeap.isEmpty() && removedPos.contains(maxHeap.peek().id)) {
                maxHeap.poll();
            }

            // check if the current max height has changed
            int curMaxHeight = maxHeap.isEmpty() ? 0 : maxHeap.peek().height;
            if (res.isEmpty() || curMaxHeight != res.getLast().get(1)) {
                res.add(Arrays.asList(pos, curMaxHeight));
            }
        }

        return res;
    }

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

    // --------------------------------------------------------------------

    static class Event {
        int id;
        int pos;
        int height;
        int status;

        Event(int id, int pos, int height, int status) {
            this.id = id;
            this.pos = pos;
            this.height = height;
            this.status = status;
        }
    }
}
