import java.util.*;

public class mergedIntervals {
    public int[][] merge(int[][] intervals){
        
        if(intervals.length <= 1){
            return intervals;
        }

        // Sort intervals by the start time
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        int[] currentInterval = intervals[0];
        merged.add(currentInterval);

        for(int[] interval: intervals){
            int currentEnd = currentInterval[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];

            // Check for overlap
            if(currentEnd >= nextStart){
                // Overlap exists, merge by updating the end time of the current interval
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            }
            else{
                // No overlap, the currentInterval is finalized, start a new one
                currentInterval = interval;
                merged.add(currentInterval);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

   public static void main(String[] args) {
        // Create an instance of the class to access the non-static merge method
        mergedIntervals solution = new mergedIntervals();

        // Example 1 Input
        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println("Input 1: " + Arrays.deepToString(intervals1));
        int[][] output1 = solution.merge(intervals1);
        System.out.println("Output 1: " + Arrays.deepToString(output1));
        // Expected Output 1: [[1, 6], [8, 10], [15, 18]]

        System.out.println("---------------------------------");

        // Example 2 (Another common test case)
        int[][] intervals2 = {{1, 4}, {4, 5}};
        System.out.println("Input 2: " + Arrays.deepToString(intervals2));
        int[][] output2 = solution.merge(intervals2);
        System.out.println("Output 2: " + Arrays.deepToString(output2));
        // Expected Output 2: [[1, 5]]

        System.out.println("---------------------------------");

        // Example 3 (Unsorted input to show the sorting functionality)
        int[][] intervals3 = {{4, 5}, {1, 4}, {0, 1}};
        System.out.println("Input 3: " + Arrays.deepToString(intervals3));
        int[][] output3 = solution.merge(intervals3);
        System.out.println("Output 3: " + Arrays.deepToString(output3));
        // Expected Output 3: [[0, 5]]
    }
}
