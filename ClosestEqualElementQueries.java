import java.util.*;

public class ClosestEqualElementQueries {

    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        
        // Map to store sorted indices for each unique value
        Map<Integer, List<Integer>> valToIndices = new HashMap<>();
        for (int i = 0; i < n; i++) {
            valToIndices.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        // Cache for query results to handle duplicate queries efficiently
        Map<Integer, Integer> queryCache = new HashMap<>();

        for (int qIdx : queries) {
            // Optimization: If we already calculated for this specific index, reuse it
            if (queryCache.containsKey(qIdx)) {
                result.add(queryCache.get(qIdx));
                continue;
            }

            List<Integer> indices = valToIndices.get(nums[qIdx]);

            if (indices.size() <= 1) {
                queryCache.put(qIdx, -1);
                result.add(-1);
                continue;
            }

            // Use binary search to find the position of qIdx in the indices list
            int pos = Collections.binarySearch(indices, qIdx);
            
            // The nearest neighbors in a circular list are:
            // 1. (pos - 1 + size) % size
            // 2. (pos + 1) % size
            int prevIdx = indices.get((pos - 1 + indices.size()) % indices.size());
            int nextIdx = indices.get((pos + 1) % indices.size());

            // Calculate circular distances to these two neighbors
            int dist1 = Math.min(Math.abs(qIdx - prevIdx), n - Math.abs(qIdx - prevIdx));
            int dist2 = Math.min(Math.abs(qIdx - nextIdx), n - Math.abs(qIdx - nextIdx));

            int minDistance = Math.min(dist1, dist2);
            queryCache.put(qIdx, minDistance);
            result.add(minDistance);
        }

        return result;
    }

    public static void main(String[] args) {
        ClosestEqualElementQueries sol = new ClosestEqualElementQueries();
        int[] nums = {1, 3, 1, 4, 1, 3, 2};
        int[] queries = {0, 3, 5};
        System.out.println(sol.solveQueries(nums, queries)); // [2, -1, 3]
    }
}


