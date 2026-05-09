import java.util.Arrays;

public class CyclicallyRotatingaGrid {

    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int numLayers = Math.min(m, n) / 2;

        for (int layer = 0; layer < numLayers; layer++) {
            int top = layer, left = layer;
            int bottom = m - 1 - layer, right = n - 1 - layer;
            
            // 1. Extract layer elements in CCW order
            int layerSize = 2 * (bottom - top + 1) + 2 * (right - left + 1) - 4;
            int[] elements = new int[layerSize];
            int idx = 0;

            for (int j = left; j < right; j++) elements[idx++] = grid[top][j];
            for (int i = top; i < bottom; i++) elements[idx++] = grid[i][right];
            for (int j = right; j > left; j--) elements[idx++] = grid[bottom][j];
            for (int i = bottom; i > top; i--) elements[idx++] = grid[i][left];

            // 2. Normalize k and check if rotation is needed
            int shift = k % layerSize;
            if (shift == 0) continue; 
            
            // 3. Write back using simple pointer increments to avoid modulo overhead
            idx = shift; 
            for (int j = left; j < right; j++) {
                grid[top][j] = elements[idx++];
                if (idx == layerSize) idx = 0;
            }
            for (int i = top; i < bottom; i++) {
                grid[i][right] = elements[idx++];
                if (idx == layerSize) idx = 0;
            }
            for (int j = right; j > left; j--) {
                grid[bottom][j] = elements[idx++];
                if (idx == layerSize) idx = 0;
            }
            for (int i = bottom; i > top; i--) {
                grid[i][left] = elements[idx++];
                if (idx == layerSize) idx = 0;
            }
        }
        return grid;
    }

    public static void main(String[] args) {
        CyclicallyRotatingaGrid sol = new CyclicallyRotatingaGrid();
        
        // Example test case
        int[][] grid = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };
        int k = 2;

        System.out.println("Original Grid:");
        printGrid(grid);

        int[][] result = sol.rotateGrid(grid, k);

        System.out.println("\nRotated Grid (k=" + k + "):");
        printGrid(result);
    }

    private static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
    }
}

