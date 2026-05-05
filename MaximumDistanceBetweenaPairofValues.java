class MaximumDistanceBetweenaPairofValues {
    public int maxDistance(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        int maxDist = 0;
        
        // Traverse both arrays simultaneously
        while (i < nums1.length && j < nums2.length) {
            // Check if current pair satisfies the condition
            if (nums1[i] <= nums2[j]) {
                // Update the maximum distance j - i
                maxDist = Math.max(maxDist, j - i);
                // Increment j to try and find a larger distance
                j++;
            } else {
                // If nums1[i] > nums2[j], nums1[i] is too large. 
                // Increment i to get a smaller (or equal) value from nums1
                i++;
                // Optimization: j must be at least i for a valid distance
                if (i > j) {
                    j = i;
                }
            }
        }
        return maxDist;
    }

    public static void main(String[] args) {
        MaximumDistanceBetweenaPairofValues sol = new MaximumDistanceBetweenaPairofValues();
        
        // Example test case: [55,30,5,4,2], [100,20,10,10,5]
        int[] nums1 = {55, 30, 5, 4, 2};
        int[] nums2 = {100, 20, 10, 10, 5};
        System.out.println("Max Distance: " + sol.maxDistance(nums1, nums2)); // Output: 2
    }
}
