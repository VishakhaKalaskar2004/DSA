import java.util.*;

public class GameIVJump {

    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n <= 1) return 0;

        // Step 1: Build a graph where same values are connected
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        // Step 2: BFS initialization
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.offer(0);
        visited[0] = true;
        int jumps = 0;

        // Step 3: Layer-by-layer traversal
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int currIdx = queue.poll();

                // Goal reached
                if (currIdx == n - 1) return jumps;

                // Option 1: Jump to same values
                List<Integer> neighbors = graph.get(arr[currIdx]);
                if (neighbors != null) {
                    for (int nextIdx : neighbors) {
                        if (!visited[nextIdx]) {
                            visited[nextIdx] = true;
                            queue.offer(nextIdx);
                        }
                    }
                    // CRITICAL: Clear to avoid redundant scans
                    graph.remove(arr[currIdx]);
                }

                // Option 2: Jump to next index
                if (currIdx + 1 < n && !visited[currIdx + 1]) {
                    visited[currIdx + 1] = true;
                    queue.offer(currIdx + 1);
                }

                // Option 3: Jump to previous index
                if (currIdx - 1 >= 0 && !visited[currIdx - 1]) {
                    visited[currIdx - 1] = true;
                    queue.offer(currIdx - 1);
                }
            }
            jumps++;
        }
        return -1;
    }

    // Main function to execute and test the code
    public static void main(String[] args) {
        GameIVJump solver = new GameIVJump();

        // Test Case: Expected output is 3
        // Path: 0 -> 4 -> 3 -> 9 (Indices)
        // Values: 100 -> 404 -> -23 -> 100
        int[] testArr = {100, -23, -23, 404, 100, 23, 23, 23, 3, 404};
        
        int result = solver.minJumps(testArr);
        System.out.println("Minimum jumps required: " + result);
    }
}


