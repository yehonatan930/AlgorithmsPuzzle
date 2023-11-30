import PuzzleClasses.BOARD_SIZES;
import PuzzleClasses.Board;
import PuzzleClasses.BoardGraph;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
//        Board board = IO.inputBoard();
//        System.out.println(board);

        Board board = BoardGraph.getSolvableBoard(BOARD_SIZES.A, 1).getValue();
        System.out.println(board);

//        for (GraphableValue board :
//                Board.getIdealBoard(BOARD_SIZES.A).getAdjecntValues()) {
//            System.out.println(board);
//        }
    }
}