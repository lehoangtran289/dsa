package leetcode.design;

import java.util.ArrayList;
import java.util.List;

public class M_1352_ProductOfTheLastKNumbers {
    /**
     * Idea: prefix product. getProduct(k) = totalProduct / prefixProduct(n - k - 1)
     * When we encounter 0 -> clear prefix product to record next non-zero product
     * ---
     * TC: O(1)
     */
    private final List<Integer> prefixProducts;
    private int totalProduct;

    public M_1352_ProductOfTheLastKNumbers() {
        this.prefixProducts = new ArrayList<>();
        this.totalProduct = 1;
    }

    public void add(int num) {
        if (num == 0) {
            prefixProducts.clear();
            totalProduct = 1;
        } else {
            prefixProducts.add(totalProduct * num);
            totalProduct *= num;
        }
    }

    public int getProduct(int k) {
        int streamLength = prefixProducts.size();

        if (streamLength == k) return totalProduct;
        else if (streamLength < k) return 0;

        return totalProduct / prefixProducts.get(streamLength - k - 1);
    }
}
