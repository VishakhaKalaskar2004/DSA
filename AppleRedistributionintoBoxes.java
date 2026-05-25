import java.util.Arrays;

public class AppleRedistributionintoBoxes {

    public int minimumBoxes(int[] apple, int[] capacity) {
        // Step 1: Calculate the total number of apples
        int totalApples = 0;
        for (int count : apple) {
            totalApples += count;
        }

        // Step 2: Sort the box capacities in ascending order
        Arrays.sort(capacity);

        // Step 3: Use the largest boxes first (from right to left)
        int boxesUsed = 0;
        int currentCapacity = 0;
        
        for (int i = capacity.length - 1; i >= 0; i--) {
            currentCapacity += capacity[i];
            boxesUsed++;
            
            // If the selected boxes can hold all apples, stop
            if (currentCapacity >= totalApples) {
                return boxesUsed;
            }
        }

        return boxesUsed;
    }

    public static void main(String[] args) {
        AppleRedistributionintoBoxes sol = new AppleRedistributionintoBoxes();

        // Example 1
        int[] apple1 = {1, 3, 2};
        int[] capacity1 = {4, 3, 1, 5, 2};
        System.out.println("Example 1: " + sol.minimumBoxes(apple1, capacity1)); // Expected: 2

        // Example 2
        int[] apple2 = {5, 5, 5};
        int[] capacity2 = {2, 4, 2, 7};
        System.out.println("Example 2: " + sol.minimumBoxes(apple2, capacity2)); // Expected: 4
    }
}

