package leetcode.graph.mst;

public class M_1061_LexicographicallySmallestEquivalentString {
    public static void main(String[] args) {
        System.out.println(smallestEquivalentString("hello", "world", "hold")); // "hdld"
    }

    public static String smallestEquivalentString(String s1, String s2, String baseStr) {
        StringBuilder res = new StringBuilder();
        DisjointSet dsu = new DisjointSet(26);

        for (int i = 0; i < s1.length(); ++i) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);

            if (c1 != c2) {
                dsu.union(c1 - 'a', c2 - 'a');
            }
        }

        for (int i = 0; i < baseStr.length(); ++i) {
            int index = dsu.find(baseStr.charAt(i) - 'a');
            res.append((char) (index + 'a'));
        }

        return res.toString();
    }

    static class DisjointSet {
        private final int[] parent;

        public DisjointSet(int n) {
            this.parent = new int[n];

            for (int i = 0; i < n; ++i) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] == x) return x;

            // return root of the set
            return parent[x] = find(parent[x]);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) return;

            // order by lexicographical order
            if (rootX < rootY) parent[rootY] = rootX;
            else parent[rootX] = rootY;
        }
    }
}
