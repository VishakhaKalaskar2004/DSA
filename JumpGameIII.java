import java.util.LinkedList;
import java.util.Queue;

public class JumpGameIII {

    public boolean canReach(int[] arr, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // Found the target value 0
            if (arr[current] == 0) {
                return true;
            }

            // Already visited indices are marked with negative values
            if (arr[current] < 0) {
                continue;
            }

            int forward = current + arr[current];
            int backward = current - arr[current];

            // Mark the current index as visited by negating its value
            arr[current] = -arr[current];

            // Push valid, unvisited forward movements to the queue
            if (forward < arr.length && arr[forward] >= 0) {
                queue.add(forward);
            }

            // Push valid, unvisited backward movements to the queue
            if (backward >= 0 && arr[backward] >= 0) {
                queue.add(backward);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        JumpGameIII solution = new JumpGameIII();

        // Test cases
        int[] arr1 = {4, 2, 3, 0, 3, 1, 2};
        int start1 = 5;

        int[] arr2 = {4, 2, 3, 0, 3, 1, 2};
        int start2 = 0;

        int[] arr3 = {3, 0, 2, 1, 2};
        int start3 = 2;

        System.out.println("Can reach 0 (Test 1): " + solution.canReach(arr1, start1)); // Output: true
        System.out.println("Can reach 0 (Test 2): " + solution.canReach(arr2, start2)); // Output: true
        System.out.println("Can reach 0 (Test 3): " + solution.canReach(arr3, start3)); // Output: false
    }
}


