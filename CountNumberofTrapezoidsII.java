import java.util.HashMap;
import java.util.Map;

public class CountNumberofTrapezoidsII {
    
    // Custom keys designed for high-performance hashing to avoid primitive array allocations
    static class SlopeKey {
        int a, b;
        SlopeKey(int a, int b) { this.a = a; this.b = b; }
        @Override
        public boolean equals(Object o) {
            SlopeKey other = (SlopeKey) o;
            return this.a == other.a && this.b == other.b;
        }
        @Override
        public int hashCode() { return a * 31 + b; }
    }

    static class LineKey {
        int a, b, c;
        LineKey(int a, int b, int c) { this.a = a; this.b = b; this.c = c; }
        @Override
        public boolean equals(Object o) {
            LineKey other = (LineKey) o;
            return this.a == other.a && this.b == other.b && this.c == other.c;
        }
        @Override
        public int hashCode() { return (a * 31 + b) * 31 + c; }
    }

    static class SlopeLengthKey {
        int a, b, l;
        SlopeLengthKey(int a, int b, int l) { this.a = a; this.b = b; this.l = l; }
        @Override
        public boolean equals(Object o) {
            SlopeLengthKey other = (SlopeLengthKey) o;
            return this.a == other.a && this.b == other.b && this.l == other.l;
        }
        @Override
        public int hashCode() { return (a * 31 + b) * 31 + l; }
    }

    static class LineLengthKey {
        int a, b, c, l;
        LineLengthKey(int a, int b, int c, int l) { this.a = a; this.b = b; this.c = c; this.l = l; }
        @Override
        public boolean equals(Object o) {
            LineLengthKey other = (LineLengthKey) o;
            return this.a == other.a && this.b == other.b && this.c == other.c && this.l == other.l;
        }
        @Override
        public int hashCode() { return ((a * 31 + b) * 31 + c) * 31 + l; }
    }

    public int countTrapezoids(int[][] points) {
        int n = points.length;
        int result = 0;
        int sameLengthParallels = 0;

        // Hash maps to store frequencies of segments
        Map<SlopeKey, Integer> lookupSlope = new HashMap<>();
        Map<LineKey, Integer> lookupLine = new HashMap<>();
        Map<SlopeLengthKey, Integer> lookupSlopeLength = new HashMap<>();
        Map<LineLengthKey, Integer> lookupLineLength = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];
            
            for (int j = 0; j < i; j++) {
                int x2 = points[j][0];
                int y2 = points[j][1];

                int dx = x2 - x1;
                int dy = y2 - y1;
                int g = gcd(Math.abs(dx), Math.abs(dy));

                int a = dx / g;
                int b = dy / g;

                // Ensure canonical direction orientation
                if (a < 0 || (a == 0 && b < 0)) {
                    a = -a;
                    b = -b;
                }

                // Line equation constant: b * x - a * y = c
                int c = b * x1 - a * y1;
                int l = dx * dx + dy * dy;

                SlopeKey slopeKey = new SlopeKey(a, b);
                LineKey lineKey = new LineKey(a, b, c);
                SlopeLengthKey slopeLengthKey = new SlopeLengthKey(a, b, l);
                LineLengthKey lineLengthKey = new LineLengthKey(a, b, c, l);

                int currentSlopeCount = lookupSlope.getOrDefault(slopeKey, 0);
                int currentLineCount = lookupLine.getOrDefault(lineKey, 0);
                
                // Add all previously seen parallel segments that are not collinear
                result += (currentSlopeCount - currentLineCount);

                int currentSlopeLengthCount = lookupSlopeLength.getOrDefault(slopeLengthKey, 0);
                int currentLineLengthCount = lookupLineLength.getOrDefault(lineLengthKey, 0);
                
                // Track parallel segments of identical length to deduplicate parallelograms
                sameLengthParallels += (currentSlopeLengthCount - currentLineLengthCount);

                // Update frequency counts
                lookupSlope.put(slopeKey, currentSlopeCount + 1);
                lookupLine.put(lineKey, currentLineCount + 1);
                lookupSlopeLength.put(slopeLengthKey, currentSlopeLengthCount + 1);
                lookupLineLength.put(lineLengthKey, currentLineLengthCount + 1);
            }
        }

        // Deduplicate overcounted parallelograms
        return result - (sameLengthParallels / 2);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        CountNumberofTrapezoidsII solution = new CountNumberofTrapezoidsII();

        // Example 1
        int[][] points1 = {{-3, 2}, {3, 0}, {2, 3}, {3, 2}, {2, -3}};
        System.out.println("Example 1 Output: " + solution.countTrapezoids(points1)); // Expected: 2

        // Example 2
        int[][] points2 = {{0, 0}, {1, 0}, {0, 1}, {2, 1}};
        System.out.println("Example 2 Output: " + solution.countTrapezoids(points2)); // Expected: 1
    }
}


