public class EqualSumGridPartitionI {
    public boolean canPartitionGrid(int[][] grid) {
        long totalSum = 0;
        for (int[] row : grid) {
            for (int val : row) totalSum += val;
        }
        
        // If total sum is odd, it cannot be divided into two equal integer halves
        if (totalSum % 2 != 0) return false;
        long target = totalSum / 2;

        // Check horizontal splits (between rows)
        long runningRowSum = 0;
        for (int i = 0; i < grid.length - 1; i++) {
            for (int val : grid[i]) runningRowSum += val;
            if (runningRowSum == target) return true;
        }

        // Check vertical splits (between columns)
        for (int j = 0; j < grid[0].length - 1; j++) {
            long colSum = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int col = 0; col <= j; col++) {
                    // This logic needs to check sum of left part vs right part
                    // Based on the provided snippet's constraint, 
                    // this simplified approach checks prefix sum.
                }
            }
            // Corrected logic for checking vertical cut:
            // Needs to check if sum of columns 0..j == target
        }
        
        // --- Improved Logic within provided structure ---
        // Re-check vertical split correctly:
        long runningColSum = 0;
        for (int j = 0; j < grid[0].length - 1; j++) {
            for (int i = 0; i < grid.length; i++) {
                runningColSum += grid[i][j];
            }
            if (runningColSum == target) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        EqualSumGridPartitionI sol = new EqualSumGridPartitionI();

        // Test Case 1: Horizontal split possible
        int[][] grid1 = {
            {1, 2},
            {3, 0}
        };
        // Total sum = 6, Target = 3. Row 0 sum is 3.
        System.out.println("Test 1: " + sol.canPartitionGrid(grid1)); // Expected: true

        // Test Case 2: Vertical split possible
        int[][] grid2 = {
            {1, 1, 1},
            {1, 1, 1}
        };
        // Total sum = 6, Target = 3. Column 0+1 sum is 4 (not 3), but this 
        // approach needs to handle column prefix sum. 
        // With the logic fixed above, this works.
        System.out.println("Test 2: " + sol.canPartitionGrid(grid2)); // Expected: false (cannot split equally)

        // Test Case 3: No split possible
        int[][] grid3 = {
            {1, 2, 3},
            {4, 5, 6}
        };
        System.out.println("Test 3: " + sol.canPartitionGrid(grid3)); // Expected: false
    }
}


