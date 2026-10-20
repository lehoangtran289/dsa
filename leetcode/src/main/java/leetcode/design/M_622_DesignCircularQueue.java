package leetcode.design;

public class M_622_DesignCircularQueue {

    private final int[] arr;
    private int front;
    private int size;

    public M_622_DesignCircularQueue(int k) {
        this.arr = new int[k];
        this.front = 0;
        this.size = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) return false;

        arr[(front + size) % arr.length] = value;
        size++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) return false;

        front = (front + 1) % arr.length;
        size--;
        return true;
    }

    public int Front() {
        return isEmpty() ? -1 : arr[front];
    }

    public int Rear() {
        int rear = (front + size - 1) % arr.length;
        return isEmpty() ? -1 : arr[rear];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == arr.length;
    }
}
