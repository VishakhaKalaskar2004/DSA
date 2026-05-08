import java.util.*;


public class MinimumJumpstoReachEndviaPrimeTeleportation {

    private static final int MAX_VAL = 1000001;
    private static int[] minPrime = new int[MAX_VAL];

    // Static block to pre-calculate smallest prime factors (SPF) using Sieve
    static {
        for (int i = 2; i < MAX_VAL; i++) {
            if (minPrime[i] == 0) {
                for (int j = i; j < MAX_VAL; j += i) {
                    if (minPrime[j] == 0) minPrime[j] = i;
                }
            }
        }
    }

    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        // Map each prime factor to a list of indices where nums[i] is divisible by that prime
        Map<Integer, List<Integer>> primeToIndices = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int temp = nums[i];
            while (temp > 1) {
                int p = minPrime[temp];
                primeToIndices.computeIfAbsent(p, k -> new ArrayList<>()).add(i);
                while (temp % p == 0) temp /= p;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        boolean[] visited = new boolean[n];
        Set<Integer> visitedPrimes = new HashSet<>();
        visited[0] = true;
        int jumps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int currIdx = queue.poll();
                if (currIdx == n - 1) return jumps;

                // 1. Adjacent Steps (i+1, i-1)
                int[] neighbors = {currIdx - 1, currIdx + 1};
                for (int next : neighbors) {
                    if (next >= 0 && next < n && !visited[next]) {
                        visited[next] = true;
                        queue.add(next);
                    }
                }

                // 2. Prime Teleportation (if nums[currIdx] is prime)
                int val = nums[currIdx];
                if (val >= 2 && minPrime[val] == val) { // Check if nums[currIdx] is prime
                    if (!visitedPrimes.contains(val)) {
                        visitedPrimes.add(val);
                        for (int next : primeToIndices.getOrDefault(val, Collections.emptyList())) {
                            if (!visited[next]) {
                                visited[next] = true;
                                queue.add(next);
                            }
                        }
                    }
                }
            }
            jumps++;
        }
        return -1;
    }

    public static void main(String[] args) {
        MinimumJumpstoReachEndviaPrimeTeleportation sol = new MinimumJumpstoReachEndviaPrimeTeleportation();
        
        System.out.println(sol.minJumps(new int[]{1, 2, 4, 6}));      // Output: 2
        System.out.println(sol.minJumps(new int[]{2, 3, 4, 7, 9}));   // Output: 2
        System.out.println(sol.minJumps(new int[]{4, 6, 5, 8}));      // Output: 3
    }
}


