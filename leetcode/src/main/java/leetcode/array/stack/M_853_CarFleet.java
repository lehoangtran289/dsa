package leetcode.array.stack;

import java.util.Arrays;
import java.util.Stack;

public class M_853_CarFleet {
    public static void main(String[] args) {
        System.out.println(carFleet(12, new int[]{10, 8, 0, 5, 3}, new int[]{2, 4, 1, 1, 3})); // 3
        System.out.println(carFleet(10, new int[]{3}, new int[]{3})); // 1
        System.out.println(carFleet(100, new int[]{0, 2, 4}, new int[]{4, 2, 1})); // 1
    }

    static class Car {
        int position;
        double timeToTarget;

        Car (int position, double timeToTarget) {
            this.position = position;
            this.timeToTarget = timeToTarget;
        }
    }

    /**
     * Monotonic Stack
     * Idea: A car can catch up the car in front of it if its time to target is less than or equal to the car in front of it
     * --> Sort cars by position, then use a mono decreasing stack to calculate car fleet
     * -----------------------------
     * TC: O(nlogn)
     * SC: O(n)
     */
    public static int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;

        // init Car array
        Car[] cars = new Car[n];
        for (int i = 0; i < n; ++i) {
            cars[i] = new Car(position[i], (double) (target - position[i]) / speed[i]);
        }
        Arrays.sort(cars, (a, b) -> a.position - b.position);

        // mono decreasing stack for car fleet calculation
        Stack<Car> carStack = new Stack<>();

        for (Car car : cars) {
            while (
                    !carStack.isEmpty()
                    && carStack.peek().timeToTarget <= car.timeToTarget
            ) {
                carStack.pop();
            }
            carStack.add(car);
        }

        return carStack.size();
    }
}
