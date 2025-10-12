package leetcode.array.stack;

import java.util.Stack;

public class M_901_OnlineStockSpan {
    public static void main(String[] args) {
        M_901_OnlineStockSpan stockSpanner = new M_901_OnlineStockSpan();
        System.out.println(stockSpanner.next(100)); // 1
        System.out.println(stockSpanner.next(80));  // 1
        System.out.println(stockSpanner.next(60));  // 1
        System.out.println(stockSpanner.next(70));  // 2
        System.out.println(stockSpanner.next(60));  // 1
        System.out.println(stockSpanner.next(75));  // 4
        System.out.println(stockSpanner.next(85));  // 6
    }

    static class Stock {
        int price;
        int id;
        int span;

        Stock(int price, int id, int span) {
            this.price = price;
            this.id = id;
            this.span = span;
        }
    }

    private int curId;
    private final Stack<Stock> stockStack;

    public M_901_OnlineStockSpan() {
        this.curId = -1;
        this.stockStack = new Stack<>();
    }

    /**
     * Monotonic Stack, store (price, id, span)
     * TC: O(n) amortized
     * SC: O(n)
     */
    public int next(int price) {
        int span = 1;
        curId++;

        while (
                !stockStack.isEmpty()
                && stockStack.peek().price <= price
        ) {
            Stock prevStock = stockStack.pop();
            span = Math.max(span, curId - prevStock.id + prevStock.span);
        }

        stockStack.push(new Stock(price, curId, span));
        return span;
    }
}
