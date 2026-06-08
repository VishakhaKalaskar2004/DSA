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

public class CreateBinaryTreeFromDescriptions {
    public TreeNode createBinaryTree(int[][] descriptions) {
        // Map to store created nodes: Node Value -> TreeNode Object
        Map<Integer, TreeNode> nodeMap = new HashMap<>();
        // Set to store all values that appear as children
        Set<Integer> children = new HashSet<>();

        for (int[] desc : descriptions) {
            int parentVal = desc[0];
            int childVal = desc[1];
            boolean isLeft = desc[2] == 1;

            // Retrieve or create the parent node
            nodeMap.putIfAbsent(parentVal, new TreeNode(parentVal));
            TreeNode parentNode = nodeMap.get(parentVal);

            // Retrieve or create the child node
            nodeMap.putIfAbsent(childVal, new TreeNode(childVal));
            TreeNode childNode = nodeMap.get(childVal);

            // Connect the parent to the child based on the flag
            if (isLeft) {
                parentNode.left = childNode;
            } else {
                parentNode.right = childNode;
            }

            // Track that this node has a parent
            children.add(childVal);
        }

        // The root node is the only node in the map that is never a child
        for (int parentVal : nodeMap.keySet()) {
            if (!children.contains(parentVal)) {
                return nodeMap.get(parentVal);
            }
        }

        return null;
    }

    public static void main(String[] args) {
        CreateBinaryTreeFromDescriptions solution = new CreateBinaryTreeFromDescriptions();

        // Test Case 1
        // Descriptions: [parent, child, isLeft]
        int[][] descriptions1 = {
            {20, 15, 1}, // 20's left child is 15
            {20, 17, 0}, // 20's right child is 17
            {50, 20, 1}, // 50's left child is 20
            {50, 80, 0}, // 50's right child is 80
            {80, 19, 1}  // 80's left child is 19
        };

        TreeNode root1 = solution.createBinaryTree(descriptions1);
        System.out.print("Test Case 1 Root Value: " + (root1 != null ? root1.val : "null"));
        System.out.println(" (Expected: 50)");
        System.out.print("Level-order Traversal of Result: ");
        printTree(root1);

        // Test Case 2
        int[][] descriptions2 = {
            {1, 2, 1},   // 1's left child is 2
            {2, 3, 0},   // 2's right child is 3
            {3, 4, 1}    // 3's left child is 4
        };

        TreeNode root2 = solution.createBinaryTree(descriptions2);
        System.out.print("\nTest Case 2 Root Value: " + (root2 != null ? root2.val : "null"));
        System.out.println(" (Expected: 1)");
        System.out.print("Level-order Traversal of Result: ");
        printTree(root2);
    }

    // Helper method to print the tree in level-order format for verification
    private static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("[]");
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        List<String> result = new ArrayList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr != null) {
                result.add(String.valueOf(curr.val));
                queue.offer(curr.left);
                queue.offer(curr.right);
            } else {
                result.add("null");
            }
        }

        // Clean up trailing "null" strings for standard cleaner representation
        int lastIndex = result.size() - 1;
        while (lastIndex >= 0 && result.get(lastIndex).equals("null")) {
            result.remove(lastIndex);
            lastIndex--;
        }

        System.out.println(result);
    }
}

