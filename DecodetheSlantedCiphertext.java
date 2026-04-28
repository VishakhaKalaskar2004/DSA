public class DecodetheSlantedCiphertext {

    public String decodeCiphertext(String encodedText, int rows) {
        if (rows == 1) return encodedText;
        
        int n = encodedText.length();
        int cols = n / rows;
        StringBuilder sb = new StringBuilder();
        
        // Traverse each diagonal starting from each column in the first row
        for (int startCol = 0; startCol < cols; startCol++) {
            int r = 0;
            int c = startCol;
            
            // Move diagonally down-right (row+1, col+1)
            while (r < rows && c < cols) {
                // The linear index in the encodedText corresponds to (r * cols + c)
                sb.append(encodedText.charAt(r * cols + c));
                r++;
                c++;
            }
        }
        
        // Remove trailing spaces as per problem requirements
        int i = sb.length() - 1;
        while (i >= 0 && sb.charAt(i) == ' ') {
            i--;
        }
        return sb.substring(0, i + 1);
    }

    public static void main(String[] args) {
        DecodetheSlantedCiphertext sol = new DecodetheSlantedCiphertext();
        
        // Example 1
        System.out.println("Example 1: " + sol.decodeCiphertext("ch ie pr", 3)); 
        // Expected Output: "cipher"
        
        // Example 2
        System.out.println("Example 2: " + sol.decodeCiphertext("iveo eed l te olc", 4)); 
        // Expected Output: "i love leetcode"
        
        // Example 3
        System.out.println("Example 3: " + sol.decodeCiphertext("coding", 1)); 
        // Expected Output: "coding"
    }
}


