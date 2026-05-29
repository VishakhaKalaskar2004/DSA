import java.util.Arrays;

public class MinimumElementAfterReplacementWithDigitSum {

    public int minElement(int[] nums) {
        int minSum = Integer.MAX_VALUE;
        
        for (int num : nums) {
            int currentSum = 0;
            int temp = num;
            
            while (temp > 0) {
                currentSum += temp % 10;
                temp /= 10;
            }
            
            if (currentSum < minSum) {
                minSum = currentSum;
            }
        }
        
        return minSum;
    }

    public static void main(String[] args) {
        MinimumElementAfterReplacementWithDigitSum solution = new MinimumElementAfterReplacementWithDigitSum();
        
        // Test Case 1: Elements with various digit lengths
        int[] nums1 = {10, 12, 13, 14};
        System.out.println("Input: " + Arrays.toString(nums1));
        System.out.println("Output: " + solution.minElement(nums1)); // 10 -> 1+0=1 (Min)
        
        // Test Case 2: Mix of large and small numbers
        int[] nums2 = {999, 19, 111, 23};
        System.out.println("\nInput: " + Arrays.toString(nums2));
        System.out.println("Output: " + solution.minElement(nums2)); // 111 -> 1+1+1=3 (Min)
    }
}


