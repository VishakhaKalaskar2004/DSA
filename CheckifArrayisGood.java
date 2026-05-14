import java.util.Arrays;

public class CheckifArrayisGood {
    
    public boolean isGood(int[] nums) {
        int n = nums.length - 1;
        int[] count = new int[n + 2];
        
        for (int num : nums) {
            if (num > n) {
                return false; // Value exceeds max valid integer n
            }
            count[num]++;
        }
        
        // n must appear exactly twice, and numbers 1 to n-1 must appear exactly once
        if (count[n] != 2) {
            return false;
        }
        
        for (int i = 1; i < n; i++) {
            if (count[i] != 1) {
                return false;
            }
        }
        
        return true;
    }

    public static void main(String[] args) {
        CheckifArrayisGood solution = new CheckifArrayisGood();

        // Test Case 1: A good array (should return true)
        int[] test1 = {1, 3, 3, 2};
        System.out.println("Test 1: " + solution.isGood(test1)); 

        // Test Case 2: Not a good array (should return false)
        int[] test2 = {1, 1};
        System.out.println("Test 2: " + solution.isGood(test2)); 

        // Test Case 3: A good array (should return true)
        int[] test3 = {1, 4, 5, 2, 3, 2};
        System.out.println("Test 3: " + solution.isGood(test3)); 
    }
}
