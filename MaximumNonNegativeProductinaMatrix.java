public class MaximumNonNegativeProductinaMatrix {
    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long MOD = 1_000_000_007;

        long[][] dpMax = new long[m][n];
        long[][] dpMin = new long[m][n];

        dpMax[0][0] = dpMin[0][0] = grid[0][0];

        for (int i = 1; i < m; i++) {
            dpMax[i][0] = dpMin[i][0] = dpMax[i - 1][0] * grid[i][0];
        }

        for (int j = 1; j < n; j++) {
            dpMax[0][j] = dpMin[0][j] = dpMax[0][j - 1] * grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] < 0) {
                    dpMax[i][j] = Math.min(dpMin[i - 1][j], dpMin[i][j - 1]) * grid[i][j];
                    dpMin[i][j] = Math.max(dpMax[i - 1][j], dpMax[i][j - 1]) * grid[i][j];
                } else {
                    dpMax[i][j] = Math.max(dpMax[i - 1][j], dpMax[i][j - 1]) * grid[i][j];
                    dpMin[i][j] = Math.min(dpMin[i - 1][j], dpMin[i][j - 1]) * grid[i][j];
                }
            }
        }

        long result = dpMax[m - 1][n - 1];
        if (result < 0) {
            return -1;
        }
        return (int) (result % MOD);
    }

    public static void main(String[] args) {
       MaximumNonNegativeProductinaMatrix sol = new MaximumNonNegativeProductinaMatrix();
        
        // Example 1: [[-1,-2,-3],[4,5,-6],[7,8,9]] -> Expected: -1
        int[][] grid1 = {{-1,-2,-3}, {4,5,-6}, {7,8,9}};
        System.out.println("Example 1 Result: " + sol.maxProductPath(grid1));

        // Example 2: [[1,-2,1],[1,-2,1],[3,-4,1]] -> Expected: 8
        int[][] grid2 = {{1,-2,1}, {1,-2,1}, {3,-4,1}};
        System.out.println("Example 2 Result: " + sol.maxProductPath(grid2));
        
        // Example 3: [[1, 3], [3, 0]] -> Expected: 0
        int[][] grid3 = {{1, 3}, {3, 0}};
        System.out.println("Example 3 Result: " + sol.maxProductPath(grid3));

        // Example 4: [[1,1],[1,1]] -> Expected: 1
        int[][] grid4 = {{1,1}, {1,1}};
        System.out.println("Example 4 Result: " + sol.maxProductPath(grid4));
    }
}


