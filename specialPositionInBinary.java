public class specialPositionInBinary {
    public int numSpecial(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] rowSum = new int[m];
        int[] colSum = new int[n];
        int specialCount = 0;

        // First pass: Calculate the sum of 1s in each row and column
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    rowSum[i]++;
                    colSum[j]++;
                }
            }
        }

        // Second pass: Check for special positions
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1 && rowSum[i] == 1 && colSum[j] == 1) {
                    specialCount++;
                }
            }
        }

        return specialCount;
    }

    public static void main(String[] args) {
        specialPositionInBinary sol = new specialPositionInBinary();

        // Example 1
        int[][] mat1 = {
            {1, 0, 0},
            {0, 0, 1},
            {1, 0, 0}
        };
        System.out.println("Example 1 Output: " + sol.numSpecial(mat1)); // Expected: 1

        // Example 2
        int[][] mat2 = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };
        System.out.println("Example 2 Output: " + sol.numSpecial(mat2)); // Expected: 3
    }
}
