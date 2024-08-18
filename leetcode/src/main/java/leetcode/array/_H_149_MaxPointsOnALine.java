package leetcode.array;

public class _H_149_MaxPointsOnALine {
    public static void main(String[] args) {
        int[][] input = new int[][]{{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}};
        System.out.println(new _H_149_MaxPointsOnALine().maxPoints(input));

        int[][] input2 = new int[][]{{1,1},{0,0},{1,-1}};
        System.out.println(new _H_149_MaxPointsOnALine().maxPoints(input2));

        int[][] input3 = new int[][]{{2,3},{3,3},{-5,3}};
        System.out.println(new _H_149_MaxPointsOnALine().maxPoints(input3));
    }

    public int maxPoints(int[][] points) {
        if (points.length <= 2) return points.length;

        int res = 2;


        for (int i = 0; i < points.length; ++i) {
            for (int j = i + 1; j < points.length; ++j) {
                int curMax = 2;
                for (int k = 0; k < points.length; ++k) {
                    if (k != i && k != j && check(points[i], points[j], points[k])) {
                        curMax++;
                        res = Math.max(curMax, res);
                    }
                }
            }
        }
        return res;
    }

    /**
     * (x - x1) * (y2 - y1) - (y - y1) * (x2 - x1) = 0
     */
    public boolean check(int[] p1, int[] p2, int[] check) {
        return (check[0] - p1[0]) * (p2[1] - p1[1]) - (check[1] - p1[1]) * (p2[0] - p1[0]) == 0;
    }
}
