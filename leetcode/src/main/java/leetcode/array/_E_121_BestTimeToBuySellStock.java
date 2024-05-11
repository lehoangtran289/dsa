package leetcode.array;

public class _E_121_BestTimeToBuySellStock {
    public int maxProfit(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int num : nums) {
            min = Math.min(min, num); // min price so far
            max = Math.max(max, num - min); // max prof so far
        }
        return max;
    }

    public static void main(String[] args) {
        _E_121_BestTimeToBuySellStock obj = new _E_121_BestTimeToBuySellStock();
        System.out.println(obj.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }
}
