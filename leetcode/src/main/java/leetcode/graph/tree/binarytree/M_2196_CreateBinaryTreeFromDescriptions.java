package leetcode.graph.tree.binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class M_2196_CreateBinaryTreeFromDescriptions {

    /**
     * Intuition, build graph + find root node + dfs
     */
    public TreeNode createBinaryTree1(int[][] descriptions) {
        Set<Integer> children = new HashSet<>();
        Set<Integer> parents = new HashSet<>();
        Map<Integer, List<int[]>> graph = new HashMap<>();

        // build graph (Adj list)
        for (int[] d : descriptions) {
            graph.putIfAbsent(d[0], new ArrayList<>());
            graph.putIfAbsent(d[1], new ArrayList<>());
            graph.get(d[0]).add(new int[]{d[1], d[2]});

            parents.add(d[0]);
            children.add(d[1]);
        }

        // find root node
        int rootVal = 0;
        for (var parent : parents) {
            if (!children.contains(parent)) {
                rootVal = parent;
                break;
            }
        }

        return buildBinaryTree(graph, new TreeNode(rootVal));
    }

    private TreeNode buildBinaryTree(Map<Integer, List<int[]>> graph, TreeNode root) {
        for (int[] child : graph.get(root.val)) {
            if (child[1] == 1) root.left = buildBinaryTree(graph, new TreeNode(child[0]));
            else if (child[1] == 0) root.right = buildBinaryTree(graph, new TreeNode(child[0]));
        }

        return root;
    }

    /**
     * Build tree directly by storing map of val -> TreeNode, then find root node
     */
    public TreeNode createBinaryTree2(int[][] descriptions) {
        Set<Integer> children = new HashSet<>();
        Map<Integer, TreeNode> valToNodeMap = new HashMap<>();

        for (int[] d : descriptions) {
            int parent = d[0];
            int child = d[1];
            boolean isLeft = d[2] == 1;

            children.add(child);

            valToNodeMap.putIfAbsent(parent, new TreeNode(parent));
            valToNodeMap.putIfAbsent(child, new TreeNode(child));

            if (isLeft) valToNodeMap.get(parent).left = valToNodeMap.get(child);
            else valToNodeMap.get(parent).right = valToNodeMap.get(child);
        }

        // find root node
        TreeNode root = null;

        for (int key : valToNodeMap.keySet()) {
            if (!children.contains(key)) {
                root = valToNodeMap.get(key);
                break;
            }
        }

        return root;
    }
}
