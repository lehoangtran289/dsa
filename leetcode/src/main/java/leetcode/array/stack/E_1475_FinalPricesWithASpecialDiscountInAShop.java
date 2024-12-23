package leetcode.array.stack;

import java.util.Arrays;
import java.util.Stack;

public class E_1475_FinalPricesWithASpecialDiscountInAShop {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(finalPrices(new int[]{8, 4, 6, 2, 3})));
    }

    /**
     * finding the "next smaller element," which can be efficiently solved using a stack
     */
    // Monotonic stack
    public static int[] finalPrices(int[] prices) {
        int n = prices.length;
        Stack<Integer> stack = new Stack<>();

        int[] res = prices.clone();
        for (int i = 0; i < n; ++i) {
            Integer topIndex = stack.peek();
            while (!stack.isEmpty() && prices[i] <= prices[topIndex]) {
                res[stack.pop()] -= prices[i];
            }
            stack.add(i);
        }
        return res;
    }
}
