import java.util.*;

public class MinimumCostPathwithTeleportations {

    public int minCost(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int inf = Integer.MAX_VALUE / 2; // Prevent integer overflow during additions

        // f[t][i][j] stores the minimum cost to reach cell (i, j) with exactly t teleports
        int[][][] f = new int[k + 1][m][n];
        for (int t = 0; t <= k; t++) {
            for (int i = 0; i < m; i++) {
                Arrays.fill(f[t][i], inf);
            }
        }

        // Base case: Starting at (0, 0) costs 0 initially
        f[0][0][0] = 0;

        // Step 1: Pre-calculate Layer 0 (No teleports, purely moving right or down)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && f[0][i - 1][j] != inf) {
                    f[0][i][j] = Math.min(f[0][i][j], f[0][i - 1][j] + grid[i][j]);
                }
                if (j > 0 && f[0][i][j - 1] != inf) {
                    f[0][i][j] = Math.min(f[0][i][j], f[0][i][j - 1] + grid[i][j]);
                }
            }
        }

        // Step 2: Group all coordinates by their cell values to process teleport transitions
        TreeMap<Integer, List<int[]>> valueToPositions = new TreeMap<>(Collections.reverseOrder());
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                valueToPositions.computeIfAbsent(grid[i][j], z -> new ArrayList<>()).add(new int[]{i, j});
            }
        }

        // Step 3: Compute DP states for each teleportation layer from 1 to k
        for (int t = 1; t <= k; t++) {
            int mn = inf;

            // Process groups in descending order of cell values
            for (Map.Entry<Integer, List<int[]>> entry : valueToPositions.entrySet()) {
                List<int[]> positions = entry.getValue();

                // 1. Gather the minimum cost to teleport FROM any cell in this value group
                for (int[] pos : positions) {
                    mn = Math.min(mn, f[t - 1][pos[0]][pos[1]]);
                }

                // 2. Teleport TO any cell in this value group from a cell of equal or higher value
                if (mn != inf) {
                    for (int[] pos : positions) {
                        f[t][pos[0]][pos[1]] = Math.min(f[t][pos[0]][pos[1]], mn);
                    }
                }
            }

            // 3. Relax states using normal moves (down and right) for the current layer t
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i > 0 && f[t][i - 1][j] != inf) {
                        f[t][i][j] = Math.min(f[t][i][j], f[t][i - 1][j] + grid[i][j]);
                    }
                    if (j > 0 && f[t][i][j - 1] != inf) {
                        f[t][i][j] = Math.min(f[t][i][j], f[t][i][j - 1] + grid[i][j]);
                    }
                }
            }
        }

        // Step 4: Find the global minimum cost to reach the bottom-right cell
        int minTotalCost = inf;
        for (int t = 0; t <= k; t++) {
            minTotalCost = Math.min(minTotalCost, f[t][m - 1][n - 1]);
        }

        return minTotalCost == inf ? -1 : minTotalCost;
    }

    public static void main(String[] args) {
        MinimumCostPathwithTeleportations solution = new MinimumCostPathwithTeleportations();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of rows (m): ");
        int m = scanner.nextInt();
        System.out.print("Enter number of columns (n): ");
        int n = scanner.nextInt();

        int[][] grid = new int[m][n];
        System.out.println("Enter the grid elements row by row:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        System.out.print("Enter maximum allowed teleports (k): ");
        int k = scanner.nextInt();

        int result = solution.minCost(grid, k);
        System.out.println("The minimum path cost is: " + result);

        scanner.close();
    }
}


