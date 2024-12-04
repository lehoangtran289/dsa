package leetcode.array.array2d;

import java.util.Arrays;

public class M_1861_RotatingTheBox {
    public static void main(String[] args) {
        char[][] box = {{'#', '#', '*', '.', '*', '.'},
                {'#', '#', '#', '*', '.', '.'},
                {'#', '#', '#', '.', '#', '.'}};
        System.out.println(Arrays.deepToString(rotateTheBox(box)));
    }

    public static char[][] rotateTheBox(char[][] box) {
        int m = box.length;
        int n = box[0].length;
        char[][] res = new char[n][m];

        // create transpose of box
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                res[i][j] = box[j][i];
            }
        }

        // reverse box to 90 degree
        for (int i = 0; i < n; ++i) {
            reverse(res[i]);
        }

        for (int j = 0; j < m; j++) {
            // Process each cell in column `j` from bottom to top
            for (int i = n - 1; i >= 0; i--) {
                if (res[i][j] == '.') { // Found an empty cell; check if a stone can fall into it
                    int nextRowWithStone = -1;

                    // Look for a stone directly above the empty cell `res[i][j]`
                    for (int k = i - 1; k >= 0; k--) {
                        if (res[k][j] == '*') break; // Obstacle blocks any stones above
                        if (res[k][j] == '#') { // Stone found with no obstacles in between
                            nextRowWithStone = k;
                            break;
                        }
                    }

                    // If a stone was found above, let it fall into the empty cell `res[i][j]`
                    if (nextRowWithStone != -1) {
                        res[nextRowWithStone][j] = '.';
                        res[i][j] = '#';
                    }
                }
            }
        }
        return res;
    }

    // Helper function to reverse an array
    private static void reverse(char[] row) {
        int l = 0;
        int r = row.length - 1;
        while (l < r) {
            // swap l & r
            char temp = row[l];
            row[l] = row[r];
            row[r] = temp;
            l++;
            r--;
        }
    }
}
