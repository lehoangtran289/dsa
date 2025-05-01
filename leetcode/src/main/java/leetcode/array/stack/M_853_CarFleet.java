package leetcode.array.stack;

import java.util.Arrays;

public class M_853_CarFleet {
    public static void main(String[] args) {
        System.out.println(carFleet(12, new int[]{10, 8, 0, 5, 3}, new int[]{2, 4, 1, 1, 3})); // 3
        System.out.println(carFleet(10, new int[]{3}, new int[]{3})); // 1
        System.out.println(carFleet(100, new int[]{0, 2, 4}, new int[]{4, 2, 1})); // 1
    }

    /**
     * Greedy, sorting
     * Idea: Sort the cars by their position in descending order.
     * Then, iterate through the sorted cars and check if the current car can catch up to the next car.
     * If it can, they form a fleet
     * ----------------------------------------------------
     * TC: O(nlogn)
     * SC: O(n)
     */
    public static int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        Car[] cars = new Car[n];
        for (int i = 0; i < n; ++i) {
            cars[i] = new Car(position[i], (double) (target - position[i]) / speed[i]);
        }

        Arrays.sort(cars, (a, b) -> a.pos - b.pos);

        int res = 0;
        double minTime = 0;
        for (int i = n - 1; i > 0; --i) {
            if (cars[i].time < cars[i - 1].time) { // if the current car cannot catch up to the prev car
                res++;
            } else {
                cars[i - 1].time = cars[i].time;
                minTime = Math.min(minTime, cars[i - 1].time);
            }
        }

        return res + (cars[0].time < minTime ? 0 : 1); // check first car
    }

    static class Car {
        int pos;
        double time;

        Car(int pos, double time) {
            this.pos = pos;
            this.time = time;
        }
    }
}
