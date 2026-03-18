import java.util.Arrays;

public class LargestSubmatrixWithRearrangements {

    /**
     * Finds the area of the largest submatrix with all ones after optimal column rearrangements.
     *
     * @param matrix The input binary matrix.
     * @return The area of the largest submatrix with all ones.
     */
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxArea = 0;

        // Iterate through each row
        for (int i = 0; i < m; ++i) {
            // Update the current row with the height of consecutive ones
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 1 && i > 0) {
                    matrix[i][j] += matrix[i - 1][j];
                }
            }

            // Create a copy and sort the current row's heights in descending order
            int[] currentRow = matrix[i].clone();
            Arrays.sort(currentRow);
            // After sorting in ascending order, iterate from the end to use descending logic

            // Calculate the largest possible area for this specific height configuration
            for (int j = 0; j < n; ++j) {
                int height = currentRow[j];
                int width = n - j; // Number of columns that can have at least this height
                maxArea = Math.max(maxArea, height * width);
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        LargestSubmatrixWithRearrangements solution = new LargestSubmatrixWithRearrangements();

        // Example 1
        int[][] matrix1 = {{0, 0, 1}, {1, 1, 1}, {1, 0, 1}};
        System.out.println("Example 1 Output: " + solution.largestSubmatrix(matrix1)); // Expected: 4

        // Example 2
        int[][] matrix2 = {{1, 0, 1, 0, 1}};
        System.out.println("Example 2 Output: " + solution.largestSubmatrix(matrix2)); // Expected: 3

        // Example 3
        int[][] matrix3 = {{1, 1, 0}, {1, 0, 1}};
        System.out.println("Example 3 Output: " + solution.largestSubmatrix(matrix3)); // Expected: 2
    }
}
