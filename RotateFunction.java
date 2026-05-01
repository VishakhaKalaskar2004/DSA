public class RotateFunction {

    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int sum = 0;
        int f = 0;

        // Calculate sum of all elements and F(0)
        // F(0) = 0*nums[0] + 1*nums[1] + ... + (n-1)*nums[n-1]
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            f += i * nums[i];
        }

        int max = f;

        // Use recurrence relation to find F(k) from F(k-1)
        // F(k) = F(k-1) + sum - n * nums[n-k]
        for (int i = 1; i < n; i++) {
            f = f + sum - n * nums[n - i];
            max = Math.max(max, f);
        }

        return max;
    }

    public static void main(String[] args) {
        RotateFunction sol = new RotateFunction();
        int[] nums = {4, 3, 2, 6};
        
        // Expected Output: 26
        // F(0) = 0*4 + 1*3 + 2*2 + 3*6 = 0 + 3 + 4 + 18 = 25
        // F(1) = 0*6 + 1*4 + 2*3 + 3*2 = 0 + 4 + 6 + 6 = 16
        // F(2) = 0*2 + 1*6 + 2*4 + 3*3 = 0 + 6 + 8 + 9 = 23
        // F(3) = 0*3 + 1*2 + 2*6 + 3*4 = 0 + 2 + 12 + 12 = 26
        // Max: 26
        System.out.println("Max Rotate Function Value: " + sol.maxRotateFunction(nums));
    }
}


