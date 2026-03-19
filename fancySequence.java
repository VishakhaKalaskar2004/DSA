import java.util.ArrayList;
import java.util.List;

// Changed class name to FancyOperations
public class fancySequence {
    private List<Integer> list;

    // Constructor to initialize the list
    public fancySequence() {
        list = new ArrayList<>();
    }

    // Appends the value to the list
    public void append(int val) {
        list.add(val);
    }

    // Adds increment 'inc' to all existing elements
    public void addAll(int inc) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) + inc);
        }
    }

    // Multiplies all existing elements by 'm'
    public void multAll(int m) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) * m);
        }
    }

    // Returns the value at idx, or -1 if out of bounds
    public int getIndex(int idx) {
        if (idx < 0 || idx >= list.size()) {
            return -1;
        }
        return list.get(idx);
    }

    // Main function to demonstrate functionality
    public static void main(String[] args) {
        fancySequence fo = new fancySequence();
        fo.append(2);   // List: [2]
        fo.addAll(3);   // List: [2+3] = [5]
        fo.append(7);   // List: [5, 7]
        fo.multAll(2);  // List: [5*2, 7*2] = [10, 14]
        
        System.out.println(fo.getIndex(0)); // Output: 10
        System.out.println(fo.getIndex(1)); // Output: 14
    }
}
