package leetcode.tree.binarytree;

import java.util.ArrayList;
import java.util.List;

public class M_1028_RecoverATreeFromPreorderTraversal {
    public static void main(String[] args) {
        System.out.println(recoverFromPreorder("1-2--3---4-5--6---7"));
        System.out.println(recoverFromPreorder("1-401--349---90--88"));
    }

    public static TreeNode recoverFromPreorder(String traversal) {
        List<TreeNode> levels = new ArrayList<>();
        int i = 0;

        while (i < traversal.length()) {
            // determine current level
            int level = 0;
            while (traversal.charAt(i) == '-') {
                level++;
                i++;
            }

            // extract node val
            int nodeVal = 0;
            while (i < traversal.length()) {
                if (traversal.charAt(i) == '-') break;
                nodeVal = 10 * nodeVal + (traversal.charAt(i) - '0');
                i++;
            }

            System.out.println(level + " " + nodeVal);
            TreeNode node = new TreeNode(nodeVal);

        }

        return null;
    }
}
