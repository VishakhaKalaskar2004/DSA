import java.util.Arrays;


public class WalkingRobotSimulationII {

    private int width, height, perimeter;
    private int pos = 0;
    private boolean moved = false;

    public WalkingRobotSimulationII(int width, int height) {
        this.width = width;
        this.height = height;
        // Total steps to return to (0,0) is the sum of all sides minus 4 overlapping corners
        this.perimeter = 2 * (width + height - 2);
    }
    
    public void step(int num) {
        moved = true;
        // Use modulo to handle large step counts efficiently
        pos = (pos + num) % perimeter;
    }
    
    public int[] getPos() {
        if (pos < width) {
            return new int[]{pos, 0}; // Bottom side
        } else if (pos < width + height - 1) {
            return new int[]{width - 1, pos - (width - 1)}; // Right side
        } else if (pos < 2 * width + height - 2) {
            return new int[]{width - 1 - (pos - (width + height - 2)), height - 1}; // Top side
        } else {
            return new int[]{0, height - 1 - (pos - (2 * width + height - 3))}; // Left side
        }
    }
    
    public String getDir() {
        // Special case: If we've moved and are at (0,0), we are facing South
        if (moved && pos == 0) return "South";
        
        // Direction ranges based on current position index
        if (pos > 0 && pos < width) return "East";
        if (pos >= width && pos < width + height - 1) return "North";
        if (pos >= width + height - 1 && pos < 2 * width + height - 2) return "West";
        
        return "South";
    }

    // Main function for testing
    public static void main(String[] args) {
        // Robot of 6x3
        WalkingRobotSimulationII robot = new WalkingRobotSimulationII(6, 3);

        robot.step(2);
        System.out.println("Pos: " + Arrays.toString(robot.getPos()) + " Dir: " + robot.getDir()); 
        // Expected: [2, 0], East

        robot.step(2);
        System.out.println("Pos: " + Arrays.toString(robot.getPos()) + " Dir: " + robot.getDir()); 
        // Expected: [4, 0], East

        robot.step(2);
        System.out.println("Pos: " + Arrays.toString(robot.getPos()) + " Dir: " + robot.getDir()); 
        // Expected: [5, 1], North

        robot.step(1);
        System.out.println("Pos: " + Arrays.toString(robot.getPos()) + " Dir: " + robot.getDir()); 
        // Expected: [5, 2], North

        robot.step(4);
        System.out.println("Pos: " + Arrays.toString(robot.getPos()) + " Dir: " + robot.getDir()); 
        // Expected: [1, 2], West
    }
}


