class FlipSquaresSubmatrixVertically {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        // Iterate through the top half of the rows in the submatrix
        for (int i = x; i < x + k / 2; i++) {
            // Calculate the index of the corresponding row from the bottom half
            int i2 = x + k - 1 - (i - x);
            // Swap all elements in the current row with the corresponding bottom row
            for (int j = y; j < y + k; j++) {
                int t = grid[i][j];
                grid[i][j] = grid[i2][j];
                grid[i2][j] = t;
            }
        }
        return grid;
    }

    // Helper method to print the grid for verification
    public static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        FlipSquaresSubmatrixVertically sol = new FlipSquaresSubmatrixVertically();

        // Example 1
        int[][] grid1 = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };
        int x1 = 1, y1 = 1, k1 = 2;
        System.out.println("Original Grid 1:");
        printGrid(grid1);
        System.out.println("Reversed Submatrix (x=1, y=1, k=2):");
        sol.reverseSubmatrix(grid1, x1, y1, k1);
        printGrid(grid1);
        // Expected Output for grid1:
        // 1 2 3 4 
        // 5 10 11 8 
        // 9 6 7 12 
        // 13 14 15 16 

        System.out.println("-------------------------");

        // Example 2
        int[][] grid2 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int x2 = 0, y2 = 0, k2 = 3;
        System.out.println("Original Grid 2:");
        printGrid(grid2);
        System.out.println("Reversed Submatrix (x=0, y=0, k=3):");
        sol.reverseSubmatrix(grid2, x2, y2, k2);
        printGrid(grid2);
        // Expected Output for grid2:
        // 7 8 9 
        // 4 5 6 
        // 1 2 3 

        System.out.println("-------------------------");

        // Example 3: k=1 (no change expected)
        int[][] grid3 = {
            {1, 2},
            {3, 4}
        };
        int x3 = 0, y3 = 0, k3 = 1;
        System.out.println("Original Grid 3:");
        printGrid(grid3);
        System.out.println("Reversed Submatrix (x=0, y=0, k=1):");
        sol.reverseSubmatrix(grid3, x3, y3, k3);
        printGrid(grid3);
        // Expected Output for grid3:
        // 1 2 
        // 3 4 
    }
}
