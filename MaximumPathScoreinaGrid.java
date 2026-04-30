
public class MaximumPathScoreinaGrid {
    private int[][] grid;
    private Integer[][][] memo;
    private final int INF = 1 << 30;

    /**
     * Calculates the maximum score achievable in an m x n grid 
     * without exceeding a total cost of k.
     * Rules: 0 adds 0 score/0 cost; 1 adds 1 score/1 cost; 2 adds 2 score/1 cost.
     */
    public int maxPathScore(int[][] grid, int k) {
        this.grid = grid;
        int m = grid.length;
        int n = grid[0].length;
        this.memo = new Integer[m][n][k + 1];
        
        int result = dfs(m - 1, n - 1, k);
        return result < 0 ? -1 : result;
    }

    private int dfs(int i, int j, int k) {
        // Base case: out of bounds or cost limit exceeded
        if (i < 0 || j < 0 || k < 0) {
            return -INF;
        }
        
        // Base case: reached the starting point
        if (i == 0 && j == 0) {
            return 0;
        }
        
        // Return memoized result if already computed
        if (memo[i][j][k] != null) {
            return memo[i][j][k];
        }

        int score = grid[i][j];
        int cost = (grid[i][j] > 0) ? 1 : 0;
        
        // Recurse from top and left neighbors
        int path1 = dfs(i - 1, j, k - cost);
        int path2 = dfs(i, j - 1, k - cost);
        
        int res = score + Math.max(path1, path2);
        return memo[i][j][k] = res;
    }

    // Main function to run example cases
    public static void main(String[] args) {
        MaximumPathScoreinaGrid sol = new MaximumPathScoreinaGrid();

        // Example 1: Basic valid path
        int[][] grid1 = {{0, 1}, {2, 0}};
        int k1 = 1;
        System.out.println("Test Case 1 (Expected 2): " + sol.maxPathScore(grid1, k1));

        // Example 2: Invalid path due to cost constraints
        int[][] grid2 = {{0, 1}, {1, 2}};
        int k2 = 1;
        System.out.println("Test Case 2 (Expected -1): " + sol.maxPathScore(grid2, k2));
    }
}
