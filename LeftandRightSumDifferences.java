public class LeftandRightSumDifferences {
    
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        
        // Calculate the total sum of all elements
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        int leftSum = 0;
        for (int i = 0; i < n; i++) {
            // Right sum is total sum minus the left sum and the current element
            int rightSum = totalSum - leftSum - nums[i];
            
            // Calculate the absolute difference
            answer[i] = Math.abs(leftSum - rightSum);
            
            // Add the current element to the left sum for the next iteration
            leftSum += nums[i];
        }
        
        return answer;
    }

    public static void main(String[] args) {
        LeftandRightSumDifferences solution = new LeftandRightSumDifferences();
        
        // Test Case 1
        int[] nums1 = {10, 4, 8, 3};
        int[] result1 = solution.leftRightDifference(nums1);
        System.out.print("Input: [10, 4, 8, 3] -> Output: ");
        printArray(result1); // Expected: [15, 1, 11, 22]
        
        // Test Case 2
        int[] nums2 = {1};
        int[] result2 = solution.leftRightDifference(nums2);
        System.out.print("Input: [1] -> Output: ");
        printArray(result2); // Expected: [0]
    }
    
    // Helper method to print the array output
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}

