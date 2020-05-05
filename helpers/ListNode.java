package helpers;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.val);
        sb.append(" -> ");
        ListNode next = this.next;
        while (next != null) {
            sb.append(next.val);
            sb.append(" -> ");
            next = next.next;
        }
        sb.append("x");
        return sb.toString();
    }
}