public class LargestMagicSquare {
    
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // 1-based prefix sums for rows and columns
        int[][] rowPrefix = new int[m + 1][n + 1];
        int[][] colPrefix = new int[m + 1][n + 1];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowPrefix[i + 1][j + 1] = rowPrefix[i + 1][j] + grid[i][j];
                colPrefix[i + 1][j + 1] = colPrefix[i][j + 1] + grid[i][j];
            }
        }
        
        // Check all possible square sizes starting from the maximum possible down to 1
        for (int k = Math.min(m, n); k >= 1; k--) {
            for (int r = 0; r <= m - k; r++) {
                for (int c = 0; c <= n - k; c++) {
                    if (isValidMagicSquare(r, c, k, grid, rowPrefix, colPrefix)) {
                        return k;
                    }
                }
            }
        }
        return 1;
    }
    
    private boolean isValidMagicSquare(int r, int c, int k, int[][] grid, int[][] rowPrefix, int[][] colPrefix) {
        int expectedSum = 0;
        
        // Calculate the sum of the first row of this subgrid
        for (int j = c; j < c + k; j++) {
            expectedSum += grid[r][j];
        }
        
        // Check remaining rows
        for (int i = r + 1; i < r + k; i++) {
            int rowSum = rowPrefix[i + 1][c + k] - rowPrefix[i + 1][c];
            if (rowSum != expectedSum) return false;
        }
        
        // Check columns
        for (int j = c; j < c + k; j++) {
            int colSum = colPrefix[r + k][j + 1] - colPrefix[r][j + 1];
            if (colSum != expectedSum) return false;
        }
        
        // Check main diagonal
        int mainDiagSum = 0;
        for (int i = 0; i < k; i++) {
            mainDiagSum += grid[r + i][c + i];
        }
        if (mainDiagSum != expectedSum) return false;
        
        // Check anti-diagonal
        int antiDiagSum = 0;
        for (int i = 0; i < k; i++) {
            antiDiagSum += grid[r + i][c + k - 1 - i];
        }
        if (antiDiagSum != expectedSum) return false;
        
        return true;
    }

    public static void main(String[] args) {
        LargestMagicSquare solution = new LargestMagicSquare();
        
        // Example test case
        int[][] grid = {
            {7, 1, 4, 5, 6},
            {2, 5, 1, 6, 4},
            {8, 3, 5, 2, 9}
        };
        
        int result = solution.largestMagicSquare(grid);
        System.out.println("The size of the largest magic square is: " + result);
    }
}


