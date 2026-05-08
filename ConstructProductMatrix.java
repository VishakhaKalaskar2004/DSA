import java.util.Arrays;

public class ConstructProductMatrix {

    /**
     * Constructs a product matrix where each cell is the product of all other elements modulo 12345.
     * Uses prefix and suffix products to achieve O(n * m) time complexity.
     */
    public int[][] constructProductMatrix(int[][] grid) {
        final int MOD = 12345;
        int n = grid.length;
        int m = grid[0].length;
        int[][] res = new int[n][m];

        // Step 1: Calculate suffix products (backward pass)
        // res[i][j] will temporarily store the product of all elements appearing AFTER it
        long suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                res[i][j] = (int) suffix;
                suffix = (suffix * grid[i][j]) % MOD;
            }
        }

        // Step 2: Calculate prefix products (forward pass) and combine
        // Multiply the suffix product already in res[i][j] by the prefix product
        long prefix = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = (int) ((res[i][j] * prefix) % MOD);
                prefix = (prefix * grid[i][j]) % MOD;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        ConstructProductMatrix sol = new ConstructProductMatrix();

        // Example 1
        int[][] grid1 = {{1, 2}, {3, 4}};
        int[][] result1 = sol.constructProductMatrix(grid1);
        System.out.println("Input: [[1, 2], [3, 4]]");
        System.out.println("Output: " + Arrays.deepToString(result1));

        // Example 2
        int[][] grid2 = {{12345}, {2}, {1}};
        int[][] result2 = sol.constructProductMatrix(grid2);
        System.out.println("\nInput: [[12345], [2], [1]]");
        System.out.println("Output: " + Arrays.deepToString(result2));
    }
}


