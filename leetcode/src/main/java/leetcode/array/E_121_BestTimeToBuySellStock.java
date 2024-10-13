package leetcode.array;

public class E_121_BestTimeToBuySellStock {
    public static void main(String[] args) {
        E_121_BestTimeToBuySellStock obj = new E_121_BestTimeToBuySellStock();
        System.out.println(obj.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    public int maxProfit(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int num : nums) {
            min = Math.min(min, num); // min price so far
            max = Math.max(max, num - min); // max prof so far
        }
        return max;
    }
}
