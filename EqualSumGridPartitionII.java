import java.util.*;

public class EqualSumGridPartitionII {
    public boolean canPartitionGrid(int[][] grid) {
        int rows = grid.length;
        if (rows == 0) return false;
        int cols = grid[0].length;
        long totalSum = 0;
        Map<Integer, Integer> totalMap = new HashMap<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                totalSum += grid[i][j];
                totalMap.put(grid[i][j], totalMap.getOrDefault(grid[i][j], 0) + 1);
            }
        }

        // 1. Horizontal Cuts
        Map<Integer, Integer> topMap = new HashMap<>();
        long topSum = 0;
        for (int r = 0; r < rows - 1; r++) {
            for (int j = 0; j < cols; j++) {
                int val = grid[r][j];
                topSum += val;
                topMap.put(val, topMap.getOrDefault(val, 0) + 1);
            }
            long bottomSum = totalSum - topSum;
            
            if (hasValidDiscount(topMap, grid, 0, r + 1, 0, cols, topSum - bottomSum)) return true;
            if (hasValidDiscountInRemainder(totalMap, topMap, grid, r + 1, rows, 0, cols, bottomSum - topSum)) return true;
        }

        // 2. Vertical Cuts
        Map<Integer, Integer> leftMap = new HashMap<>();
        long leftSum = 0;
        for (int c = 0; c < cols - 1; c++) {
            for (int i = 0; i < rows; i++) {
                int val = grid[i][c];
                leftSum += val;
                leftMap.put(val, leftMap.getOrDefault(val, 0) + 1);
            }
            long rightSum = totalSum - leftSum;

            if (hasValidDiscount(leftMap, grid, 0, rows, 0, c + 1, leftSum - rightSum)) return true;
            if (hasValidDiscountInRemainder(totalMap, leftMap, grid, 0, rows, c + 1, cols, rightSum - leftSum)) return true;
        }

        return false;
    }

    private boolean hasValidDiscount(Map<Integer, Integer> sectionMap, int[][] grid, int rS, int rE, int cS, int cE, long diff) {
        if (diff == 0) return true;
        if (diff < Integer.MIN_VALUE || diff > Integer.MAX_VALUE) return false;
        int d = (int) diff;
        if (!sectionMap.containsKey(d)) return false;

        int h = rE - rS, w = cE - cS;
        if (h > 1 && w > 1) return true;
        if (h == 1) return grid[rS][cS] == d || grid[rS][cE - 1] == d;
        if (w == 1) return grid[rS][cS] == d || grid[rE - 1][cS] == d;
        return false;
    }

    private boolean hasValidDiscountInRemainder(Map<Integer, Integer> total, Map<Integer, Integer> part, int[][] grid, int rS, int rE, int cS, int cE, long diff) {
        if (diff == 0) return true;
        if (diff < Integer.MIN_VALUE || diff > Integer.MAX_VALUE) return false;
        int d = (int) diff;
        if (total.getOrDefault(d, 0) - part.getOrDefault(d, 0) <= 0) return false;

        int h = rE - rS, w = cE - cS;
        if (h > 1 && w > 1) return true;
        if (h == 1) return grid[rS][cS] == d || grid[rS][cE - 1] == d;
        if (w == 1) return grid[rS][cS] == d || grid[rE - 1][cS] == d;
        return false;
    }

    public static void main(String[] args) {
        EqualSumGridPartitionII sol = new EqualSumGridPartitionII();

        // Example 3: Should be false because discounting the 3 in the bottom row splits [2, 3, 5]
        int[][] grid3 = {{1, 2, 4}, {2, 3, 5}};
        System.out.println("Example 3 (Expected false): " + sol.canPartitionGrid(grid3));

        // Example 2: Should be true
        int[][] grid2 = {{1, 2}, {3, 4}};
        System.out.println("Example 2 (Expected true): " + sol.canPartitionGrid(grid2));
    }
}

