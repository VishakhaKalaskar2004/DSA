public class RobotReturnOrigin {
    

    // LeetCode 657: Robot Return to Origin Solution
    public boolean judgeCircle(String moves) {
        int x = 0, y = 0;
        // Iterate through each character in the string
        for (char move : moves.toCharArray()) {
            if (move == 'U') y++;
            else if (move == 'D') y--;
            else if (move == 'L') x--;
            else if (move == 'R') x++;
        }
        // If final position is (0,0), return true
        return x == 0 && y == 0;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        RobotReturnOrigin robot = new RobotReturnOrigin();

        // Test Cases
        String moves1 = "UD";
        String moves2 = "LL";

        System.out.println("Input: " + moves1 + " | Returns to origin: " + robot.judgeCircle(moves1));
        System.out.println("Input: " + moves2 + " | Returns to origin: " + robot.judgeCircle(moves2));
    }
}
