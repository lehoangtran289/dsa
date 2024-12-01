package leetcode.contest.weekly426;

public class E_100501_SmallestNumberWithAllSetBits {
    public static void main(String[] args) {
        System.out.println(smallestNumber(5));
    }

    public static int smallestNumber(int n) {
        int i = 0;
        while ((1 << i) - 1 < n) {
            i++;
        }
        return (1 << i) - 1;
    }
}
