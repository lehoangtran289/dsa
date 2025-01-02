package leetcode.string;

public class E_1427_PerformStringShifts {
    public static void main(String[] args) {
        int[][] shifts = {{1, 8}, {1, 4}, {1, 3}, {1, 6}, {0, 6}, {1, 4}, {0, 2}, {0, 1}};
        System.out.println(stringShift("yisxjwry", shifts));
    }

    public static String stringShift(String s, int[][] shifts) {
        int amount = 0;
        for (int[] shift : shifts) {
            if (shift[0] == 0) amount -= shift[1];
            else amount += shift[1];
        }
        if (amount == 0) return s;

        StringBuilder sb = new StringBuilder();
        int idx = Math.abs(amount) % s.length();
        return amount < 0 ?
                sb.append(s, idx, s.length()).append(s, 0, idx).toString() :
                sb.append(s, s.length() - idx, s.length()).append(s, 0, s.length() - idx).toString();
    }
}
