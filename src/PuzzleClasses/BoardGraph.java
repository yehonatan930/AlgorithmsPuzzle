package PuzzleClasses;

import DataStructures.Graph;
import DataStructures.Vertex;

public class BoardGraph extends Graph<Board> {

    public BoardGraph() {
        super();
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
