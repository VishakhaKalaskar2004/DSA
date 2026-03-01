public class findDuplicateNumber {

    /**
     * Finds the duplicate number in an array using Floyd's Tortoise and Hare algorithm.
     * The array should contain n + 1 integers where each integer is in the range [1, n].
     *
     * @param nums The input array of integers with exactly one duplicate.
     * @return The duplicate number.
     */
    public int findDuplicate(int[] nums) {
        // Phase 1: Find the intersection point of the two pointers.
        int slow = nums[0];
        int fast = nums[0];
        
        do { 
            slow = nums[slow];        // Slow pointer moves 1 step
            fast = nums[nums[fast]];  // Fast pointer moves 2 steps
        } while (slow != fast);

        // Phase 2: Find the entrance to the cycle (the duplicate number).
        int slow2 = nums[0];
        while (slow != slow2) {
            slow = nums[slow];
            slow2 = nums[slow2];
        }

        return slow; // Both pointers now point to the duplicate number
    }

    // Main function to demonstrate the usage of the findDuplicate method.
    public static void main(String[] args) {
        findDuplicateNumber solution = new findDuplicateNumber();
        
        // Example 1:
        int[] nums1 = {1, 3, 4, 2, 2};
        System.out.println("The duplicate number in {1, 3, 4, 2, 2} is: " + solution.findDuplicate(nums1)); // Output: 2

        // Example 2:
        int[] nums2 = {3, 1, 3, 4, 2};
        System.out.println("The duplicate number in {3, 1, 3, 4, 2} is: " + solution.findDuplicate(nums2)); // Output: 3

        // Example 3:
        int[] nums3 = {1, 1};
        System.out.println("The duplicate number in {1, 1} is: " + solution.findDuplicate(nums3)); // Output: 1
    }
}
