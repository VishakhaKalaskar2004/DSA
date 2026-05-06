import java.util.*;

public class MinimizeHammingDistanceAfterSwapOperations {

    private int[] parent;

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        // 1. Group indices into connected components using DSU
        for (int[] swap : allowedSwaps) {
            union(swap[0], swap[1]);
        }

        // 2. Map root index to a frequency map of numbers in that component
        Map<Integer, Map<Integer, Integer>> componentMaps = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = find(i);
            componentMaps.putIfAbsent(root, new HashMap<>());
            Map<Integer, Integer> freqMap = componentMaps.get(root);
            freqMap.put(source[i], freqMap.getOrDefault(source[i], 0) + 1);
        }

        // 3. Subtract target elements from their respective component's map
        int distance = 0;
        for (int i = 0; i < n; i++) {
            int root = find(i);
            Map<Integer, Integer> freqMap = componentMaps.get(root);
            int targetVal = target[i];

            if (freqMap.getOrDefault(targetVal, 0) > 0) {
                freqMap.put(targetVal, freqMap.get(targetVal) - 1);
            } else {
                distance++;
            }
        }

        return distance;
    }

    private int find(int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent[i]); // Path compression
    }

    private void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI != rootJ) parent[rootI] = rootJ;
    }

    // MAIN FUNCTION FOR VSCODE
    public static void main(String[] args) {
        MinimizeHammingDistanceAfterSwapOperations sol = new MinimizeHammingDistanceAfterSwapOperations();

        // Test Case 1
        int[] source1 = {1, 2, 3, 4};
        int[] target1 = {2, 1, 4, 5};
        int[][] swaps1 = {{0, 1}, {2, 3}};
        System.out.println("Test 1 Result: " + sol.minimumHammingDistance(source1, target1, swaps1)); // Expected: 1

        // Test Case 2
        int[] source2 = {1, 2, 3, 4};
        int[] target2 = {1, 3, 2, 4};
        int[][] swaps2 = {};
        System.out.println("Test 2 Result: " + sol.minimumHammingDistance(source2, target2, swaps2)); // Expected: 2

        // Test Case 3
        int[] source3 = {5, 1, 2, 4, 3};
        int[] target3 = {1, 5, 4, 2, 3};
        int[][] swaps3 = {{0, 4}, {4, 2}, {1, 3}, {1, 4}};
        System.out.println("Test 3 Result: " + sol.minimumHammingDistance(source3, target3, swaps3)); // Expected: 0
    }
}


