import DataStructures.Graph;
import DataStructures.Vertex;
import PuzzleClasses.BOARD_SIZES;
import PuzzleClasses.Board;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
//        Board board = IO.inputBoard();
//        System.out.println(board);

        Graph<Vertex> graph = new Graph<Vertex>(new Vertex(Board.getIdealBoard(BOARD_SIZES.A)));

        Board board = (Board) graph.moveRandomly(1).getValue();
        System.out.println(board);
    }
}