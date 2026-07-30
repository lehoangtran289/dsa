package leetcode.graph.trie;

import java.util.ArrayList;
import java.util.List;

public class H_212_WordSearchII {

    private static final int[][] DIRS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private char[][] board;
    private int rows;
    private int cols;
    private List<String> res;

    /**
     * Idea: backtrack + trie, and directly store word at leaf
     * ---
     * TC: O(N * L + R * C * 3^L)
     * where R, C = number of rows/columns of board
     * N = number of words
     * L = length of the longest word
     * ---
     * Build Trie -> O(N * L)
     * DFS backtrack -> O(R * C * 3^L), where 3^L = the number of possible paths of length L in the board
     */
    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        this.rows = board.length;
        this.cols = board[0].length;
        this.res = new ArrayList<>();
        Trie trie = new Trie();

        for (String word : words) {
            trie.insert(word);
        }

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                int idx = board[i][j] - 'a';
                if (trie.root.nodes[idx] == null) continue;

                backtrack(i, j, new boolean[rows][cols], trie.root.nodes[idx]);
            }
        }
        return res;
    }

    private void backtrack(
            int i, int j,
            boolean[][] visited,
            Node cur
    ) {
        if (cur == null) return;

        if (cur.word != null) {
            res.add(cur.word);
            cur.word = null;
        }

        visited[i][j] = true;

        for (int[] dir : DIRS) {
            int nx = i + dir[0];
            int ny = j + dir[1];

            if (
                    isCellValid(nx, ny, rows, cols)
                    && !visited[nx][ny]
            ) {
                visited[nx][ny] = true;
                backtrack(nx, ny, visited, cur.nodes[board[nx][ny] - 'a']);
                visited[nx][ny] = false;
            }
        }
    }

    private boolean isCellValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    static class Node {
        Node[] nodes = new Node[26];
        String word;
    }

    static class Trie {
        Node root = new Node();

        void insert(String word) {
            Node cur = root;

            for (char c : word.toCharArray()) {
                int idx = c - 'a';

                if (cur.nodes[idx] == null) {
                    cur.nodes[idx] = new Node();
                }
                cur = cur.nodes[idx];
            }
            cur.word = word;
        }
    }
}
