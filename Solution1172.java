import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.TreeSet;

// https://leetcode.com/problems/dinner-plate-stacks/
class Solution1172 implements LeetcodeSolution {
    public void test() {
        DinnerPlates dinnerPlates = new DinnerPlates(1);
        dinnerPlates.push(1);
        dinnerPlates.push(1);
        dinnerPlates.popAtStack(1);
        dinnerPlates.push(1);
        dinnerPlates.push(2);
        dinnerPlates.pop();
        dinnerPlates.pop();
        // 1
    }
}

class DinnerPlates {
    private int capacity;
    private ArrayList<ArrayDeque<Integer>> storage;
    private TreeSet<Integer> nonFullStacks;
    
    public DinnerPlates(int capacity) {
        this.capacity = capacity;    
        storage = new ArrayList<>();
        nonFullStacks = new TreeSet<>();
    }

    public void push(int val) {
        if (nonFullStacks.isEmpty()) {
            ArrayDeque<Integer> newStack = new ArrayDeque<>(capacity);
            storage.add(newStack);
            nonFullStacks.add(storage.size() - 1);
        }
        int firstNonFullStack = nonFullStacks.first();
        ArrayDeque<Integer> stack = storage.get(firstNonFullStack);
        stack.offerLast(val);
        if (stack.size() == capacity) {
            nonFullStacks.remove(firstNonFullStack);
        }
    }
    
    public int pop() {
        return popAtStack(storage.size() - 1);
    }
    
    public int popAtStack(int index) {
        if (index < 0 || index >= storage.size() || storage.get(index).isEmpty()) {
            return -1;
        }
        ArrayDeque<Integer> stack = storage.get(index);
        Integer val = stack.pollLast();
        nonFullStacks.add(index);
        while (storage.size() != 0 && storage.get(storage.size() - 1).isEmpty()) {
            int lastIndex = storage.size() - 1;
            storage.remove(lastIndex);
            nonFullStacks.remove(lastIndex);
        }
        return val;
    }
}