import java.util.*;

class CheckifThereisaValidPathinaGrid {
    // Directions for each street type: {dy, dx}
    int[][][] dirs = {
        {{0, -1}, {0, 1}},  // Type 1: left, right
        {{-1, 0}, {1, 0}},  // Type 2: up, down
        {{0, -1}, {1, 0}},  // Type 3: left, down
        {{0, 1}, {1, 0}},   // Type 4: right, down
        {{0, -1}, {-1, 0}}, // Type 5: left, up
        {{-1, 0}, {0, 1}}   // Type 6: up, right
    };

    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        
        queue.add(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1];
            if (r == m - 1 && c == n - 1) return true;

            int type = grid[r][c] - 1;
            for (int[] dir : dirs[type]) {
                int nr = r + dir[0], nc = c + dir[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {
                    // Back-connection check
                    int nType = grid[nr][nc] - 1;
                    for (int[] backDir : dirs[nType]) {
                        if (nr + backDir[0] == r && nc + backDir[1] == c) {
                            visited[nr][nc] = true;
                            queue.add(new int[]{nr, nc});
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CheckifThereisaValidPathinaGrid sol = new CheckifThereisaValidPathinaGrid();

        // Test Case 1: Valid path
        int[][] grid1 = {{2, 4, 3}, {6, 5, 2}};
        System.out.println("Test 1 (Expected True): " + sol.hasValidPath(grid1));

        // Test Case 2: Dead end/Invalid connection
        int[][] grid2 = {{1, 2, 1}, {1, 2, 1}};
        System.out.println("Test 2 (Expected False): " + sol.hasValidPath(grid2));

        // Test Case 3: Start doesn't connect to finish
        int[][] grid3 = {{1, 1, 2}};
        System.out.println("Test 3 (Expected False): " + sol.hasValidPath(grid3));
    }
}
