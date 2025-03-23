package contest.weekly442;

public class Q1 {
    public static void main(String[] args) {
        System.out.println(new Q1().maxContainers(2, 3, 15)); // 4
        System.out.println(new Q1().maxContainers(3, 5, 20)); // 4
        System.out.println(new Q1().maxContainers(1, 1, 1)); // 1
    }

    public int maxContainers(int n, int w, int maxWeight) {
        int res = n * n;
        while (res >= 1) {
            if (w * res <= maxWeight) break;
            res--;
        }

        return res;
    }
}
