package leetcode.contest.weekly426;

public class E_100501_SmallestNumberWithAllSetBits {
    public static void main(String[] args) {
        System.out.println(smallestNumber(5));
    }

    public static int smallestNumber(int n) {
        int i = n + 1;
        while (true) {
            if (Integer.bitCount(i) == 1) return i - 1;
            i++;
        }
    }
}
