import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MinimumAbsoluteDifference {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Step 1: Sort the array
        Arrays.sort(arr);
        
        // Step 2: Find the minimum absolute difference
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            int currentDiff = arr[i + 1] - arr[i];
            if (currentDiff < minDiff) {
                minDiff = currentDiff;
            }
        }
        
        // Step 3: Find all pairs with the minimum absolute difference
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] == minDiff) {
                result.add(Arrays.asList(arr[i], arr[i + 1]));
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        MinimumAbsoluteDifference solution = new MinimumAbsoluteDifference();
        Scanner scanner = new Scanner(System.in);

        // Input array size
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];

        // Input array elements
        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // Get the output
        List<List<Integer>> output = solution.minimumAbsDifference(arr);
        
        // Print the output
        System.out.println("Pairs with the minimum absolute difference: " + output);

        scanner.close();
    }
}


