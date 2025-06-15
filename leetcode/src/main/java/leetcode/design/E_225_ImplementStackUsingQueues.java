package leetcode.design;

import java.util.*;

public class E_225_ImplementStackUsingQueues {

    private Queue<Integer> q1;
    private Queue<Integer> q2;
    private int top;

    /**
     * Implement a stack using two queues.
     * -----------------------
     * TC: O(n) for pop, O(1) for push
     * SC: O(n)
     */
    public E_225_ImplementStackUsingQueues() {
        q1 = new ArrayDeque<>();
        q2 = new ArrayDeque<>();
    }

    public void push(int x) {
        q1.add(x);
        top = x;
    }

    public int pop() {
        while (q1.size() > 1) {
            top = q1.poll();
            q2.add(top);
        }
        int temp = q1.poll();
        q1 = q2;
        q2 = new ArrayDeque<>();

        return temp;
    }

    public int top() {
        return top;
    }

    public boolean empty() {
        return q1.isEmpty();
    }
}
