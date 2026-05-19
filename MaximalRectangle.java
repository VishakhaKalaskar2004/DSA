import java.util.Stack;

public class MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int cols = matrix[0].length;
        int[] heights = new int[cols];
        int maxArea = 0;
        
        for (char[] row : matrix) {
            for (int j = 0; j < cols; j++) {
                heights[j] = (row[j] == '1') ? heights[j] + 1 : 0;
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        
        return maxArea;
    }
    
    private int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;
        
        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];
            
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        
        return maxArea;
    }

    public static void main(String[] args) {
        MaximalRectangle solution = new MaximalRectangle();

        // Test Case 1: Standard matrix with a 3x2 rectangle of '1's
        char[][] matrix1 = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };
        int result1 = solution.maximalRectangle(matrix1);
        System.out.println("Test Case 1 Result: " + result1); // Expected: 6

        // Test Case 2: Single element matrix ('0')
        char[][] matrix2 = {{'0'}};
        int result2 = solution.maximalRectangle(matrix2);
        System.out.println("Test Case 2 Result: " + result2); // Expected: 0

        // Test Case 3: Single element matrix ('1')
        char[][] matrix3 = {{'1'}};
        int result3 = solution.maximalRectangle(matrix3);
        System.out.println("Test Case 3 Result: " + result3); // Expected: 1
    }
}


