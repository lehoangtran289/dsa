package leetcode.graph.mst;

public class _0_DisjointSet {
    public static void main(String[] args) {
        int n = 5; // Number of elements
        DisjointSet ds = new DisjointSet(n);

        // Example usage
        ds.join(0, 1);
        ds.join(2, 3);
        ds.join(0, 4);
        ds.join(4, 1);

        // Check if two elements belong to the same set
        System.out.println(ds.find(0) == ds.find(4)); // Should print true
        System.out.println(ds.find(1) == ds.find(3)); // Should print false
    }
}