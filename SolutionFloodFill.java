class SolutionFloodFill implements LeetcodeSolution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image[0] == null || sr < 0 || sc < 0 || sr >= image.length || sc >= image[0].length) {
            return image;
        }
        int colorToChnage = image[sr][sc];
        boolean[][] visited = new boolean[image.length][image[0].length];
        floodFill(image, sr, sc, colorToChnage, newColor, visited);
        return image;
    }
    public void floodFill(int[][] image, int sr, int sc, int color, int newColor, boolean[][] visited) {
        if (image == null || image[0] == null || sr < 0 || sc < 0 || sr >= image.length || sc >= image[0].length) {
            return;
        }
        if (visited[sr][sc]) {
            return;
        }
        if (image[sr][sc] != color) {
            return;
        }
        image[sr][sc] = newColor;
        visited[sr][sc] = true;
        floodFill(image, sr - 1, sc, color, newColor, visited);
        floodFill(image, sr, sc + 1, color, newColor, visited);
        floodFill(image, sr, sc - 1, color, newColor, visited);
        floodFill(image, sr + 1, sc, color, newColor, visited);
    }

    // [[1,1,1],[1,1,0],[1,0,1]]
    public void test() {
        int[][] image = new int[][] {
            {1,1,1}, {1,1,0}, {1,0,1}
        };
        image = floodFill(image, 1, 1, 2);
        for (int[] row: image) {
            for (int pixle: row) {
                System.out.print(pixle);
                System.out.print(",");
            }
            System.out.println("");
        }
    }
}