import java.util.Comparator;
import java.util.PriorityQueue;

class Solution407 implements LeetcodeSolution {
    public int trapRainWater(int[][] heightMap) {
        int water = 0;
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
            return water;
        }
        boolean[][] visited = new boolean[heightMap.length][heightMap[0].length];
        PriorityQueue<Cell> pq = new PriorityQueue<>(new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                return o1.value - o2.value;
            }
        });
        for (int i = 0; i < heightMap[0].length; i++) {
            pq.offer(new Cell(0, i, heightMap));
            pq.offer(new Cell(heightMap.length - 1, i, heightMap));
            visited[0][i] = true;
            visited[heightMap.length - 1][i] = true;
        }
        for (int i = 1; i < heightMap.length - 1; i++) {
            pq.offer(new Cell(i, 0, heightMap));
            pq.offer(new Cell(i, heightMap[0].length - 1, heightMap));
            visited[i][0] = true;
            visited[i][heightMap[0].length - 1] = true;
        }
        int[][] neighborsOffset = new int[][] { { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 } };
        while (pq.size() != 0) {
            Cell cell = pq.poll();
            for (int[] offset : neighborsOffset) {
                int x = cell.x + offset[0];
                int y = cell.y + offset[1];
                if (x >= 0 && x < heightMap.length && y >= 0 && y < heightMap[0].length && !visited[x][y]) {
                    // pq.offer(new Cell(x, y, heightMap));
                    visited[x][y] = true;
                    if (heightMap[x][y] < cell.value) {
                        water += cell.value - heightMap[x][y];
                    }
                    pq.offer(new Cell(x, y, Math.max(heightMap[x][y], cell.value)));

                }
            }
        }
        return water;
    }

    public void test() {
        int[][] heightMap = new int[][] { { 1, 4, 3, 1, 3, 2 }, { 3, 2, 1, 3, 2, 4 }, { 2, 3, 3, 2, 3, 1 } };
        System.out.println(trapRainWater(heightMap));
    }
}

class Cell {
    int x;
    int y;
    int value;

    Cell(int x, int y, int[][] heightMap) {
        this.x = x;
        this.y = y;
        this.value = heightMap[x][y];
    }

    Cell(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.value = val;
    }

}