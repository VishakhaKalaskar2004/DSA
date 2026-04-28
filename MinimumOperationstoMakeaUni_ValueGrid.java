import java.util.Arrays;

public class MinimumOperationstoMakeaUni_ValueGrid {
    
    
    /**
     * Calculates the minimum number of operations to make all grid elements equal.
     * All operations must change elements by an increment/decrement of x.
     */
    public int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;
        int totalElements = m * n;
        int[] nums = new int[totalElements];

        // 1. Flatten the grid and check modulo consistency
        int index = 0;
        int mod = grid[0][0] % x;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If any element doesn't have the same remainder as others when divided by x,
                // it is impossible to make them equal using steps of x.
                if (grid[i][j] % x != mod) {
                    return -1; 
                }
                nums[index++] = grid[i][j];
            }
        }

        // 2. Sort to find the median
        // The median is the optimal target value to minimize absolute differences.
        Arrays.sort(nums);
        int median = nums[totalElements / 2];

        // 3. Calculate operations
        int operations = 0;
        for (int num : nums) {
            operations += Math.abs(num - median) / x;
        }

        return operations;
    }

    /**
     * Entry point of the program.
     */
    public static void main(String[] args) {
        // Create an instance of the Solution class
        MinimumOperationstoMakeaUni_ValueGrid sol = new MinimumOperationstoMakeaUni_ValueGrid();

        // Sample input: 2D grid and increment value x
        int[][] grid = {
            {2, 4},
            {6, 8}
        };
        int x = 2;

        // Call the method and print the result to the console
        int result = sol.minOperations(grid, x);
        
        System.out.println("Input Grid: " + Arrays.deepToString(grid));
        System.out.println("Increment value (x): " + x);
        System.out.println("Minimum Operations: " + result);
    }
}


