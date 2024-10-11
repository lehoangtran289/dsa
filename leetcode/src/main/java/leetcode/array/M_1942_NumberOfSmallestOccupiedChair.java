package leetcode.array;

import java.util.Arrays;

public class M_1942_NumberOfSmallestOccupiedChair {
    public static void main(String[] args) {
        int[][] intervals = {
                {4, 5}, {12, 13}, {5, 6}, {1, 2}, {8, 9}, {9, 10}, {6, 7}, {3, 4},
                {7, 8}, {13, 14}, {15, 16}, {14, 15}, {10, 11}, {11, 12}, {2, 3}, {16, 17}
        };
        System.out.println(new M_1942_NumberOfSmallestOccupiedChair()
                .smallestChair(intervals, 0));
    }

    public int smallestChair(int[][] times, int targetFriend) {
        int[] chairs = new int[100001];
        Arrays.fill(chairs, -1);
        int[] friendArrival = times[targetFriend];
        Arrays.sort(times, (a, b) -> a[0] - b[0]);
        System.out.println(Arrays.deepToString(times));

        int res = 0;
        for (int[] time : times) {
            int start = time[0];
            int end = time[1];
            for (int j = 0; j < chairs.length; ++j) {
                if (chairs[j] == -1 || chairs[j] <= start) {
                    chairs[j] = end;
                    if (checkTargetFriend(start, end, friendArrival)) return j;
                    break;
                }
            }
        }
        return res;
    }

    private boolean checkTargetFriend(int start, int end, int[] time) {
        return start == time[0] && end == time[1];
    }

}
