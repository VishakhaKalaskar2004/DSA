
public class FindtheStringwithLCP {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] s = new char[n];
        char cur = 'a';

        // 1. Greedy Assignment
        for (int i = 0; i < n; i++) {
            if (s[i] != 0) continue; // Already assigned
            if (cur > 'z') return ""; // Ran out of alphabet
            
            for (int j = i; j < n; j++) {
                if (lcp[i][j] > 0) {
                    s[j] = cur;
                }
            }
            cur++;
        }

        // 2. Validation of the constructed string against the LCP matrix
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int expectedLCP = 0;
                if (s[i] == s[j]) {
                    expectedLCP = (i + 1 < n && j + 1 < n) ? lcp[i + 1][j + 1] + 1 : 1;
                }
                
                if (lcp[i][j] != expectedLCP) {
                    return "";
                }
            }
        }

        return new String(s);
    }

    public static void main(String[] args) {
        FindtheStringwithLCP sol = new FindtheStringwithLCP();

        // Example 1
        int[][] lcp1 = {{4,0,2,0},{0,3,0,1},{2,0,2,0},{0,1,0,1}};
        System.out.println("Example 1: " + sol.findTheString(lcp1)); // Output: "abab"

        // Example 2
        int[][] lcp2 = {{4,3,2,1},{3,3,2,1},{2,2,2,1},{1,1,1,1}};
        System.out.println("Example 2: " + sol.findTheString(lcp2)); // Output: "aaaa"

        // Example 3
        int[][] lcp3 = {{4,3,2,1},{3,3,2,1},{2,2,2,1},{1,1,1,3}};
        System.out.println("Example 3: " + sol.findTheString(lcp3)); // Output: ""
    }
}

