package PuzzleClasses;

import DataStructures.Graph;
import DataStructures.Vertex;

import java.util.List;

public class BoardGraph extends Graph<Board> {

    static int counter = 0;

    public BoardGraph() {
        super();
    }

    public BoardGraph(Vertex<Board> boardVertex) {
        super(boardVertex);
    }


    public static Vertex<Board> getSolvableBoard(BOARD_SIZES size, int n) {
        Vertex<Board> boardVertex = new Vertex<Board>(Board.getIdealBoard(size));
        BoardGraph boardGraph = new BoardGraph(boardVertex);
        boardGraph.buildGraph(boardVertex);
        return boardGraph.moveRandomly(n);
    }

    @Override
    public void buildGraph(Vertex<Board> boardVertex) {
        counter++;
        System.out.println(counter);
        if (boardVertex == null) {
            return;
        }
        Board board = boardVertex.getValue();
        if (board == null) {
            return;
        }

        List<Vertex<Board>> adjecntVertices = boardVertex.getAdjecntVertices();

        for (Vertex<Board> adjecntVertex : adjecntVertices) {
            boolean isVertexAlreadyInGraph = this.getAdjecntVerticesPerVertex().containsKey(adjecntVertex);

            if (!isVertexAlreadyInGraph) {
                System.out.println(adjecntVertex.getValue());
                this.addVertex(adjecntVertex);
                this.addEdge(boardVertex, adjecntVertex);
                buildGraph(adjecntVertex);
            }
        }
    }
}
