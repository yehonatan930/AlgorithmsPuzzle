package PuzzleClasses;

import DataStructures.Vertex;

import java.util.ArrayList;
import java.util.List;

public class BoardVertex extends Vertex<Board> {

    public BoardVertex(Board board) {
        super(board);
    }

    @Override
    public List<Vertex<Board>> getAdjecntVertices() {
        Board board = this.getValue();

        List<Vertex<Board>> adjecntVertices = new ArrayList<Vertex<Board>>();
        Board boardAfterMovingEmptyPieceUp = board.getBoardAfterMovingEmptyPieceUp();
        Board boardAfterMovingEmptyPieceDown = board.getBoardAfterMovingEmptyPieceDown();
        Board boardAfterMovingEmptyPieceLeft = board.getBoardAfterMovingEmptyPieceLeft();
        Board boardAfterMovingEmptyPieceRight = board.getBoardAfterMovingEmptyPieceRight();

        if (boardAfterMovingEmptyPieceUp != null) {
            adjecntVertices.add(new BoardVertex(boardAfterMovingEmptyPieceUp));
        }
        if (boardAfterMovingEmptyPieceDown != null) {
            adjecntVertices.add(new BoardVertex(boardAfterMovingEmptyPieceDown));
        }
        if (boardAfterMovingEmptyPieceLeft != null) {
            adjecntVertices.add(new BoardVertex(boardAfterMovingEmptyPieceLeft));
        }
        if (boardAfterMovingEmptyPieceRight != null) {
            adjecntVertices.add(new BoardVertex(boardAfterMovingEmptyPieceRight));
        }

        return adjecntVertices;
    }
}
