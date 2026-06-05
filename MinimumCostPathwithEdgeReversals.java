import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MinimumCostPathwithEdgeReversals {


    public int minCost(int n, int[][] edges) {
        // Step 1: Build the augmented adjacency list
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            
            // Regular edge: cost is w
            adj.get(u).add(new int[]{v, w});
            // Flipped edge: cost is 2 * w
            adj.get(v).add(new int[]{u, 2 * w});
        }
        
        // Step 2: Initialize Dijkstra's Algorithm
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        
        // Priority Queue stores pairs of {cost, node}, sorted by cost ascending
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{0, 0});
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currentCost = curr[0];
            int u = curr[1];
            
            // Skip processing if a cheaper path to this node was already finalized
            if (currentCost > dist[u]) {
                continue;
            }
            
            // Optimization: Stop early if we reach the target node (n - 1)
            if (u == n - 1) {
                return currentCost;
            }
            
            // Explore neighbors
            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int weight = neighbor[1];
                
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }
        
        // Return -1 if destination node n-1 is structurally unreachable
        return dist[n - 1] == Integer.MAX_VALUE ? -1 : dist[n - 1];
    }

    public static void main(String[] args) {
        MinimumCostPathwithEdgeReversals solution = new MinimumCostPathwithEdgeReversals();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of nodes (n): ");
        int n = scanner.nextInt();

        System.out.print("Enter the number of edges: ");
        int numEdges = scanner.nextInt();

        int[][] edges = new int[numEdges][3];
        System.out.println("Enter edges in the format (u v cost) line by line:");
        for (int i = 0; i < numEdges; i++) {
            edges[i][0] = scanner.nextInt(); // Source node u
            edges[i][1] = scanner.nextInt(); // Target node v
            edges[i][2] = scanner.nextInt(); // Edge cost w
        }

        int result = solution.minCost(n, edges);
        System.out.println("The minimum cost to reach node " + (n - 1) + " is: " + result);

        scanner.close();
    }
}


