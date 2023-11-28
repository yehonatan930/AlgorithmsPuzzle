package PuzzleClasses;

import DataStructures.Graph;

public class BoardGraph extends Graph<Board> {

    public BoardGraph(Board board) {
        super();
        BoardVertex boardVertex = new BoardVertex(board);
        this.addVertex(boardVertex);
    }

}
