
public class DivideanArrayIntoSubarraysWithMinimumCostI {

    public int minimumCost(int[] nums) {
        // The first element is always included in the cost
        int first = nums[0];
        
        // Find the two smallest elements in the remaining part of the array
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min1) {
                min2 = min1;
                min1 = nums[i];
            } else if (nums[i] < min2) {
                min2 = nums[i];
            }
        }
        
        return first + min1 + min2;
    }

    public static void main(String[] args) {
        DivideanArrayIntoSubarraysWithMinimumCostI solution = new DivideanArrayIntoSubarraysWithMinimumCostI();

        // Test Case 1: Standard array (Expected output: 6)
        int[] test1 = {1, 2, 3, 12};
        System.out.println("Test 1: " + solution.minimumCost(test1)); 

        // Test Case 2: Unsorted array (Expected output: 10)
        int[] test2 = {5, 4, 3, 2, 1};
        System.out.println("Test 2: " + solution.minimumCost(test2)); 

        // Test Case 3: Duplicate small values (Expected output: 12)
        int[] test3 = {10, 1, 1, 1};
        System.out.println("Test 3: " + solution.minimumCost(test3)); 
    }
}


