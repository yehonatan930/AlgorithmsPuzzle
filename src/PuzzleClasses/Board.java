package PuzzleClasses;

public class Board {
    BOARD_SIZES size;
    int[][] board;

    public Board(BOARD_SIZES size) {
        this.size = size;
        this.board = new int[size.getNumVal()][size.getNumVal()];
    }

    public Board(BOARD_SIZES size, int[][] board) {
        this.size = size;
        this.board = board;
    }

    public static int[][] getIdealBoard(BOARD_SIZES size) {
        int[][] idealBoard = new int[size.getNumVal()][size.getNumVal()];
        int counter = 1;
        for (int i = 0; i < size.getNumVal(); i++) {
            for (int j = 0; j < size.getNumVal(); j++) {
                idealBoard[i][j] = counter++;
            }
        }
        idealBoard[size.getNumVal() - 1][size.getNumVal() - 1] = 0;
        return idealBoard;
    }

    public void printBoard() {
        for (int i = 0; i < size.getNumVal(); i++) {
            for (int j = 0; j < size.getNumVal(); j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public BOARD_SIZES getSize() {
        return size;
    }

    public void setSize(BOARD_SIZES size) {
        this.size = size;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }
}
