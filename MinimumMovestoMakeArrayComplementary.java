
public class MinimumMovestoMakeArrayComplementary {

    public int minMoves(int[] nums, int limit) {
        // Difference array to store change in moves for each possible target sum (2 to 2*limit)
        int[] diff = new int[2 * limit + 2];
        int n = nums.length;

        for (int i = 0; i < n / 2; i++) {
            int a = Math.min(nums[i], nums[n - 1 - i]);
            int b = Math.max(nums[i], nums[n - 1 - i]);

            // Range analysis for moves:
            // 2 moves: sums in [2, a + 1) and (b + limit, 2 * limit]
            // 1 move: sums in [a + 1, a + b) and (a + b, b + limit]
            // 0 moves: sum is exactly a + b

            // Default all sums in [2, 2*limit] to 2 moves
            diff[2] += 2;
            diff[2 * limit + 1] -= 2;

            // Reduce to 1 move for sums in [a + 1, b + limit]
            diff[a + 1] -= 1;
            diff[b + limit + 1] += 1;

            // Reduce to 0 moves for exactly a + b
            diff[a + b] -= 1;
            diff[a + b + 1] += 1;
        }

        int minMoves = n;
        int currentMoves = 0;
        // Sweep through the difference array to find the minimum
        for (int i = 2; i <= 2 * limit; i++) {
            currentMoves += diff[i];
            minMoves = Math.min(minMoves, currentMoves);
        }

        return minMoves;
    }

    public static void main(String[] args) {
        MinimumMovestoMakeArrayComplementary sol = new MinimumMovestoMakeArrayComplementary();

        // Example 1: nums = [1,2,4,3], limit = 4 -> Output: 1
        int[] nums1 = {1, 2, 4, 3};
        int limit1 = 4;
        System.out.println("Example 1: " + sol.minMoves(nums1, limit1));

        // Example 2: nums = [1,2,2,1], limit = 2 -> Output: 2
        int[] nums2 = {1, 2, 2, 1};
        int limit2 = 2;
        System.out.println("Example 2: " + sol.minMoves(nums2, limit2));

        // Example 3: nums = [1,2,1,2], limit = 2 -> Output: 0
        int[] nums3 = {1, 2, 1, 2};
        int limit3 = 2;
        System.out.println("Example 3: " + sol.minMoves(nums3, limit3));
    }
}


