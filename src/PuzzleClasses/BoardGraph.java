package PuzzleClasses;

import DataStructures.Graph;
import DataStructures.Vertex;

public class BoardGraph extends Graph<Board> {

    public BoardGraph() {
        super();
    }

    public BoardGraph(Vertex<Board> boardVertex) {
        super(boardVertex);
    }

    public BOARD_SIZES getBoardSize() {
        return this.getAdjecntVerticesPerVertex().keySet().iterator().next().getValue().getSize();
    }

    public Vertex<Board> getSolvableBoard(int n) {
        BoardVertex boardVertex = new BoardVertex(Board.getIdealBoard(this.getBoardSize()));
        BoardGraph boardGraph = new BoardGraph(boardVertex);
        boardGraph.buildGraph(boardVertex);
        return boardGraph.moveRandomly(n);
    }

    @Override
    public void buildGraph(Vertex<Board> boardVertex) {
        if (boardVertex == null) {
            return;
        }
        Board board = boardVertex.getValue();
        if (board == null) {
            return;
        }

        for (Vertex<Board> adjecntVertex : ((BoardVertex) boardVertex).getAdjecntVertices()) {
            if (!this.getAdjecntVerticesPerVertex().containsKey(adjecntVertex)) {
                this.addVertex(adjecntVertex);
                this.addEdge(boardVertex, adjecntVertex);
                buildGraph(adjecntVertex);
            }
        }
    }
}
