public class DetectCyclesin2DGrid {
    // 4 directions: Right, Left, Down, Up
    static int[] dirX = {1, -1, 0, 0};
    static int[] dirY = {0, 0, 1, -1};

    public boolean containsCycle(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];

        // Traverse every cell as a potential starting point
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    // Start DFS to check for a cycle in this component
                    // Passing -1, -1 as the initial parent coordinates
                    if (hasCycle(grid, visited, i, j, -1, -1, grid[i][j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean hasCycle(char[][] grid, boolean[][] visited, int r, int c, int pR, int pC, char val) {
        visited[r][c] = true;

        // Check 4 adjacent neighbors
        for (int i = 0; i < 4; i++) {
            int nR = r + dirX[i];
            int nC = c + dirY[i];

            // Bounds check
            if (nR >= 0 && nR < grid.length && nC >= 0 && nC < grid[0].length) {
                // Only consider same character
                if (grid[nR][nC] == val) {
                    if (visited[nR][nC]) {
                        // If neighbor is visited and NOT the parent, cycle found
                        if (nR != pR || nC != pC) {
                            return true;
                        }
                    } else {
                        // Recursively visit neighbor
                        if (hasCycle(grid, visited, nR, nC, r, c, val)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        DetectCyclesin2DGrid solver = new DetectCyclesin2DGrid();

        char[][] gridWithCycle = {
            {'a', 'a', 'a', 'a'},
            {'a', 'b', 'b', 'a'},
            {'a', 'b', 'b', 'a'},
            {'a', 'a', 'a', 'a'}
        };

        char[][] gridWithoutCycle = {
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'}
        };

        System.out.println("Grid 1 has cycle: " + solver.containsCycle(gridWithCycle));     // Expected: true
        System.out.println("Grid 2 has cycle: " + solver.containsCycle(gridWithoutCycle));  // Expected: false
    }
}
