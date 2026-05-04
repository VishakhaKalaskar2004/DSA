import java.util.HashMap;
import java.util.Map;

public class MinimumAbsoluteDistanceBetweenMirrorPairs {

    public int minMirrorPairDistance(int[] nums) {
        int n = nums.length;
        // Map to store: reversedNumber -> lastIndexSeen
        Map<Integer, Integer> map = new HashMap<>();
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            // If current number matches a previously stored reversed number
            if (map.containsKey(nums[i])) {
                minDistance = Math.min(minDistance, i - map.get(nums[i]));
            }
            // Store the reverse of the current number and its index
            map.put(reverse(nums[i]), i);
        }

        return (minDistance == Integer.MAX_VALUE) ? -1 : minDistance;
    }

    // Helper function to reverse an integer
    private int reverse(int x) {
        int reversed = 0;
        while (x > 0) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }
        return reversed;
    }

    public static void main(String[] args) {
        MinimumAbsoluteDistanceBetweenMirrorPairs sol = new MinimumAbsoluteDistanceBetweenMirrorPairs();
        
        // Test Case 1: Example with mirror pairs
        int[] nums1 = {12, 34, 21, 56, 43}; 
        // 12 reversed is 21 (indices 0 and 2) -> dist 2
        // 34 reversed is 43 (indices 1 and 4) -> dist 3
        System.out.println("Result 1: " + sol.minMirrorPairDistance(nums1)); // Expected: 2

        // Test Case 2: No pairs
        int[] nums2 = {1, 2, 3, 4};
        System.out.println("Result 2: " + sol.minMirrorPairDistance(nums2)); // Expected: -1

        // Test Case 3: Palindrome Numbers
        int[] nums3 = {121, 55, 121};
        System.out.println("Result 3: " + sol.minMirrorPairDistance(nums3)); // Expected: 2
    }
}


