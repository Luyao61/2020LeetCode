import java.util.HashMap;

// https://leetcode.com/problems/lru-cache/
class LRUCache {
    int capacity;
    int size;
    DLinkedNode head;
    DLinkedNode tail;
    HashMap<Integer, DLinkedNode> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        head = new DLinkedNode(-1, -1);
        tail = new DLinkedNode(-1, -1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>(this.capacity);
    }

    private void removeNode(DLinkedNode node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    private void addToHead(DLinkedNode node) {
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    private void removeTail() {
        DLinkedNode last = tail.prev;
        removeNode(last);
        map.remove(last.key);
    }

    public int get(int key) {
        if (size == 0 || !map.containsKey(key)) {
            return -1;
        }
        DLinkedNode node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = map.get(key);
        if (node != null) {
            removeNode(node);
            addToHead(node);
            node.value = value;
        } else {
            if (size == capacity) {
                this.removeTail();
                size--;
            }
            DLinkedNode newNode = new DLinkedNode(key, value);
            map.put(key, newNode);
            this.addToHead(newNode);
            size++;
        }
    }
}

class DLinkedNode {
    DLinkedNode prev;
    DLinkedNode next;
    int key;
    int value;

    DLinkedNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj =
 * new LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */