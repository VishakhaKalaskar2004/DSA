import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class SmallestSubtreewithalltheDeepestNodes {
    // Helper class to return both the depth and the target node from recursion
    private static class Result {
        TreeNode node;
        int depth;

        Result(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).node;
    }

    private Result dfs(TreeNode node) {
        if (node == null) {
            return new Result(null, 0);
        }

        Result left = dfs(node.left);
        Result right = dfs(node.right);

        // Current depth equals 1 plus the maximum depth of the children
        int currentDepth = Math.max(left.depth, right.depth) + 1;

        // Case 1: Both subtrees have the same maximum depth
        if (left.depth == right.depth) {
            return new Result(node, currentDepth);
        }
        
        // Case 2: Deepest nodes are on the left side
        if (left.depth > right.depth) {
            return new Result(left.node, currentDepth);
        }
        
        // Case 3: Deepest nodes are on the right side
        return new Result(right.node, currentDepth);
    }

    // Helper method to print the tree in level-order for verification
    public static void printTree(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr != null) {
                System.out.print(curr.val + " ");
                queue.offer(curr.left);
                queue.offer(curr.right);
            }
        }
        System.out.println();
    }

    // Main function to execute and test the code
    public static void main(String[] args) {
        SmallestSubtreewithalltheDeepestNodes solver = new SmallestSubtreewithalltheDeepestNodes();

        /*
         * Constructing LeetCode Example 1 Tree:
         *             3
         *            / \
         *           5   1
         *          / \ / \
         *         6  2 0  8
         *           / \
         *          7   4
         * 
         * Deepest nodes are 7 and 4 (depth 4).
         * Smallest subtree containing both is node 2.
         */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        TreeNode result = solver.subtreeWithAllDeepest(root);
        
        System.out.print("Subtree root value (Expected: 2): ");
        System.out.println(result != null ? result.val : "null");
        
        System.out.print("Full qualifying subtree hierarchy: ");
        printTree(result);
    }
}


