import java.util.Arrays;

class MatrixCanBeObtainedByRotation{
    public boolean findRotation(int[][] mat, int[][] target) {
        // Check 4 possible rotations: 0, 90, 180, 270 degrees
        for (int i = 0; i < 4; i++) {
            if (Arrays.deepEquals(mat, target)) {
                return true;
            }
            rotate(mat);
        }
        return false;
    }

    // Helper method to rotate the matrix 90 degrees clockwise
    private void rotate(int[][] mat) {
        int n = mat.length;
        // Transpose the matrix
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }
        // Reverse each row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[i][n - 1 - j];
                mat[i][n - 1 - j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        MatrixCanBeObtainedByRotation sol = new MatrixCanBeObtainedByRotation();

        // Example 1: Rotatable
        int[][] mat1 = {{0,1},{1,0}};
        int[][] target1 = {{1,0},{0,1}};
        System.out.println("Example 1 Result: " + sol.findRotation(mat1, target1)); // Expected: true

        // Example 2: Not rotatable
        int[][] mat2 = {{0,1},{1,1}};
        int[][] target2 = {{1,0},{0,1}};
        System.out.println("Example 2 Result: " + sol.findRotation(mat2, target2)); // Expected: false

        // Example 3: Already the same
        int[][] mat3 = {{0,0,0},{0,1,0},{1,1,1}};
        int[][] target3 = {{0,0,0},{0,1,0},{1,1,1}};
        System.out.println("Example 3 Result: " + sol.findRotation(mat3, target3)); // Expected: true
    }
}
