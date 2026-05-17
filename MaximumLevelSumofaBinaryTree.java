import java.util.LinkedList;
import java.util.Queue;


   
// Definition for a binary tree node provided by LeetCode
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

class MaximumLevelSumofaBinaryTree {
    public int maxLevelSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int maxSum = Integer.MIN_VALUE;
        int maxLevel = 1;
        int currentLevel = 1;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // Standard BFS (Level Order Traversal)
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int currentLevelSum = 0;

            // Process all nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevelSum += currentNode.val;

                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }

            // Update max sum and track the smallest level index if there is a tie
            if (currentLevelSum > maxSum) {
                maxSum = currentLevelSum;
                maxLevel = currentLevel;
            }

            currentLevel++;
        }

        return maxLevel;
    }

    public static void main(String[] args) {
        MaximumLevelSumofaBinaryTree solution = new MaximumLevelSumofaBinaryTree();

        // Creating a test binary tree:
        //        1
        //       / \
        //      7   0
        //     / \
        //    7  -8
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(7);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(-8);

        // Level 1 sum = 1
        // Level 2 sum = 7 + 0 = 7
        // Level 3 sum = 7 + (-8) = -1
        // Max sum is 7, which occurs at Level 2.
        System.out.println("Level with maximum sum: " + solution.maxLevelSum(root)); // Output: 2
    }
}


