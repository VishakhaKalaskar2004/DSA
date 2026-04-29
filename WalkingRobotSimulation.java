import java.util.HashSet;
import java.util.Set;


public class WalkingRobotSimulation {

    public int robotSim(int[] commands, int[][] obstacles) {
        // Directions: 0-North, 1-East, 2-South, 3-West
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        // Store obstacles in a Set for O(1) lookup
        // We use a string "x,y" as a key because int[] doesn't work well in Sets
        Set<String> obstacleSet = new HashSet<>();
        for (int[] obs : obstacles) {
            obstacleSet.add(obs[0] + "," + obs[1]);
        }
        
        int x = 0, y = 0; // Starting position
        int dirIndex = 0; // Initially facing North
        int maxDistSquared = 0;
        
        for (int cmd : commands) {
            if (cmd == -1) {
                // Turn right: 0->1, 1->2, 2->3, 3->0
                dirIndex = (dirIndex + 1) % 4;
            } else if (cmd == -2) {
                // Turn left: 0->3, 3->2, 2->1, 1->0
                dirIndex = (dirIndex + 3) % 4;
            } else {
                // Move forward cmd steps
                for (int i = 0; i < cmd; i++) {
                    int nextX = x + directions[dirIndex][0];
                    int nextY = y + directions[dirIndex][1];
                    
                    // Check if the next position is an obstacle
                    if (obstacleSet.contains(nextX + "," + nextY)) {
                        break; // Stop moving in this direction
                    }
                    
                    x = nextX;
                    y = nextY;
                    maxDistSquared = Math.max(maxDistSquared, x * x + y * y);
                }
            }
        }
        
        return maxDistSquared;
    }

    // Add main function for testing
    public static void main(String[] args) {
        WalkingRobotSimulation sol = new WalkingRobotSimulation();
        
        // Test Case 1: Expected output: 25
        int[] commands1 = {4, -1, 3};
        int[][] obstacles1 = {};
        System.out.println("Test Case 1: " + sol.robotSim(commands1, obstacles1));
        
        // Test Case 2: Expected output: 65
        int[] commands2 = {4, -1, 4, -2, 4};
        int[][] obstacles2 = {{2, 4}};
        System.out.println("Test Case 2: " + sol.robotSim(commands2, obstacles2));
    }
}


