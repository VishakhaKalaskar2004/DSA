import java.util.Arrays;

public class TwoBestNonOverlappingEvents {

    public int maxTwoEvents(int[][] events) {
        int n = events.length;
        
        // Step 1: Sort events based on their start times
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Step 2: Create a suffix max array to store the maximum value from index i to n-1
        int[] maxValueFrom = new int[n];
        maxValueFrom[n - 1] = events[n - 1][2];
        for (int i = n - 2; i >= 0; i--) {
            maxValueFrom[i] = Math.max(events[i][2], maxValueFrom[i + 1]);
        }
        
        int maxTotalValue = 0;
        
        // Step 3: For each event, find the best non-overlapping next event
        for (int i = 0; i < n; i++) {
            // Case A: Choose only the current single event
            maxTotalValue = Math.max(maxTotalValue, events[i][2]);
            
            // Case B: Find a second non-overlapping event using binary search
            int nextEventIdx = findNextValidEvent(events, events[i][1], i + 1, n - 1);
            if (nextEventIdx != -1) {
                maxTotalValue = Math.max(maxTotalValue, events[i][2] + maxValueFrom[nextEventIdx]);
            }
        }
        
        return maxTotalValue;
    }
    
    // Binary search helper to find the first event with startTime > currentEndTime
    private int findNextValidEvent(int[][] events, int currentEndTime, int low, int high) {
        int result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (events[mid][0] > currentEndTime) {
                result = mid;
                high = mid - 1; // Try to find an even earlier valid start time
            } else {
                low = mid + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TwoBestNonOverlappingEvents sol = new TwoBestNonOverlappingEvents();

        // Example 1: Non-overlapping options exist
        int[][] events1 = {{1, 3, 2}, {4, 5, 2}, {2, 4, 3}};
        System.out.println("Example 1: " + sol.maxTwoEvents(events1)); // Expected: 4 (Event 1 and Event 2)

        // Example 2: Choosing overlapping maximum values vs single best
        int[][] events2 = {{1, 3, 2}, {4, 5, 2}, {1, 5, 5}};
        System.out.println("Example 2: " + sol.maxTwoEvents(events2)); // Expected: 5 (Only Event 3)

        // Example 3: All events overlap completely
        int[][] events3 = {{1, 5, 3}, {1, 5, 1}, {6, 6, 5}};
        System.out.println("Example 3: " + sol.maxTwoEvents(events3)); // Expected: 8 (Event 1 and Event 3)
    }
}


