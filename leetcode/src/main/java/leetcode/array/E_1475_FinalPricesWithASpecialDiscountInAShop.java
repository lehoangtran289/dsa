package leetcode.array;

import java.util.Arrays;
import java.util.Stack;

public class E_1475_FinalPricesWithASpecialDiscountInAShop {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(finalPrices(new int[]{8, 4, 6, 2, 3})));
    }

    // Monotonic stack
    public static int[] finalPrices(int[] prices) {
        int n = prices.length;
        Stack<Integer> stack = new Stack<>();

        int[] res = prices.clone();
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && prices[stack.peek()] >= prices[i]) {
                res[stack.pop()] -= prices[i];
            }
            stack.add(i);
        }
        return res;
    }
}
