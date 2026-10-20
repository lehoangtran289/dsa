package leetcode.array.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class M_155_MinStack {

    /**
     * Val/Min pair approach
     */
    private final Deque<int[]> stack;

    public M_155_MinStack() {
        this.stack = new ArrayDeque<>();
    }

    public void push1(int value) {
        if (stack.isEmpty()) {
            stack.push(new int[]{value, value});
        } else {
            stack.push(new int[]{value, Math.min(value, stack.peek()[1])});
        }
    }

    public void pop1() {
        stack.pop();
    }

    public int top1() {
        return stack.peek()[0];
    }

    public int getMin1() {
        return stack.peek()[1];
    }

    // --------------------------------------------------------------------------

    /**
     * Idea: we don't have to store every min value along each element if it doesn't change
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
