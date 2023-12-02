import PuzzleClasses.BOARD_SIZES;
import PuzzleClasses.Board;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
//        Board board = IO.inputBoard();
//        System.out.println(board);

        Board board = Board.getIdealBoard(BOARD_SIZES.A).moveRandomly(40);
        System.out.println(board);
    }
}