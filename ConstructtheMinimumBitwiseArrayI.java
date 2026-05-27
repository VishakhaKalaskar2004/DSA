import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConstructtheMinimumBitwiseArrayI {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        
        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            
            if (num == 2) {
                ans[i] = -1; // 2 is even; no answer exists
            } else {
                // Find the rightmost '0' bit starting from bit position 1
                for (int bitPosition = 1; bitPosition < 32; bitPosition++) {
                    // Check if the bit at the current position is 0
                    if (((num >> bitPosition) & 1) == 0) {
                        // Flip the bit at (bitPosition - 1) from 1 to 0
                        ans[i] = num ^ (1 << (bitPosition - 1));
                        break;
                    }
                }
            }
        }
        
        return ans;
    }

    // Main function to test the solution
    public static void main(String[] args) {
        ConstructtheMinimumBitwiseArrayI solver = new ConstructtheMinimumBitwiseArrayI();

        // Test Case 1: Primes or odd numbers [2, 3, 5, 7]
        List<Integer> test1 = new ArrayList<>(Arrays.asList(2, 3, 5, 7));
        int[] result1 = solver.minBitwiseArray(test1);
        // Expected output: [-1, 1, 4, 3]
        System.out.println("Input: " + test1);
        System.out.println("Output: " + Arrays.toString(result1));
        System.out.println();

        // Test Case 2: Custom numbers [11, 13, 31]
        List<Integer> test2 = new ArrayList<>(Arrays.asList(11, 13, 31));
        int[] result2 = solver.minBitwiseArray(test2);
        // Expected output: [9, 12, 15]
        System.out.println("Input: " + test2);
        System.out.println("Output: " + Arrays.toString(result2));
    }
}


