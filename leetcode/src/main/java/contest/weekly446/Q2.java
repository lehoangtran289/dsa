package contest.weekly446;

import java.util.Stack;

public class Q2 {
    public static void main(String[] args) {
        System.out.println(maximumPossibleSize(new int[]{4, 2, 5, 3, 5}));
        System.out.println(maximumPossibleSize(new int[]{1, 2, 3}));
        System.out.println(maximumPossibleSize(new int[]{19, 80, 63, 74}));
    }

    public static int maximumPossibleSize(int[] nums) {
        Stack<Integer> stack = new Stack<>();

        for (int num : nums) {
            while (!stack.isEmpty() && stack.peek() > num) {
                int top = stack.pop();
                num = Math.max(num, top);
            }
            stack.add(num);
        }
        return stack.size();
    }
}
