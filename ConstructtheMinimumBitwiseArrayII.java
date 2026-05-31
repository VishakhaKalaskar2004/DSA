import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ConstructtheMinimumBitwiseArrayII {


    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] result = new int[n];
        
        for (int i = 0; i < n; i++) {
            int currentNum = nums.get(i);
            
            // Special Case: 2 is the only even prime. 
            // x | (x + 1) always yields an odd number, making 2 impossible to reach.
            if (currentNum == 2) {
                result[i] = -1;
            } else {
                // Scan for the first 0 bit starting from index 1
                for (int bitPosition = 1; bitPosition < 32; bitPosition++) {
                    // Check if the bit at this position is 0
                    if (((currentNum >> bitPosition) & 1) == 0) {
                        // Change the 1-bit immediately to its right to a 0
                        result[i] = currentNum ^ (1 << (bitPosition - 1));
                        break;
                    }
                }
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        ConstructtheMinimumBitwiseArrayII solver = new ConstructtheMinimumBitwiseArrayII();

        // Test Case 1: Mixed odd primes and the special value 2
        List<Integer> nums1 = new ArrayList<>(Arrays.asList(2, 3, 5, 7));
        int[] result1 = solver.minBitwiseArray(nums1);
        System.out.println("Test 1 Result: " + Arrays.toString(result1));
        // Explanation: 
        // 2 -> -1
        // 3 (0b11) -> 1 (0b01) -> 1 | 2 = 3
        // 5 (0b101) -> 4 (0b100) -> 4 | 5 = 5
        // 7 (0b111) -> 3 (0b011) -> 3 | 4 = 7

        // Test Case 2: Larger prime numbers
        List<Integer> nums2 = new ArrayList<>(Arrays.asList(11, 13, 19));
        int[] result2 = solver.minBitwiseArray(nums2);
        System.out.println("Test 2 Result: " + Arrays.toString(result2));
    }
}

