import java.util.*;

class RobotCollisions {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) indices[i] = i;

        // Sort robots by their physical position on the line
        Arrays.sort(indices, (a, b) -> Integer.compare(positions[a], positions[b]));

        Deque<Integer> stack = new ArrayDeque<>(); // Indices of right-moving robots

        for (int currentIndex : indices) {
            if (directions.charAt(currentIndex) == 'R') {
                stack.push(currentIndex);
            } else {
                // Left-moving robot collides with right-moving robots in the stack
                while (!stack.isEmpty() && healths[currentIndex] > 0) {
                    int topIndex = stack.pop();

                    if (healths[topIndex] > healths[currentIndex]) {
                        healths[topIndex] -= 1;
                        healths[currentIndex] = 0;
                        stack.push(topIndex); 
                    } else if (healths[topIndex] < healths[currentIndex]) {
                        healths[currentIndex] -= 1;
                        healths[topIndex] = 0;
                    } else {
                        healths[currentIndex] = 0;
                        healths[topIndex] = 0;
                    }
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int h : healths) {
            if (h > 0) result.add(h);
        }
        return result;
    }

    public static void main(String[] args) {
        RobotCollisions sol = new RobotCollisions();

        // Test Case 1: No collisions (all moving right)
        int[] p1 = {5, 4, 3, 2, 1};
        int[] h1 = {2, 17, 9, 15, 10};
        String d1 = "RRRRR";
        System.out.println("Test 1: " + sol.survivedRobotsHealths(p1, h1, d1)); 
        // Expected: [2, 17, 9, 15, 10]

        // Test Case 2: Multi-collision scenario
        int[] p2 = {3, 5, 2, 6};
        int[] h2 = {10, 10, 15, 12};
        String d2 = "RLRL";
        System.out.println("Test 2: " + sol.survivedRobotsHealths(p2, h2, d2)); 
        // Expected: [14] (Robot at pos 2 survives after colliding with robot at pos 6)

        // Test Case 3: All robots destroyed
        int[] p3 = {1, 2, 5, 6};
        int[] h3 = {10, 10, 11, 11};
        String d3 = "RLRL";
        System.out.println("Test 3: " + sol.survivedRobotsHealths(p3, h3, d3)); 
        // Expected: []
    }
}
