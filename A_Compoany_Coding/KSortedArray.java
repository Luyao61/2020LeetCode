package A_Compoany_Coding;

import java.util.PriorityQueue;

class KSortedArray {

    void sort(int[] array, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < k + 1 && i < array.length; i++) {
            pq.offer(array[i]);
        }
        int index = 0;
        for (int i = pq.size(); i < array.length; i++) {
            array[index++] = pq.poll();
            pq.offer(array[i]);
        }
        while (pq.size() != 0) {
            array[index++] = pq.poll();
        }
    }
}