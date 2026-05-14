public class TrionicArrayI {
    
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        int i = 0;

        // Phase 1: Strictly Increasing
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            i++;
        }
        // Peak must not be at the very start (meaning no first increasing part)
        // or at the very end (meaning no decreasing part follows)
        if (i == 0 || i == n - 1) {
            return false;
        }

        // Phase 2: Strictly Decreasing
        int peak = i;
        while (i + 1 < n && nums[i] > nums[i + 1]) {
            i++;
        }
        // Valley must have moved from the peak and cannot be at the very end 
        // (meaning no second increasing part follows)
        if (i == peak || i == n - 1) {
            return false;
        }

        // Phase 3: Strictly Increasing
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            i++;
        }

        // The array is valid only if we successfully parsed the whole array to the end
        return i == n - 1;
    }

    public static void main(String[] args) {
        TrionicArrayI solution = new TrionicArrayI();

        // Test Case 1: Valid trionic array (1->3->5 up, 5->4->2 down, 2->6->8 up)
        int[] test1 = {1, 3, 5, 4, 2, 6, 8};
        System.out.println("Test 1 (Expected: true):  " + solution.isTrionic(test1));

        // Test Case 2: Invalid (No final increasing section)
        int[] test2 = {1, 4, 2, 1};
        System.out.println("Test 2 (Expected: false): " + solution.isTrionic(test2));

        // Test Case 3: Invalid (Contains flat duplicates, violates strict monotony)
        int[] test3 = {1, 3, 3, 2, 4};
        System.out.println("Test 3 (Expected: false): " + solution.isTrionic(test3));

        // Test Case 4: Minimal valid trionic array (1->3 up, 3->2 down, 2->4 up)
        int[] test4 = {1, 3, 2, 4};
        System.out.println("Test 4 (Expected: true):  " + solution.isTrionic(test4));
    }
}


