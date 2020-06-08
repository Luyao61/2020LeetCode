package A_Compoany_Coding;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Canvas {
    // id map to object reference. use char as id for simplicty;
    Map<Character, DLNode> map;
    int cRows;
    int cCols;
    DLNode head;
    DLNode tail;

    public Canvas(int rows, int columns) {
        this.cCols = columns;
        this.cRows = rows;
        this.map = new HashMap<>();
        head = new DLNode();
        tail = new DLNode();
        head.next = tail;
        tail.prev = head;
    }

    public void draw(char id, int x, int y, int height, int width) {
        Rectangle rect = new Rectangle(id, x, y, height, width);
        DLNode node = new DLNode();
        node.rect = rect;
        this._addToTail(node);
        map.put(id, node);

    }

    public void drag(char id, int[] offset) {
        Rectangle rect = map.get(id).rect;
        rect.x += offset[0];
        rect.y += offset[1];
    }

    public void moveToTop(char id) {
        DLNode node = map.get(id);
        this._removeNodeFromLink(node);
        this._addToTail(node);
    }

    public void delete(char id) {
        DLNode node = map.get(id);
        this._removeNodeFromLink(node);
        map.remove(id);
    }

    public void print() {
        char[][] canvas = new char[this.cRows][this.cCols];
        Arrays.stream(canvas).forEach(a -> Arrays.fill(a, '0'));

        DLNode node = head.next;
        while (node != tail) {
            Rectangle rect = node.rect;
            for (int i = 0; i < rect.height; i++) {
                for (int j = 0; j < rect.width; j++) {
                    int x = rect.x + i;
                    int y = rect.y + j;
                    if (this._isValidPoint(x, y)) {
                        canvas[x][y] = rect.id;
                    }
                }
            }
            node = node.next;
        }
        Arrays.stream(canvas).forEach(row -> {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        });
    }

    private void _addToTail(DLNode node) {
        tail.prev.next = node;
        node.prev = tail.prev;
        node.next = tail;
        tail.prev = node;
    }

    private void _removeNodeFromLink(DLNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private boolean _isValidPoint(int x, int y) {
        return x >= 0 && y >= 0 && x < this.cRows && y < this.cCols;
    }

    public static void main(String[] args) {
        Canvas myCanvas = new Canvas(10, 20);
        myCanvas.draw('a', 1, 1, 3, 40);
        myCanvas.draw('b', 0, 2, 2, 2);
        myCanvas.print();
    }
}

class DLNode {
    DLNode prev;
    DLNode next;
    Rectangle rect;
}

class Rectangle {
    char id;
    int x;
    int y;
    int height;
    int width;

    Rectangle(char id, int x, int y, int height, int width) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }
}