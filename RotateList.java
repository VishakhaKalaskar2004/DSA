
    // 1. The ListNode class must be defined so VS Code knows what it is
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class RotateList {
    // 2. Your LeetCode logic
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        ListNode tail = head;
        int length = 1;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        k = k % length;
        if (k == 0) return head;

        tail.next = head;

        int stepsToNewTail = length - k;
        ListNode newTail = tail; 
        for (int i = 0; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }

    // 3. The Main function to actually execute the code
    public static void main(String[] args) {
        RotateList sol = new RotateList();

        // Setup: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        int k = 2;

        System.out.println("Original List: 1 -> 2 -> 3 -> 4 -> 5");
        
        ListNode result = sol.rotateRight(head, k);

        System.out.print("Rotated List:  ");
        printList(result);
    }

    // Helper method to see the output in the console
    private static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + (node.next != null ? " -> " : ""));
            node = node.next;
        }
        System.out.println();
    }
}


