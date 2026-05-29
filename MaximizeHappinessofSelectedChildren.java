import java.util.Arrays;


public class MaximizeHappinessofSelectedChildren {
    public long maximumHappinessSum(int[] happiness, int k) {
        // Sort the array to access the maximum values from the end
        Arrays.sort(happiness);
        
        long totalHappiness = 0;
        int n = happiness.length;
        
        // Pick k children greedily starting from the largest remaining value
        for (int i = 0; i < k; i++) {
            // Calculate happiness after decrementing for the current turn
            long currentHappiness = happiness[n - 1 - i] - i;
            
            // Happiness value cannot drop below 0
            if (currentHappiness > 0) {
                totalHappiness += currentHappiness;
            } else {
                // Since the array is sorted, remaining values will also be <= 0
                break;
            }
        }
        
        return totalHappiness;
    }

    public static void main(String[] args) {
        MaximizeHappinessofSelectedChildren solution = new MaximizeHappinessofSelectedChildren();
        
        // Test Case 1: Standard greedy picking
        int[] happiness1 = {1, 2, 3};
        int k1 = 2;
        System.out.println("Test Case 1 Output: " + solution.maximumHappinessSum(happiness1, k1)); 
        // Expected: 4 (Pick 3 -> becomes 3. Pick 2 -> turns to 1. Total = 3 + 1)
        
        // Test Case 2: Happiness drops to zero
        int[] happiness2 = {1, 1, 1, 1};
        int k2 = 2;
        System.out.println("Test Case 2 Output: " + solution.maximumHappinessSum(happiness2, k2)); 
        // Expected: 1 (Pick 1 -> becomes 1. Next picks decrement to 0. Total = 1)
        
        // Test Case 3: Larger values with multiple turns
        int[] happiness3 = {2, 3, 4, 5};
        int k3 = 1;
        System.out.println("Test Case 3 Output: " + solution.maximumHappinessSum(happiness3, k3)); 
        // Expected: 5 (Pick 5 -> becomes 5. Total = 5)
    }
}

