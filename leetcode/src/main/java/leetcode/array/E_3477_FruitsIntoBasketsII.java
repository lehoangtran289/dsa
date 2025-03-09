package leetcode.array;

public class E_3477_FruitsIntoBasketsII {
    public static void main(String[] args) {
        System.out.println(new E_3477_FruitsIntoBasketsII().numOfUnplacedFruits(
                new int[]{4, 2, 5}, new int[]{3, 5, 4}
        ));
    }

    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int res = 0;

        for (int i = 0; i < fruits.length; ++i) {
            boolean isPlaced = false;

            for (int j = 0; j < baskets.length; ++j) {
                if (baskets[j] == 0) continue;

                if (baskets[j] >= fruits[i]) {
                    isPlaced = true;
                    baskets[j] = 0;
                    break;
                }
            }

            if (!isPlaced) {
                res++;
            }
        }

        return res;
    }
}
