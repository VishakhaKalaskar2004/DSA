public class divisorsfour {
    
    public int sumFourDivisors(int[] nums) {
        int totalSum = 0;
        
        for (int num : nums) {
            int count = 0;
            int currentSum = 0;
            
            // Loop up to the square root of the number
            for (int i = 1; i * i <= num; i++) {
                if (num % i == 0) {
                    // i is a divisor
                    count++;
                    currentSum += i;
                    
                    // If the matching divisor is different, count and add it too
                    if (i * i != num) {
                        count++;
                        currentSum += num / i;
                    }
                }
                
                // Optimization: Break early if we exceed 4 divisors
                if (count > 4) {
                    break;
                }
            }
            
            // If the number has exactly 4 divisors, add their sum to our total
            if (count == 4) {
                totalSum += currentSum;
            }
        }
        
        return totalSum;
    }

    public static void main(String[] args) {
        divisorsfour solution = new divisorsfour();

        // Test case
        int[] nums = {21, 4, 7};
        
        // 21 has divisors: 1, 3, 7, 21 (Count = 4, Sum = 32)
        // 4 has divisors: 1, 2, 4 (Count = 3)
        // 7 has divisors: 1, 7 (Count = 2)
        System.out.println("Total sum of divisors: " + solution.sumFourDivisors(nums)); // Output: 32
    }
}


