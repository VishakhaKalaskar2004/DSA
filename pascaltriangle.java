import java.util.ArrayList;
import java.util.List;

public class pascaltriangle {
    // Method to generate Pascal's triangle up to a given number of rows
    public List<List<Integer>> generate(int numRow){
        // 'triangle' will store the entire Pascal's triangle
        List<List<Integer>> triangle = new ArrayList<>();

        // If the number of rows is 0 or less, return an empty triangle
        if(numRow <= 0){
            return triangle;
        }

        // The first row of Pascal's triangle is always [1]
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        triangle.add(firstRow);

        // Iterate from the second row (index 1) up to the requested number of rows
        for(int i = 1;i < numRow; i++){
            // Get the previous row to calculate the current row's values
            List<Integer> prevRow = triangle.get(i-1);
            ArrayList<Integer> currRow = new ArrayList<>();

            // The first element of every row is always 1
            currRow.add(1);

            // Calculate the inner elements of the current row
            // Each element is the sum of the two elements directly above it in the previous row
            for(int j = 1;j < i; j++){
                currRow.add(prevRow.get(j-1) + prevRow.get(j));
            }

            // The last element of every row is always 1
            currRow.add(1);
            
            // Add the newly generated row to the triangle
            triangle.add(currRow);
        }

        // Return the complete Pascal's triangle
        return triangle;

    } 
    public static void main(String[] args) {
        pascaltriangle pt = new pascaltriangle();
        int rows = 5;
        // Call the generate method to create a Pascal's triangle with 5 rows
        List<List<Integer>> result = pt.generate(rows);
        
        // Print the result to see the Pascal Triangle
        System.out.println(result);
}
}
