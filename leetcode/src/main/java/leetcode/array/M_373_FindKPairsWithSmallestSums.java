package leetcode.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class M_373_FindKPairsWithSmallestSums {

    /**
     * Idea: first min pair = (0, 0)
     *      -> next min pair = min((0, 0 + 1), (0 + 1, 0)), choose 1 and keep 1 for next iteration. Assume min = (0, 1)
     *      -> next min pair = min((0 + 1, 1), (0, 1 + 1), and (1, 0))
     * -> Maintain a minheap to choose next min pair and 2 new min pair generated from it.
     * ---
     * TC: O(m n log(mn))
     * SC: O(m n)
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();

        // minHeap to maintain pointers to 2 arrays ~ [p1, p2]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) ->
                Integer.compare(nums1[a[0]] + nums2[a[1]], nums1[b[0]] + nums2[b[1]])
        );
        Set<List<Integer>> visited = new HashSet<>();

        // init first state
        visited.add(List.of(0, 0));
        minHeap.add(new int[]{0, 0});

        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] cur = minHeap.poll();
            int id1 = cur[0];
            int id2 = cur[1];
            res.add(List.of(nums1[id1], nums2[id2]));

            if (id1 + 1 < nums1.length && !visited.contains(List.of(id1 + 1, id2))) {
                minHeap.add(new int[]{id1 + 1, id2});
                visited.add(List.of(id1 + 1, id2));
            }

            if (id2 + 1 < nums2.length && !visited.contains(List.of(id1, id2 + 1))) {
                minHeap.add(new int[]{id1, id2 + 1});
                visited.add(List.of(id1, id2 + 1));
            }
        }

        return res;
    }
}
