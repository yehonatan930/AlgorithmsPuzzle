package PuzzleClasses;

import DataStructures.GraphableValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board implements GraphableValue {
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

    public static Board getIdealBoard(BOARD_SIZES size) {
        int[][] idealBoard = new int[size.getNumVal()][size.getNumVal()];
        int counter = 1;
        for (int i = 0; i < size.getNumVal(); i++) {
            for (int j = 0; j < size.getNumVal(); j++) {
                idealBoard[i][j] = counter++;
            }
        }
        idealBoard[size.getNumVal() - 1][size.getNumVal() - 1] = 0;
        return new Board(size, idealBoard);
    }

    public static void switchPieces(int[][] board, int row1, int col1, int row2, int col2) {
        int temp = board[row1][col1];
        board[row1][col1] = board[row2][col2];
        board[row2][col2] = temp;
    }


    public BOARD_SIZES getSize() {
        return this.size;
    }

    public void setSize(BOARD_SIZES size) {
        this.size = size;
    }

    public int[][] getBoard() {
        return this.board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[] getEmptyPieceCoordinates() {
        int[] emptyPieceCoordinates = new int[2];
        for (int i = 0; i < this.size.getNumVal(); i++) {
            for (int j = 0; j < this.size.getNumVal(); j++) {
                if (this.board[i][j] == 0) {
                    emptyPieceCoordinates[0] = i;
                    emptyPieceCoordinates[1] = j;
                    return emptyPieceCoordinates;
                }
            }
        }
        return null;
    }

    public int[][] duplicateBoard() {
        int[][] duplicateBoard = new int[this.size.getNumVal()][this.size.getNumVal()];
        for (int i = 0; i < this.size.getNumVal(); i++) {
            System.arraycopy(this.board[i], 0, duplicateBoard[i], 0, this.size.getNumVal());
        }
        return duplicateBoard;
    }

    public Board getBoardAfterMovingEmptyPieceUp() {
        int[][] boardAfterMovingPieceUp = this.duplicateBoard();
        int[] emptyPieceCoordinates = getEmptyPieceCoordinates();
        int emptyPieceRow = emptyPieceCoordinates[0];
        int emptyPieceCol = emptyPieceCoordinates[1];
        if (emptyPieceRow == 0) {
            return null;
        }
        Board.switchPieces(boardAfterMovingPieceUp, emptyPieceRow, emptyPieceCol, emptyPieceRow - 1, emptyPieceCol);
        return new Board(this.size, boardAfterMovingPieceUp);
    }

    public Board getBoardAfterMovingEmptyPieceDown() {
        int[][] boardAfterMovingPieceDown = this.duplicateBoard();
        int[] emptyPieceCoordinates = getEmptyPieceCoordinates();
        int emptyPieceRow = emptyPieceCoordinates[0];
        int emptyPieceCol = emptyPieceCoordinates[1];
        if (emptyPieceRow == this.size.getNumVal() - 1) {
            return null;
        }
        Board.switchPieces(boardAfterMovingPieceDown, emptyPieceRow, emptyPieceCol, emptyPieceRow + 1, emptyPieceCol);
        return new Board(this.size, boardAfterMovingPieceDown);
    }


    public Board getBoardAfterMovingEmptyPieceLeft() {
        int[][] boardAfterMovingPieceLeft = this.duplicateBoard();
        int[] emptyPieceCoordinates = getEmptyPieceCoordinates();
        int emptyPieceRow = emptyPieceCoordinates[0];
        int emptyPieceCol = emptyPieceCoordinates[1];
        if (emptyPieceCol == 0) {
            return null;
        }
        Board.switchPieces(boardAfterMovingPieceLeft, emptyPieceRow, emptyPieceCol, emptyPieceRow, emptyPieceCol - 1);
        return new Board(this.size, boardAfterMovingPieceLeft);
    }

    public Board getBoardAfterMovingEmptyPieceRight() {
        int[][] boardAfterMovingPieceRight = this.duplicateBoard();
        int[] emptyPieceCoordinates = getEmptyPieceCoordinates();
        int emptyPieceRow = emptyPieceCoordinates[0];
        int emptyPieceCol = emptyPieceCoordinates[1];
        if (emptyPieceCol == this.size.getNumVal() - 1) {
            return null;
        }
        Board.switchPieces(boardAfterMovingPieceRight, emptyPieceRow, emptyPieceCol, emptyPieceRow, emptyPieceCol + 1);
        return new Board(this.size, boardAfterMovingPieceRight);
    }

    @Override
    public List<GraphableValue> getAdjacentValues() {

        List<GraphableValue> adjecntValues = new ArrayList<>();
        Board boardAfterMovingEmptyPieceUp = this.getBoardAfterMovingEmptyPieceUp();
        Board boardAfterMovingEmptyPieceDown = this.getBoardAfterMovingEmptyPieceDown();
        Board boardAfterMovingEmptyPieceLeft = this.getBoardAfterMovingEmptyPieceLeft();
        Board boardAfterMovingEmptyPieceRight = this.getBoardAfterMovingEmptyPieceRight();

        if (boardAfterMovingEmptyPieceUp != null) {
            adjecntValues.add(boardAfterMovingEmptyPieceUp);
        }
        if (boardAfterMovingEmptyPieceDown != null) {
            adjecntValues.add(boardAfterMovingEmptyPieceDown);
        }
        if (boardAfterMovingEmptyPieceLeft != null) {
            adjecntValues.add(boardAfterMovingEmptyPieceLeft);
        }
        if (boardAfterMovingEmptyPieceRight != null) {
            adjecntValues.add(boardAfterMovingEmptyPieceRight);
        }
        return adjecntValues;
    }

    public Board moveRandomly(int n) {
        Board currentBoard = this;
        for (int i = 0; i < n; i++) {
            List<GraphableValue> adjecntValues = currentBoard.getAdjacentValues();
            int randomIndex = (int) (Math.random() * adjecntValues.size());
            currentBoard = (Board) adjecntValues.get(randomIndex);
        }
        return currentBoard;
    }

    public String toString() {
        String boardString = "";
        for (int i = 0; i < this.size.getNumVal(); i++) {
            for (int j = 0; j < this.size.getNumVal(); j++) {
                boardString += this.board[i][j] + " ";
            }
            boardString += "\n";
        }
        return boardString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board)) return false;

        Board board1 = (Board) o;

        if (this.getSize() != board1.getSize()) return false;
        return Arrays.deepEquals(this.board, board1.getBoard());
    }

    @Override
    public int hashCode() {
        int result = this.size.hashCode();
        result = 31 * result + Arrays.deepHashCode(this.board);
        return result;
    }

    public int[] getPositionInBoard(int value) {
        int[] position = new int[2];
        for (int i = 0; i < this.size.getNumVal(); i++) {
            for (int j = 0; j < this.size.getNumVal(); j++) {
                if (this.board[i][j] == value) {
                    position[0] = i;
                    position[1] = j;
                    return position;
                }
            }
        }
        return null;
    }

    @Override
    public int getManhattanDistanceFromIdealValue() {
        int manhattanDistance = 0;
        Board idealBoard = Board.getIdealBoard(this.size);
        for (int i = 0; i < this.size.getNumVal(); i++) {
            for (int j = 0; j < this.size.getNumVal(); j++) {
                int[] positionInIdealBoard = idealBoard.getPositionInBoard(this.board[i][j]);
                manhattanDistance += Math.abs(i - positionInIdealBoard[0]) + Math.abs(j - positionInIdealBoard[1]);
            }
        }
        return manhattanDistance;
    }
}
