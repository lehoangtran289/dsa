package leetcode.bit;

import java.util.HashMap;
import java.util.Map;

public class M_1386_CinemaSeatAllocation {

    private static final int BLOCK1_MASK = 0b0000111100; // 1 << 2 | 1 << 3 | 1 << 4 | 1 << 5
    private static final int BLOCK2_MASK = 0b0011110000; // 1 << 4 | 1 << 5 | 1 << 6 | 1 << 7
    private static final int BLOCK3_MASK = 0b1111000000; // 1 << 6 | 1 << 7 | 1 << 8 | 1 << 9

    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> seatMap = new HashMap<>();

        for (int[] r : reservedSeats) {
            seatMap.put(r[0], seatMap.getOrDefault(r[0], 0) | (1 << r[1]));
        }

        int res = 0;
        for (var entry : seatMap.entrySet()) {
            Integer mask = entry.getValue();

            boolean block1Free = (mask & BLOCK1_MASK) == 0;
            boolean block2Free = (mask & BLOCK2_MASK) == 0;
            boolean block3Free = (mask & BLOCK3_MASK) == 0;

            if (block1Free && block3Free) {
                res += 2;
            } else if (block1Free || block2Free || block3Free) {
                res++;
            }
        }
        return res + (n - seatMap.size()) * 2;
    }
}
