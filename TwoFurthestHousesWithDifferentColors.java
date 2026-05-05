public class TwoFurthestHousesWithDifferentColors {
    public int maxDistance(int[] colors) {
        int n = colors.length;
        int maxDist = 0;

        // Case 1: Compare the first house with all houses from the end
        for (int i = n - 1; i > 0; i--) {
            if (colors[0] != colors[i]) {
                maxDist = Math.max(maxDist, i);
                break; // Found the furthest house from the start
            }
        }

        // Case 2: Compare the last house with all houses from the start
        for (int i = 0; i < n - 1; i++) {
            if (colors[n - 1] != colors[i]) {
                maxDist = Math.max(maxDist, (n - 1) - i);
                break; // Found the furthest house from the end
            }
        }

        return maxDist;
    }

    public static void main(String[] args) {
        TwoFurthestHousesWithDifferentColors sol = new TwoFurthestHousesWithDifferentColors();

        // Test Case 1
        int[] colors1 = {1, 1, 1, 6, 1, 1, 1};
        System.out.println("Input: [1, 1, 1, 6, 1, 1, 1]");
        System.out.println("Output: " + sol.maxDistance(colors1)); // Expected: 3 (index 0 and 3)

        // Test Case 2
        int[] colors2 = {1, 8, 3, 8, 3};
        System.out.println("\nInput: [1, 8, 3, 8, 3]");
        System.out.println("Output: " + sol.maxDistance(colors2)); // Expected: 4 (index 0 and 4)

        // Test Case 3
        int[] colors3 = {0, 1};
        System.out.println("\nInput: [0, 1]");
        System.out.println("Output: " + sol.maxDistance(colors3)); // Expected: 1
    }
}


