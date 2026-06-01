import java.util.Arrays;

public class MinimumCostofBuyingCandiesWithDiscount {
    public int minimumCost(int[] cost) {
        // Step 1: Sort the array to easily pick the most expensive items first
        Arrays.sort(cost);
        int totalCost = 0;
        int n = cost.length;
        
        // Step 2: Traverse from right to left (descending order)
        // Buy 2 (indices i and i-1), skip 1 (index i-2)
        for (int i = n - 1; i >= 0; i--) {
            // Add first item
            totalCost += cost[i];
            i--;
            
            // Add second item if it exists
            if (i >= 0) {
                totalCost += cost[i];
                i--;
            }
            
            // The third item (i-2) is skipped/free - handled by i-- in the loop
        }
        
        return totalCost;
    }

    public static void main(String[] args) {
        MinimumCostofBuyingCandiesWithDiscount sol = new MinimumCostofBuyingCandiesWithDiscount();
        
        // Example test cases
        int[] cost1 = {1, 2, 3};
        int[] cost2 = {6, 5, 7, 9, 2, 2};
        int[] cost3 = {5, 5};
        
        System.out.println("Cost 1: " + sol.minimumCost(cost1)); // Output: 5
        System.out.println("Cost 2: " + sol.minimumCost(cost2)); // Output: 23
        System.out.println("Cost 3: " + sol.minimumCost(cost3)); // Output: 10
    }
}


