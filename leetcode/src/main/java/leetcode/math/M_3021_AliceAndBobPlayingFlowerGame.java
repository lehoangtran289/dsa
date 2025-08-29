package leetcode.math;

public class M_3021_AliceAndBobPlayingFlowerGame {
    public static void main(String[] args) {
        M_3021_AliceAndBobPlayingFlowerGame game = new M_3021_AliceAndBobPlayingFlowerGame();
        System.out.println(game.flowerGame(2, 3)); // 3
        System.out.println(game.flowerGame(1, 4)); // 2
        System.out.println(game.flowerGame(3, 3)); // 4
    }

    /**
     *  (x, y) must has different parity
     */
    public long flowerGame(int n, int m) {
        long oddsInFirst = countOddInRange(1, n);
        long evensInFirst = n - oddsInFirst;
        long oddsInSecond = countOddInRange(1, m);
        long evensInSecond = m - oddsInSecond;

        return oddsInFirst * evensInSecond + evensInFirst * oddsInSecond;
    }

    private int countOddInRange(int x, int y) {
        if ((x & 1) == 0) x++;
        if ((y & 1) == 0) y--;
        return x <= y ? (y - x) / 2 + 1 : 0;
    }

    private int countEvenInRange(int x, int y) {
        if ((x & 1) == 1) x++;
        if ((y & 1) == 1) y--;
        return x <= y ? (y - x) / 2 + 1 : 0;
    }
}
