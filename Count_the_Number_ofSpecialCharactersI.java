public class Count_the_Number_ofSpecialCharactersI {
    
    public int numberOfSpecialChars(String word) {
        // Arrays to track if a lowercase or uppercase letter has appeared
        boolean[] lower = new boolean[26];
        boolean[] upper = new boolean[26];
        
        // Mark the presence of each character
        for (char c : word.toCharArray()) {
            if (Character.isLowerCase(c)) {
                lower[c - 'a'] = true;
            } else if (Character.isUpperCase(c)) {
                upper[c - 'A'] = true;
            }
        }
        
        // Count how many letters have both lowercase and uppercase versions
        int specialCount = 0;
        for (int i = 0; i < 26; i++) {
            if (lower[i] && upper[i]) {
                specialCount++;
            }
        }
        
        return specialCount;
    }

    // Main function to test the solution
    public static void main(String[] args) {
        Count_the_Number_ofSpecialCharactersI solver = new Count_the_Number_ofSpecialCharactersI();

        // Test Case 1: Expected output: 3 (a, b, and c)
        String test1 = "aaAbcBC";
        System.out.println("Input: " + test1 + " -> Output: " + solver.numberOfSpecialChars(test1));

        // Test Case 2: Expected output: 0
        String test2 = "abc";
        System.out.println("Input: " + test2 + " -> Output: " + solver.numberOfSpecialChars(test2));

        // Test Case 3: Expected output: 1 (b)
        String test3 = "abBCab";
        System.out.println("Input: " + test3 + " -> Output: " + solver.numberOfSpecialChars(test3));
    }
}

