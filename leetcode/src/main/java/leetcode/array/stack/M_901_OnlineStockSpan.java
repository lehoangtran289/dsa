package leetcode.array.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class M_901_OnlineStockSpan {
    static void main() {
        M_901_OnlineStockSpan stockSpanner = new M_901_OnlineStockSpan();
        System.out.println(stockSpanner.next(100)); // 1
        System.out.println(stockSpanner.next(80));  // 1
        System.out.println(stockSpanner.next(60));  // 1
        System.out.println(stockSpanner.next(70));  // 2
        System.out.println(stockSpanner.next(60));  // 1
        System.out.println(stockSpanner.next(75));  // 4
        System.out.println(stockSpanner.next(85));  // 6
    }

    private final Deque<int[]> stack; // [value, span]

    public M_901_OnlineStockSpan() {
        this.stack = new ArrayDeque<>();
    }

    /**
     Idea: monotonic stack, store [value, span]
     ---
     Dry run: [7,2,1,2], price = 2
     price = 7 -> stack = [[7,1]]
     price = 2 -> stack = [[7,1],[2,1]]
     price = 1 -> stack = [[7,1],[2,1],[1,1]]
     price = 2 -> stack = [[7,1],[2,3]]
     price = 2 (today) -> stack = [[7,1],[2,4]]
     ---
     TC: O(N) - number of prices entered
     SC: O(N)
     */
    public int next(int price) {
        int span = 1;
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1];
        }
        stack.push(new int[]{price, span});

        return span;
    }
}
