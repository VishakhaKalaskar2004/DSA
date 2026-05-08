
public class ThekthLexicographicalStringofAllHappyStringsofLengthn {

    private int count = 0;
    private String result = "";

    public String getHappyString(int n, int k) {
        count = 0;
        result = "";
        backtrack(n, k, new StringBuilder());
        return result;
    }

    private void backtrack(int n, int k, StringBuilder sb) {
        // If we found the k-th string, stop further recursion
        if (!result.isEmpty()) return;

        // Base case: happy string of length n reached
        if (sb.length() == n) {
            count++;
            if (count == k) {
                result = sb.toString();
            }
            return;
        }

        // Try adding 'a', 'b', and 'c' in order to maintain lexicographical sorting
        for (char c : new char[]{'a', 'b', 'c'}) {
            // Check "happy" condition: no two adjacent characters are the same
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == c) {
                continue;
            }
            
            sb.append(c);
            backtrack(n, k, sb);
            sb.deleteCharAt(sb.length() - 1); // Backtrack
        }
    }

    // Main function to test the implementation
    public static void main(String[] args) {
        ThekthLexicographicalStringofAllHappyStringsofLengthn sol = new ThekthLexicographicalStringofAllHappyStringsofLengthn();
        
        // Example 1: n = 1, k = 3 -> Expected: "c"
        System.out.println("n=1, k=3: " + sol.getHappyString(1, 3)); 
        
        // Example 2: n = 1, k = 4 -> Expected: ""
        System.out.println("n=1, k=4: \"" + sol.getHappyString(1, 4) + "\""); 
        
        // Example 3: n = 3, k = 9 -> Expected: "cab"
        System.out.println("n=3, k=9: " + sol.getHappyString(3, 9)); 
    }
}


