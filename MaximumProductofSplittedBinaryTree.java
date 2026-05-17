public class MaximumProductofSplittedBinaryTree {
    
    private long maxProduct = 0;
    private long totalTreeSum = 0;
    private final int MOD = 1_000_000_007;

    public int maxProduct(TreeNode root) {
        maxProduct = 0;
        
        // Step 1: Calculate the total sum of the entire tree
        totalTreeSum = getSubtreeSum(root);
        
        // Step 2: Post-order traversal to check every possible split
        getSubtreeSum(root);
        
        // Return result modulo 10^9 + 7
        return (int) (maxProduct % MOD);
    }

    private long getSubtreeSum(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Post-order: Calculate the sum of left and right subtrees
        long leftSum = getSubtreeSum(node.left);
        long rightSum = getSubtreeSum(node.right);
        long currentSubtreeSum = node.val + leftSum + rightSum;

        // Calculate product if we cut the edge above the current node
        long remainingSum = totalTreeSum - currentSubtreeSum;
        long currentProduct = currentSubtreeSum * remainingSum;

        // Track the maximum product found
        if (currentProduct > maxProduct) {
            maxProduct = currentProduct;
        }

        return currentSubtreeSum;
    }

    public static void main(String[] args) {
        MaximumProductofSplittedBinaryTree solution = new MaximumProductofSplittedBinaryTree();

        // Constructing LeetCode Example 1 Tree:
        //        1
        //       / \
        //      2   3
        //     / \  /
        //    4  5 6
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        // Total sum = 1 + 2 + 3 + 4 + 5 + 6 = 21
        // Best split is cutting above node 3 (subtree sum = 3 + 6 = 9)
        // Product = 9 * (21 - 9) = 9 * 12 = 108
        System.out.println("Maximum Product of Splitted Binary Tree: " + solution.maxProduct(root)); // Output: 108
    }
}


