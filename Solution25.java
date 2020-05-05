import helpers.ListNode;

class Solution25 implements LeetcodeSolution {
    public ListNode reverseKGroup(ListNode head, int k) {
        return reverseByKGroup(head, k);
    }

    private ListNode reverseByKGroup(ListNode headOfCurrentGroup, int k) {
        int counter = 0;
        ListNode lastNode = headOfCurrentGroup;
        while (counter < k) {
            if (lastNode == null) {
                return headOfCurrentGroup;
            }
            if (counter == k - 1)
                break;
            lastNode = lastNode.next;
            counter++;
        }
        ListNode headOfRemainingNodes = lastNode.next;
        lastNode.next = null;
        ListNode newHeadOfCurrentGroup = reverse(headOfCurrentGroup);
        headOfCurrentGroup.next = reverseByKGroup(headOfRemainingNodes, k);
        return newHeadOfCurrentGroup;
    }

    private ListNode reverse(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode newHead = reverse(node.next);
        node.next.next = node;
        node.next = null;
        return newHead;
    }

    public void test() {
        ListNode head = new ListNode(0);
        ListNode temp = head;
        for (int i = 1; i < 5; i++) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        System.out.println(head);
        ListNode newHead = this.reverseKGroup(head, 2);
        System.out.println(newHead);
    }
}