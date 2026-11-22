package leetcode.graph.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M_3331_FindSubtreeSizesAfterChanges {
    static void main() {
        var sol = new M_3331_FindSubtreeSizesAfterChanges();
        System.out.println(Arrays.toString(sol.findSubtreeSizes(new int[]{-1, 0, 0, 1, 1, 1}, "abaabc")));
        // [6,3,1,1,1,1]
    }

    private String s;

    /**
     * Idea: find ancestors -> DFS path, maintain last seen index of a char -> closest-same-character ancestor
     * remove edge and make new parent -> modify parent[] to point parent[x] to new node y.
     * Size of subtree -> DFS post-order traversal
     * ---
     * TC: O(N)
     * SC: O(N)
     */
    public int[] findSubtreeSizes(int[] parent, String s) {
        this.s = s;
        int n = parent.length;

        // build ORIGINAL tree
        List<Integer>[] tree = new List[n];
        for (int i = 0; i < n; ++i) tree[i] = new ArrayList<>();
        for (int i = 1; i < n; ++i) tree[parent[i]].add(i);

        // DFS states
        int[] lastSeenNode = new int[128]; // for each char, -1 = none
        int[] newParent = new int[n];

        Arrays.fill(lastSeenNode, -1);
        for (int i = 0; i < n; ++i) newParent[i] = parent[i];
        lastSeenNode[s.charAt(0)] = 0;

        dfs(tree, lastSeenNode, newParent, 0);

        // build NEW tree
        List<Integer>[] newTree = new List[n];
        for (int i = 0; i < n; ++i) newTree[i] = new ArrayList<>();
        for (int i = 1; i < n; ++i) newTree[newParent[i]].add(i);

        // build subtree size array
        int[] result = new int[n];
        getSize(newTree, result, 0);
        return result;
    }

    private void dfs(List<Integer>[] tree, int[] lastSeenNode, int[] newParent, int node) {
        int prevIndex = lastSeenNode[s.charAt(node)];

        lastSeenNode[s.charAt(node)] = node;
        for (int child : tree[node]) {
            if (lastSeenNode[s.charAt(child)] != -1) { // exist same-char ancestor
                newParent[child] = lastSeenNode[s.charAt(child)];
            }
            dfs(tree, lastSeenNode, newParent, child);
        }
        // backtrack
        lastSeenNode[s.charAt(node)] = prevIndex;
    }

    private int getSize(List<Integer>[] tree, int[] result, int node) {
        int size = 1;
        for (int child : tree[node]) {
            size += getSize(tree, result, child);
        }
        return result[node] = size;
    }
}
