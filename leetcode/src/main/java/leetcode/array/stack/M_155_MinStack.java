package leetcode.array.stack;

import java.util.Stack;

public class M_155_MinStack {

    /**
     * Val/Min pair approach
     */
    private final Stack<Pair> stack1 = new Stack<>();

    public void push1(int val) {
        if (stack1.isEmpty()) {
            stack1.push(new Pair(val, val));
        } else {
            int curMin = Math.min(val, stack1.peek().min);
            stack1.push(new Pair(val, curMin));
        }
    }

    public void pop1() {
        stack1.pop();
    }

    public int top1() {
        return stack1.peek().val;
    }

    public int getMin1() {
        return stack1.peek().min;
    }

    static class Pair {
        int val;
        int min;

        Pair(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }

    // --------------------------------------------------------------------------

    /**
     * 2 stacks approach
     */
    private final Stack<Integer> stack2 = new Stack<>();
    private final Stack<Integer> minStack = new Stack<>();

    public void push(int val) {
        if (stack2.isEmpty()) {
            stack2.push(val);
            minStack.push(val);
        } else {
            stack2.push(val);
            if (minStack.peek() >= val) {
                minStack.push(val);
            }
        }
    }

    public void pop() {
        int val = stack2.pop();
        if (minStack.peek() == val) {
            minStack.pop();
        }
    }

    public int top() {
        return stack2.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
