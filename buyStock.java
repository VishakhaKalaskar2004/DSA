public class buyStock {
                  
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        
        for (int price : prices) {
            // Update minPrice if we find a lower price
            if (price < minPrice) {
                minPrice = price;
            } 
            // Calculate profit and update maxProfit if selling now is better
            else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }
        
        return maxProfit;
    }
    public static void main(String[] args) {
        buyStock solution = new buyStock();
        
        // Example 1
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Maximum Profit 1: " + solution.maxProfit(prices1)); // Output: 5

        // Example 2
        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println("Maximum Profit 2: " + solution.maxProfit(prices2)); // Output: 0
    }
}

