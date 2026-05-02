public class MinimumDistancetotheTargetElement {

    public int getMinDistance(int[] nums, int target, int start) {
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            // Check if the current element matches the target
            if (nums[i] == target) {
                // Calculate absolute distance and update minDistance
                int currentDistance = Math.abs(i - start);
                minDistance = Math.min(minDistance, currentDistance);
                
                // Optimization: if we found distance 0, we can't get any closer
                if (minDistance == 0) return 0;
            }
        }
        return minDistance;
    }

    public static void main(String[] args) {
        MinimumDistancetotheTargetElement sol = new MinimumDistancetotheTargetElement();

        // Test Case 1
        int[] nums1 = {1, 2, 3, 4, 5};
        int target1 = 5;
        int start1 = 3;
        System.out.println("Result 1: " + sol.getMinDistance(nums1, target1, start1)); // Output: 1

        // Test Case 2
        int[] nums2 = {1};
        int target2 = 1;
        int start2 = 0;
        System.out.println("Result 2: " + sol.getMinDistance(nums2, target2, start2)); // Output: 0

        // Test Case 3
        int[] nums3 = {1, 1, 1, 1, 1};
        int target3 = 1;
        int start3 = 0;
        System.out.println("Result 3: " + sol.getMinDistance(nums3, target3, start3)); // Output: 0
    }
}

