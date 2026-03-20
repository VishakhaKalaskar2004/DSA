import java.util.*;

class minAbsoluteDifference {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] ans = new int[m - k + 1][n - k + 1];

        for (int i = 0; i <= m - k; i++) {
            for (int j = 0; j <= n - k; j++) {
                Set<Integer> distinctSet = new HashSet<>();
                for (int x = i; x < i + k; x++) {
                    for (int y = j; y < j + k; y++) {
                        distinctSet.add(grid[x][y]);
                    }
                }

                if (distinctSet.size() < 2) {
                    ans[i][j] = 0;
                    continue;
                }

                List<Integer> sortedNums = new ArrayList<>(distinctSet);
                Collections.sort(sortedNums);

                int minDiff = Integer.MAX_VALUE;
                for (int t = 1; t < sortedNums.size(); t++) {
                    minDiff = Math.min(minDiff, sortedNums.get(t) - sortedNums.get(t - 1));
                }
                ans[i][j] = minDiff;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        minAbsoluteDifference sol = new minAbsoluteDifference();

        // Example Case
        int[][] grid = {{1, -2, 3}, {2, 3, 5}};
        int k = 2;

        int[][] result = sol.minAbsDiff(grid, k);

        // Print the result grid
        System.out.println(Arrays.deepToString(result));
    }
}
