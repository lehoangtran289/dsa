package leetcode.array;

import java.util.Comparator;
import java.util.PriorityQueue;

public class E_2558_TakeGiftsFromTheRichestPile {
    public static void main(String[] args) {
        System.out.println(pickGifts(new int[]{25, 64, 9, 4, 100}, 4));
    }

    /**
     * O(n) for init pq
     * O(k * logn) for add & poll * k times
     * ==> Time complexity O(n + k * logn)
     */
    public static long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int g : gifts) pq.add(g);

        while (k-- > 0 && !pq.isEmpty()) {
            pq.add((int) Math.sqrt(pq.poll()));
        }

        long res = 0;
        while (!pq.isEmpty()) {
            res += pq.poll();
        }

        return res;
    }
}
