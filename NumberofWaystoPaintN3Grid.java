public class NumberofWaystoPaintN3Grid {
    
    public int numOfWays(int n) {
        long mod = 1_000_000_007;
        
        // Base case for n = 1
        long aba = 6; // 3-color patterns (e.g., Red-Green-Red)
        long abc = 6; // 2-color patterns (e.g., Red-Green-Yellow)
        
        for (int i = 2; i <= n; i++) {
            long nextAba = (aba * 3 + abc * 2) % mod;
            long nextAbc = (aba * 2 + abc * 2) % mod;
            
            aba = nextAba;
            abc = nextAbc;
        }
        
        return (int) ((aba + abc) % mod);
    }

    public static void main(String[] args) {
        NumberofWaystoPaintN3Grid solution = new NumberofWaystoPaintN3Grid();

        // Test cases
        int n1 = 1;
        int n2 = 2;
        int n3 = 3;
        int n4 = 5000;

        System.out.println("Ways to color 3x" + n1 + " grid: " + solution.numOfWays(n1)); // Output: 12
        System.out.println("Ways to color 3x" + n2 + " grid: " + solution.numOfWays(n2)); // Output: 54
        System.out.println("Ways to color 3x" + n3 + " grid: " + solution.numOfWays(n3)); // Output: 246
        System.out.println("Ways to color 3x" + n4 + " grid: " + solution.numOfWays(n4)); // Output: 302283324
    }
}


