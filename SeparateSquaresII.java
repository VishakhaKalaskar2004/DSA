import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SeparateSquaresII {
    // Segment tree to track unique union width of x-coordinates
    private static class SegmentTree {
        int[] treeCount;
        double[] treeLength;
        int[] xCoords;

        SegmentTree(int[] xCoords) {
            this.xCoords = xCoords;
            int n = xCoords.length;
            this.treeCount = new int[4 * n];
            this.treeLength = new double[4 * n];
        }

        void update(int node, int start, int end, int left, int right, int value) {
            if (left <= start && end <= right) {
                treeCount[node] += value;
            } else {
                int mid = start + (end - start) / 2;
                if (left < mid) {
                    update(2 * node, start, mid, left, right, value);
                }
                if (right > mid) {
                    update(2 * node + 1, mid, end, left, right, value);
                }
            }

            if (treeCount[node] > 0) {
                treeLength[node] = xCoords[end] - xCoords[start];
            } else if (end - start == 1) {
                treeLength[node] = 0;
            } else {
                treeLength[node] = treeLength[2 * node] + treeLength[2 * node + 1];
            }
        }
    }

    private static class Event implements Comparable<Event> {
        int y;
        int type; // 1 for bottom edge, -1 for top edge
        int x1, x2;

        Event(int y, int type, int x1, int x2) {
            this.y = y;
            this.type = type;
            this.x1 = x1;
            this.x2 = x2;
        }

        @Override
        public int compareTo(Event other) {
            return Integer.compare(this.y, other.y);
        }
    }

    public double separateSquares(int[][] squares) {
        List<Event> events = new ArrayList<>();
        int[] uniqueX = new int[squares.length * 2];
        int xIdx = 0;

        for (int[] sq : squares) {
            int x = sq[0];
            int y = sq[1];
            int l = sq[2];

            uniqueX[xIdx++] = x;
            uniqueX[xIdx++] = x + l;

            events.add(new Event(y, 1, x, x + l));
            events.add(new Event(y + l, -1, x, x + l));
        }

        // Coordinate Compression for X values
        Arrays.sort(uniqueX);
        int numUniqueX = 0;
        for (int i = 0; i < uniqueX.length; i++) {
            if (i == 0 || uniqueX[i] != uniqueX[i - 1]) {
                uniqueX[numUniqueX++] = uniqueX[i];
            }
        }
        int[] compressedX = Arrays.copyOf(uniqueX, numUniqueX);
        Collections.sort(events);

        // Helper to binary search the compressed x-index
        java.util.function.Function<Integer, Integer> getCompressedIndex = (val) -> {
            int idx = Arrays.binarySearch(compressedX, val);
            return idx < 0 ? -1 : idx;
        };

        SegmentTree st = new SegmentTree(compressedX);
        
        // Pass 1: Calculate Total Union Area
        double totalArea = 0.0;
        int prevY = events.get(0).y;

        for (Event event : events) {
            int currentY = event.y;
            double activeWidth = st.treeLength[1];
            totalArea += activeWidth * (currentY - prevY);

            int x1Idx = getCompressedIndex.apply(event.x1);
            int x2Idx = getCompressedIndex.apply(event.x2);
            st.update(1, 0, compressedX.length - 1, x1Idx, x2Idx, event.type);
            prevY = currentY;
        }

        // Pass 2: Line Sweep to pinpoint the split y-axis marker
        double targetArea = totalArea / 2.0;
        double currentAreaAccumulator = 0.0;
        prevY = events.get(0).y;

        // Reset Segment Tree state
        st = new SegmentTree(compressedX);

        for (Event event : events) {
            int currentY = event.y;
            double activeWidth = st.treeLength[1];
            double deltaArea = activeWidth * (currentY - prevY);

            if (currentAreaAccumulator + deltaArea >= targetArea) {
                double remainingAreaNeeded = targetArea - currentAreaAccumulator;
                return prevY + (remainingAreaNeeded / activeWidth);
            }

            currentAreaAccumulator += deltaArea;
            int x1Idx = getCompressedIndex.apply(event.x1);
            int x2Idx = getCompressedIndex.apply(event.x2);
            st.update(1, 0, compressedX.length - 1, x1Idx, x2Idx, event.type);
            prevY = currentY;
        }

        return prevY;
    }

    public static void main(String[] args) {
        SeparateSquaresII solution = new SeparateSquaresII();

        // Test Case 1: Separate unconnected non-overlapping squares
        int[][] squares1 = {{0, 0, 1}, {2, 2, 1}};
        double result1 = solution.separateSquares(squares1);
        System.out.printf("Test Case 1 Result: %.5f\n", result1); // Expected: 1.00000

        // Test Case 2: Overlapping configuration (Area counted once)
        int[][] squares2 = {{0, 0, 2}, {1, 1, 1}};
        double result2 = solution.separateSquares(squares2);
        System.out.printf("Test Case 2 Result: %.5f\n", result2); // Expected: 1.00000
    }
}


