package leetcode.array.prefixSum;

import java.util.ArrayList;
import java.util.List;

public class M_1352_ProductOfTheLastKNumbers {
    /**
     * Your ProductOfNumbers object will be instantiated and called as such:
     * ProductOfNumbers obj = new ProductOfNumbers();
     * obj.add(num);
     * int param_2 = obj.getProduct(k);
     */
    static class ProductOfNumbers {
        private List<Integer> arr = new ArrayList<>();

        public ProductOfNumbers() {
            // Initialize the product list with 1 to handle multiplication logic
            arr.add(1);
        }

        public void add(int num) {
            if (num == 0) {
                // If num is 0, reset the cumulative products
                arr = new ArrayList<>();
                arr.add(1);
            } else {
                arr.add(arr.get(arr.size() - 1) * num);
            }
        }

        public int getProduct(int k) {
            if (k > arr.size() - 1) return 0;

            return arr.get(arr.size() - 1) / arr.get(arr.size() - 1 - k);
        }
    }
}
