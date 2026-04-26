import java.util.ArrayList;
import java.util.List;

public class WordsWithinTwoEditsofDictionary {

    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> result = new ArrayList<>();
        
        for (String query : queries) {
            for (String word : dictionary) {
                int diffCount = 0;
                for (int i = 0; i < query.length(); i++) {
                    if (query.charAt(i) != word.charAt(i)) {
                        diffCount++;
                    }
                    if (diffCount > 2) break;
                }
                
                if (diffCount <= 2) {
                    result.add(query);
                    break;
                }
            }
        }
        return result;
    }

    // Main function to test the solution
    public static void main(String[] args) {
        WordsWithinTwoEditsofDictionary sol = new WordsWithinTwoEditsofDictionary();

        // Sample test cases
        String[] queries = {"word", "note", "ants", "wood"};
        String[] dictionary = {"wood", "joke", "moat"};

        List<String> output = sol.twoEditWords(queries, dictionary);

        // Print results to the console
        System.out.println("Matching words: " + output);
    }
}


