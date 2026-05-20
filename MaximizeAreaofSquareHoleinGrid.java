import java.util.Arrays;

public class MaximizeAreaofSquareHoleinGrid {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int maxHGap = getMaxGap(hBars);
        int maxVGap = getMaxGap(vBars);
        
        int maxSide = Math.min(maxHGap, maxVGap);
        
        return maxSide * maxSide;
    }
    
    private int getMaxGap(int[] bars) {
        Arrays.sort(bars);
        
        int maxStreak = 1;
        int currentStreak = 1;
        
        for (int i = 1; i < bars.length; i++) {
            if (bars[i] == bars[i - 1] + 1) {
                currentStreak++;
            } else {
                maxStreak = Math.max(maxStreak, currentStreak);
                currentStreak = 1;
            }
        }
        maxStreak = Math.max(maxStreak, currentStreak);
        
        return maxStreak + 1;
    }

    public static void main(String[] args) {
        MaximizeAreaofSquareHoleinGrid sol = new MaximizeAreaofSquareHoleinGrid();

        // Test Case 1
        int n1 = 2, m1 = 1;
        int[] hBars1 = {2, 3};
        int[] vBars1 = {2};
        System.out.println("Test Case 1 Output: " + sol.maximizeSquareHoleArea(n1, m1, hBars1, vBars1)); 
        // Expected: 4

        // Test Case 2
        int n2 = 1, m2 = 1;
        int[] hBars2 = {2};
        int[] vBars2 = {2};
        System.out.println("Test Case 2 Output: " + sol.maximizeSquareHoleArea(n2, m2, hBars2, vBars2)); 
        // Expected: 4

        // Test Case 3
        int n3 = 2, m3 = 3;
        int[] hBars3 = {2, 3};
        int[] vBars3 = {2, 3, 4};
        System.out.println("Test Case 3 Output: " + sol.maximizeSquareHoleArea(n3, m3, hBars3, vBars3)); 
        // Expected: 9
    }
}


