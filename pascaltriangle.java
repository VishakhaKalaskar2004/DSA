import java.util.ArrayList;
import java.util.List;

public class pascaltriangle {
    public List<List<Integer>> generate(int numRow){
        List<List<Integer>> triangle = new ArrayList<>();

        if(numRow <= 0){
            return triangle;
        }

        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        triangle.add(firstRow);

        for(int i = 1;i < numRow; i++){
            List<Integer> prevRow = triangle.get(i-1);
            ArrayList<Integer> currRow = new ArrayList<>();

            currRow.add(1);

            for(int j = 1;j < i; j++){
                currRow.add(prevRow.get(j-1) + prevRow.get(j));
            }

            currRow.add(1);
            triangle.add(currRow);
        }

        return triangle;

    } 
    public static void main(String[] args) {
        pascaltriangle pt = new pascaltriangle();
        int rows = 5;
        List<List<Integer>> result = pt.generate(rows);
        
        // Print the result to see the Pascal Triangle
        System.out.println(result);
}
}