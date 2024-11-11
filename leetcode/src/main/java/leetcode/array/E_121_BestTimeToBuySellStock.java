package leetcode.array;

public class E_121_BestTimeToBuySellStock {
    public static void main(String[] args) {
        E_121_BestTimeToBuySellStock obj = new E_121_BestTimeToBuySellStock();
        System.out.println(obj.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    public int maxProfit(int[] nums) {
        int minSoFar = Integer.MAX_VALUE;

        int maxProfit = 0;
        for (int num : nums) {
            minSoFar = Math.min(minSoFar, num); // min price so far
            maxProfit = Math.max(maxProfit, num - minSoFar); // max prof so far
        }
        return maxProfit;
    }
}
