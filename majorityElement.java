class majorityElement {
    /**
     * Finds the majority element in the array.
     * The majority element is the element that appears more than floor(n / 2) times.
     * You may assume that the array is non-empty and the majority element always exist in the array.
     *
     * This method uses the Boyer-Moore Voting Algorithm to efficiently find the majority element
     * in linear time O(n) and constant space O(1).
     *
     * @param nums The input integer array.
     * @return The majority element.
     */
    public int majorityElement(int[] nums) {
        // Initialize a counter to track the occurrences of the current candidate
        int count = 0;
        // Initialize a variable to store the potential majority element
        int candidate = 0;

        // Iterate through each number in the input array
        for (int num : nums) {
            // If the counter reaches 0, it means the current candidate is no longer a majority
            // in the remaining sequence, so we set a new candidate as the current number
            if (count == 0) {
                candidate = num;
            }

            // If the current number matches the candidate, increment the counter
            if (num == candidate) {
                count++;
            }
            // If it does not match, decrement the counter
            else {
                count--;
            }
        }

        // The algorithm guarantees that the final candidate is the majority element
        // because the majority element appears more than n/2 times, ensuring it
        // will always have a positive count at the end.
        return candidate;
    }

    /**
     * Main method to demonstrate the majorityElement function.
     *
     * @param args Command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Create an instance of the Solution class
        majorityElement solution = new majorityElement();

        // Example 1
        int[] nums1 = {3, 2, 3};
        // Call the majorityElement method and print the result
        System.out.println("The majority element in [3, 2, 3] is: " + solution.majorityElement(nums1)); // Output: 3

        // Example 2
        int[] nums2 = {2, 2, 1, 1, 1, 2, 2};
        // Call the majorityElement method and print the result
        System.out.println("The majority element in [2, 2, 1, 1, 1, 2, 2] is: " + solution.majorityElement(nums2)); // Output: 2

        // Example 3 (edge case with a larger array)
        int[] nums3 = {6, 5, 5, 5, 6, 6, 6};
        System.out.println("The majority element in [6, 5, 5, 5, 6, 6, 6] is: " + solution.majorityElement(nums3)); // Output: 6
    }
}
