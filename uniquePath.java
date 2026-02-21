
class uniquePath {
    /**
     * Calculates the number of unique paths from the top-left corner to the bottom-right corner 
     * of an m x n grid, moving only down or right.
     * This uses dynamic programming with O(m*n) space complexity.
     *
     * @param m The number of rows in the grid.
     * @param n The number of columns in the grid.
     * @return The total number of unique paths.
     */
    public int uniquePaths(int m, int n) {
        // Create a 2D DP array to store the number of paths to each cell (i, j).
        // dp[i][j] represents the number of ways to reach cell (i, j) from (0, 0).
        int[][] dp = new int[m][n];
        
        // Initialize the first column with 1.
        // There is only one way to reach any cell (i, 0) by moving purely downwards from (0, 0).
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        
        // Initialize the first row with 1.
        // There is only one way to reach any cell (0, j) by moving purely rightwards from (0, 0).
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        
        // Fill the rest of the DP table using the recurrence relation.
        // To reach a cell (i, j), we can either come from the cell above (i-1, j) or 
        // the cell to the left (i, j-1).
        // dp[i][j] = dp[i-1][j] + dp[i][j-1]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        
        // The bottom-right corner value contains the total number of unique paths from 
        // the start (0, 0) to the end (m-1, n-1).
        return dp[m - 1][n - 1];
    }
    
    /**
     * Main method to demonstrate the uniquePaths function.
     */
    public static void main(String[] args) {
        uniquePath sol = new uniquePath();
        
        // Test case 1: 3x7 grid
        int m1 = 3;
        int n1 = 7;
        int paths1 = sol.uniquePaths(m1, n1);
        System.out.println("Number of unique paths in a " + m1 + "x" + n1 + " grid: " + paths1); // Expected: 28

        // Test case 2: 3x2 grid
        int m2 = 3;
        int n2 = 2;
        int paths2 = sol.uniquePaths(m2, n2);
        System.out.println("Number of unique paths in a " + m2 + "x" + n2 + " grid: " + paths2); // Expected: 3
    }
}
