class CountSubmatricesWithTopLeftElementAndSumLessThanK {
    /**
     * Counts the number of submatrices starting from the top-left (0,0) 
     * whose sum is less than or equal to k.
     * 
     * @param grid The input 2D integer array.
     * @param k The maximum allowed sum for a submatrix.
     * @return The number of valid submatrices.
     */
    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        // prefixSum[i][j] stores the sum of the submatrix from (0,0) to (i-1,j-1)
        int[][] prefixSum = new int[m + 1][n + 1];
        int count = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // Calculate current sum using prefix sums of top and left cells
                // Formula: current_sum = grid_value + top_sum + left_sum - top_left_overlap
                prefixSum[i][j] = grid[i - 1][j - 1] 
                                + prefixSum[i - 1][j] 
                                + prefixSum[i][j - 1] 
                                - prefixSum[i - 1][j - 1];
                
                // Check if this submatrix (0,0) to (i-1,j-1) is valid
                if (prefixSum[i][j] <= k) {
                    count++;
                }
            }
        }
        
        return count;
    }

    public static void main(String[] args) {
        CountSubmatricesWithTopLeftElementAndSumLessThanK solver = new CountSubmatricesWithTopLeftElementAndSumLessThanK();
        
        // Example 1:
        int[][] grid1 = {{7,2,9},{1,5,0},{2,6,6}};
        int k1 = 20;
        int result1 = solver.countSubmatrices(grid1, k1);
        System.out.println("For grid1 and k1=" + k1 + ", result: " + result1); // Expected: 6

        // Example 2:
        int[][] grid2 = {{1,1,1},{1,1,1},{1,1,1}};
        int k2 = 4;
        int result2 = solver.countSubmatrices(grid2, k2);
        System.out.println("For grid2 and k2=" + k2 + ", result: " + result2); // Expected: 6
        
        // Example 3:
        int[][] grid3 = {{1,0,1},{0,1,0},{1,0,1}};
        int k3 = 0;
        int result3 = solver.countSubmatrices(grid3, k3);
        System.out.println("For grid3 and k3=" + k3 + ", result: " + result3); // Expected: 0
    }
}
