package IO;

import PuzzleClasses.BOARD_SIZES;
import PuzzleClasses.Board;

import java.util.Scanner;

public class IO {
    static Scanner sc = new Scanner(System.in);

    public static Board inputBoard() {
        BOARD_SIZES size = inputPuzzleSize();
        Board board = new Board(size);

        System.out.println("Enter the board: ");
        for (int i = 0; i < size.getNumVal(); i++) {
            for (int j = 0; j < size.getNumVal(); j++) {
                board.getBoard()[i][j] = sc.nextInt();
            }
        }
        return board;
    }

    static BOARD_SIZES inputPuzzleSize() {
        System.out.println("Enter the size of the puzzle 15/24: ");
        int size = sc.nextInt();
        if (size == 15) {
            return BOARD_SIZES.FIFTEEN;
        } else if (size == 24) {
            return BOARD_SIZES.TWENTY_FOUR;
        } else {
            System.out.println("Invalid size. Please enter 15 or 24.");
            return inputPuzzleSize();
        }
    }

}
