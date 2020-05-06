import helpers.ListNode;

class Solution25 implements LeetcodeSolution {
    // Toggle this bool;
    private boolean useRecursion = true;

    public ListNode reverseKGroup(ListNode head, int k) {
        if (this.useRecursion) {
            return this._reverseKGroupRecursion(head, k);
        }
        return this._reverseKGroupIterative(head, k);
    }

    private ListNode _reverseKGroupRecursion(ListNode headOfCurrentGroup, int k) {
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
        headOfCurrentGroup.next = this._reverseKGroupRecursion(headOfRemainingNodes, k);
        return newHeadOfCurrentGroup;
    }

    private ListNode _reverseKGroupIterative(ListNode head, int k) {
        ListNode iterator = head;
        ListNode headNode, prevTailNode;
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        prevTailNode = dummyNode;
        while (iterator != null) {
            int counter = 0;
            headNode = iterator;
            ListNode tailNode = null;
            while (counter < k && iterator != null) {
                tailNode = iterator;
                iterator = iterator.next;
                counter++;
            }
            ListNode newHead = null;
            if (counter == k) {
                counter = 0;
                tailNode.next = null;
                newHead = this.reverse(headNode);
            }
            prevTailNode.next = newHead != null ? newHead : headNode;
            if (newHead != null) {
                prevTailNode = headNode;
            }
        }
        return dummyNode.next;
    }

    public ListNode reverse(ListNode node) {
        if (useRecursion) {
            return this._reverseRecursion(node);
        }
        return this._reverseIterative(node);
    }

    private ListNode _reverseRecursion(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode newHead = reverse(node.next);
        node.next.next = node;
        node.next = null;
        return newHead;
    }

    private ListNode _reverseIterative(ListNode node) {
        if (node == null) {
            return node;
        }
        ListNode prev = null;
        ListNode currentNode = node;
        while (currentNode != null) {
            ListNode temp = currentNode.next;
            currentNode.next = prev;
            prev = currentNode;
            currentNode = temp;
        }
        return prev;
    }

    public void test() {
        this.test(false);
    }

    public void test(boolean useRecursion) {
        this.useRecursion = useRecursion;
        ListNode head = new ListNode(1);
        ListNode temp = head;
        for (int i = 2; i < 6; i++) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        System.out.println(head);
        ListNode newHead = this.reverseKGroup(head, 2);
        System.out.println(newHead);
    }
}