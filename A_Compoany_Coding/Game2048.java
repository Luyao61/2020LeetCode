package A_Compoany_Coding;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Board {
  private int[][] board;
  private int size;

  public Board(int size) {
    this.size = size;
    this.board = new int[size][size];
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        board[i][j] = 0;
      }
    }
  }

  public int size() {
    return this.size;
  }

  public int get(int row, int col) {
    return this.board[row][col];
  }

  public void set(int row, int col, int val) {
    this.board[row][col] = val;
  }

  public int availableSlots() {
    int count = 0;
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        if (board[i][j] == 0) {
          count++;
        }
      }
    }
    return count;
  }

  public void addRandomNumber() {
    int avaiableSlots = this.availableSlots();
    int randomIndex = (int) Math.floor(Math.random() * avaiableSlots);
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        if (board[i][j] == 0) {
          randomIndex--;
        }
        if (randomIndex == 0) {
          board[i][j] = 2;
        }
      }
    }
  }

  public void addRandomNumberReservoirSampling() {
    int n = 0;
    int[] position = new int[2];
    for (int i = 0; i < this.size(); i++) {
      for (int j = 0; j < this.size(); j++) {
        if (this.board[i][j] == 0) {
          n++;
          int p = (int) Math.random() * n;
          if (p == 0) {
            position[0] = i;
            position[1] = j;
          }
        }
      }
    }
    board[position[0]][position[1]] = 2;
  }

  public boolean isFull() {
    return this.availableSlots() == 0;
  }
}

class Game2048 {
  private Board board;
  private int size;
  // private int score;

  public Game2048(int size) {
    this.size = size;
    this.board = new Board(size);
    this.init();
  }

  public void init() {
    this.board.addRandomNumber();
    this.board.addRandomNumber();
  }

  public boolean canMove() {
    if (!this.board.isFull()) {
      return true;
    }
    // check if any adjancet cells have same value;
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        // check cell[row][col] and cell[row][col+1]
        if (j != this.size - 1) {
          if (this.board.get(i, j) == this.board.get(i, j + 1)) {
            return true;
          }
        }
        // check cell[row][col] and cell[row + 1][col]
        if (i != this.size - 1) {
          if (this.board.get(i, j) == this.board.get(i + 1, j)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  // 0: up, 1: right, 2: down, 3: left
  public void move(int dir) {
    this.mergeCells(dir);
  }

  // 0: up, 1: right, 2: down, 3: left
  public int[] getVector(int dir) {
    int[] vector = new int[2];
    switch (dir) {
    case 0:
      vector[0] = -1;
      vector[1] = 0;
      break;
    case 1:
      vector[0] = 0;
      vector[1] = 1;
      break;
    case 2:
      vector[0] = 1;
      vector[1] = 0;
      break;
    case 3:
      vector[0] = 0;
      vector[1] = -1;
      break;
    }
    return vector;
  }

  public void mergeCells(int dir) {
    boolean[][] isMerged = new boolean[this.board.size()][this.board.size()];
    int[] vector = getVector(dir);
    int dx = vector[0], dy = vector[1];

    int traversalStartX = 0;
    int traversalIncrementX = 1;
    int traversalStartY = 0;
    int traversalIncrementY = 1;
    if (dir == 1 || dir == 2) {
      traversalStartX = this.board.size() - 1;
      traversalIncrementX = -1;
      traversalStartY = this.board.size() - 1;
      traversalIncrementY = -1;
    }

    for (int i = traversalStartX; i < this.board.size() && i >= 0; i += traversalIncrementX) {
      for (int j = traversalStartY; j < this.board.size() && j >= 0; j += traversalIncrementY) {
        int[] nextPos = findNextAvailablePosition(i, j, vector);
        int[] neighborPos = new int[] { nextPos[0] + dx, nextPos[1] + dy };
        if (isValid(neighborPos[0], neighborPos[1])
            && this.board.get(neighborPos[0], neighborPos[1]) == this.board.get(i, j)
            && !isMerged[neighborPos[0]][neighborPos[1]]) {
          int value = this.board.get(neighborPos[0], neighborPos[1]) * 2;
          this.board.set(neighborPos[0], neighborPos[1], value);
          isMerged[neighborPos[0]][neighborPos[1]] = true;
          this.board.set(i, j, 0);
        } else if (i != nextPos[0] || j != nextPos[1]) {
          int value = this.board.get(i, j);
          this.board.set(nextPos[0], nextPos[1], value);
          this.board.set(i, j, 0);
        }
      }
    }
  }

  public int[] findNextAvailablePosition(int i, int j, int[] vector) {
    int dx = vector[0], dy = vector[1];
    int destX = i, destY = j;
    int nextX = destX + dx, nextY = destY + dy;
    while (isValid(nextX, nextY) && this.board.get(nextX, nextY) == 0) {
      destX = nextX;
      destY = nextY;
      nextX = destX + dx;
      nextY = destY + dy;
    }
    return new int[] { destX, destY };
  }

  private boolean isValid(int x, int y) {
    return x >= 0 && y >= 0 && x < this.board.size() && y < this.board.size();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < this.board.size(); i++) {
      for (int j = 0; j < this.board.size(); j++) {
        String value = Integer.toString(this.board.get(i, j));
        sb.append(value);
        sb.append(IntStream.range(0, 5 - value.length()).mapToObj(e -> " ").collect(Collectors.joining("")));
        sb.append("|");
      }
      sb.append("\n");
    }
    return sb.toString();
  }

  public void initDefaultBoardForTest() {
    int[][] defaultBoard = new int[][] { { 2, 0, 0, 2 }, { 2, 2, 2, 2 }, { 2, 0, 4, 4 }, { 0, 8, 8, 16 }, };
    for (int i = 0; i < defaultBoard.length; i++) {
      for (int j = 0; j < defaultBoard[0].length; j++) {
        this.board.set(i, j, defaultBoard[i][j]);
      }
    }
  }

  public void rotateBoardForTest() {
    // rotate board 90 degrees colockwise
    int start = 0, end = this.board.size() - 1;
    while (start < end) {
      for (int i = start; i < end; i++) {
        int temp = this.board.get(start, start + i);
        this.board.set(start, start + i, this.board.get(end - i, start));
        this.board.set(end - i, start, this.board.get(end, end - i));
        this.board.set(end, end - i, this.board.get(start + i, end));
        this.board.set(start + i, end, temp);
      }
      start++;
      end--;
    }
  }

  public static void main(String[] args) {
    Game2048 game = new Game2048(4);
    game.initDefaultBoardForTest();
    System.out.println(game.toString());
    game.move(3);
    System.out.println(game.toString());

    game.initDefaultBoardForTest();
    game.rotateBoardForTest();
    System.out.println(game.toString());
    game.move(0);
    System.out.println(game.toString());

    game.initDefaultBoardForTest();
    game.rotateBoardForTest();
    game.rotateBoardForTest();
    System.out.println(game.toString());
    game.move(1);
    System.out.println(game.toString());

    game.initDefaultBoardForTest();
    game.rotateBoardForTest();
    game.rotateBoardForTest();
    game.rotateBoardForTest();
    System.out.println(game.toString());
    game.move(2);
    System.out.println(game.toString());
  }
}
