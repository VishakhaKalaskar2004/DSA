 import java.util.Arrays;

public class RotatingtheBox {

    public char[][] rotateTheBox(char[][] boxGrid) {
        int m = boxGrid.length;
        int n = boxGrid[0].length;
        
        char[][] res = new char[n][m];
        
        // 1. Rotate 90 degrees clockwise
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[j][m - 1 - i] = boxGrid[i][j];
            }
        }
        
        // 2. Apply gravity
        for (int j = 0; j < m; j++) {
            int lowestAvailableRow = n - 1;
            for (int i = n - 1; i >= 0; i--) {
                if (res[i][j] == '*') {
                    lowestAvailableRow = i - 1;
                } else if (res[i][j] == '#') {
                    // Move stone to the lowest available spot
                    res[i][j] = '.';
                    res[lowestAvailableRow][j] = '#';
                    lowestAvailableRow--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        RotatingtheBox sol = new RotatingtheBox();

        // Example: A 2x3 box
        // '#' = stone, '*' = obstacle, '.' = empty
        char[][] boxGrid = {
            {'#', '.', '*', '.'},
            {'#', '#', '*', '.'}
        };

        System.out.println("--- Original Box ---");
        printBox(boxGrid);

        char[][] result = sol.rotateTheBox(boxGrid);

        System.out.println("\n--- Rotated Box with Gravity ---");
        printBox(result);
    }

    // Helper method to display the grid nicely in the terminal
    private static void printBox(char[][] box) {
        for (char[] row : box) {
            System.out.println(Arrays.toString(row));
        }
    }
}


