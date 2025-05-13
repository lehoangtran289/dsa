package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class M_373_FindKPairsWithSmallestSums {
    static class Pair<K, V> {
        K k;
        V value;

        public Pair(K k, V value) {
            this.k = k;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return k.equals(pair.k) && value.equals(pair.value);
        }

        @Override
        public int hashCode() {
            return 31 * k.hashCode() + value.hashCode();
        }
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int n = nums1.length, m = nums2.length;
        List<List<Integer>> res = new ArrayList<>();

        // pq: sum, index nums 1, index nums 2
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
                                                              (nums1[a[0]] + nums2[a[1]]) - (nums1[b[0]] + nums2[b[1]])
        );
        Set<Pair<Integer, Integer>> visited = new HashSet<>();

        // init first state
        visited.add(new Pair<>(0, 0));
        pq.add(new int[]{0, 0});

        while (k-- > 0 && !pq.isEmpty()) {
            int[] cur = pq.poll();
            int id1 = cur[0];
            int id2 = cur[1];
            res.add(Arrays.asList(nums1[id1], nums2[id2]));

            if (id1 + 1 < n && !visited.contains(new Pair<>(id1 + 1, id2))) {
                pq.add(new int[]{id1 + 1, id2});
                visited.add(new Pair<>(id1 + 1, id2));
            }

            if (id2 + 1 < m && !visited.contains(new Pair<>(id1, id2 + 1))) {
                pq.add(new int[]{id1, id2 + 1});
                visited.add(new Pair<>(id1, id2 + 1));
            }
        }

        return res;
    }
}
