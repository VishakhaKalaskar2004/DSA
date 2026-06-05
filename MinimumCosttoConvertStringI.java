import java.util.Arrays;
import java.util.Scanner;


public class MinimumCosttoConvertStringI {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        // Step 1: Initialize the distance graph for 26 lowercase English letters
        long[][] dist = new long[26][26];
        long INF = Long.MAX_VALUE / 2; // Prevent overflow during additions
        
        for (int i = 0; i < 26; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0; // Cost to transform a character to itself is 0
        }
        
        // Step 2: Fill in the direct conversion edges
        for (int i = 0; i < original.length; i++) {
            int u = original[i] - 'a';
            int v = changed[i] - 'a';
            dist[u][v] = Math.min(dist[u][v], cost[i]);
        }
        
        // Step 3: Compute all-pairs shortest paths using Floyd-Warshall
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
        
        // Step 4: Calculate the total minimum cost to convert source to target
        long totalCost = 0;
        for (int i = 0; i < source.length(); i++) {
            int u = source.charAt(i) - 'a';
            int v = target.charAt(i) - 'a';
            
            if (dist[u][v] == INF) {
                return -1; // Transformation is impossible
            }
            totalCost += dist[u][v];
        }
        
        return totalCost;
    }

    public static void main(String[] args) {
        MinimumCosttoConvertStringI solution = new MinimumCosttoConvertStringI();
        Scanner scanner = new Scanner(System.in);

        // Inputs for Source and Target Strings
        System.out.print("Enter source string: ");
        String source = scanner.next();

        System.out.print("Enter target string: ");
        String target = scanner.next();

        // Checking string structural length equality
        if (source.length() != target.length()) {
            System.out.println("Error: Source and target must be of equal length.");
            scanner.close();
            return;
        }

        // Inputs for character transformations
        System.out.print("Enter the number of transformation mappings: ");
        int m = scanner.nextInt();

        char[] original = new char[m];
        char[] changed = new char[m];
        int[] cost = new int[m];

        System.out.println("Enter transformations in format (original_char changed_char cost) line by line:");
        for (int i = 0; i < m; i++) {
            original[i] = scanner.next().charAt(0);
            changed[i] = scanner.next().charAt(0);
            cost[i] = scanner.nextInt();
        }

        long result = solution.minimumCost(source, target, original, changed, cost);
        System.out.println("The minimum conversion cost is: " + result);

        scanner.close();
    }
}


