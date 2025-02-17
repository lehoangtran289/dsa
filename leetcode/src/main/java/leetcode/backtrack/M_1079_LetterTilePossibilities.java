package leetcode.backtrack;

import java.util.HashSet;
import java.util.Set;

public class M_1079_LetterTilePossibilities {
    public static void main(String[] args) {
        System.out.println(numTilePossibilities("AAB"));
        System.out.println(numTilePossibilities("AAABBC"));
    }

    public static int numTilePossibilities(String tiles) {
        Set<String> res = new HashSet<>();
        boolean[] visited = new boolean[tiles.length()];

        backtrack(tiles, "", visited, res);

        return res.size() - 1;
    }

    private static void backtrack(
            String tiles,
            String cur,
            boolean[] visited,
            Set<String> result
    ) {
        System.out.println(cur);
        result.add(cur);

        for (int i = 0; i < tiles.length(); ++i) {
            if (!visited[i]) {
                // set
                visited[i] = true;

                // process
                String next = cur + tiles.charAt(i);
                if (!result.contains(next))
                    backtrack(tiles, next, visited, result);

                // undo
                visited[i] = false;
            }
        }
    }
}
