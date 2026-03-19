public class ProblemSolution {
    /**
     * Calculates the number of submatrices starting from (0,0) that have an equal non-zero count of 'X' and 'Y'.
     * 
     * @param grid The input grid of characters.
     * @return The number of valid submatrices.
     */
    public int numberOfSubmatrices(char[][] grid) {
        final int m = grid.length;
        final int n = grid[0].length;
        int ans = 0;

        // x[i][j] := the number of 'X' in grid[0..i)[0..j)
        int[][] x = new int[m + 1][n + 1];
        // y[i][j] := the number of 'Y' in grid[0..i)[0..j)
        int[][] y = new int[m + 1][n + 1];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                x[i + 1][j + 1] = (grid[i][j] == 'X' ? 1 : 0) + x[i][j + 1] + x[i + 1][j] - x[i][j];
                y[i + 1][j + 1] = (grid[i][j] == 'Y' ? 1 : 0) + y[i][j + 1] + y[i + 1][j] - y[i][j];
                
                // Check the submatrix from (0, 0) to (i, j)
                if (x[i + 1][j + 1] > 0 && x[i + 1][j + 1] == y[i + 1][j + 1]) {
                    ++ans;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        ProblemSolution solution = new ProblemSolution();

        // Example 1
        char[][] grid1 = {
            {'X', 'Y', '.'},
            {'Y', '.', '.'}
        };
        System.out.println("Example 1 Output: " + solution.numberOfSubmatrices(grid1)); // Expected output: 1

        // Example 2
        char[][] grid2 = {
            {'X', 'X'},
            {'X', 'Y'}
        };
        System.out.println("Example 2 Output: " + solution.numberOfSubmatrices(grid2)); // Expected output: 0

        // Example 3
        char[][] grid3 = {
            {'.', '.'},
            {'.', '.'}
        };
        System.out.println("Example 3 Output: " + solution.numberOfSubmatrices(grid3)); // Expected output: 0

        // Additional test case
        char[][] grid4 = {
            {'X', 'Y'},
            {'X', 'Y'}
        };
        System.out.println("Example 4 Output: " + solution.numberOfSubmatrices(grid4)); // Expected output: 2 (submatrices up to (0,1) and (1,1))
    }
}
